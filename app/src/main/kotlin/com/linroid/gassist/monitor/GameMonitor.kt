package com.linroid.gassist.monitor

import android.content.Context
import android.content.pm.ApplicationInfo
import android.text.TextUtils
import com.linroid.gassist.App
import timber.log.Timber

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
class GameMonitor() {
    val context: Context = App.get()
    var listener: MonitorListener? = null
    var prevPkgName: String? = null
    val games: MutableSet<String> = HashSet<String>()

    fun onAppLaunched(pkgName: String) {
        if (TextUtils.equals(pkgName, prevPkgName)) {
            Timber.w("%s is current now, ignore it", pkgName)
            return
        }
        Timber.d("onAppLaunched: prev=%s, cur=%s", prevPkgName, pkgName)
        val prevIsGame = games.contains(prevPkgName)
        val curIsGame = games.contains(pkgName)
        if (curIsGame) {
            if (prevIsGame) {
                listener?.onSwitch(info(pkgName))
            } else {
                listener?.onStart(info(pkgName))
            }
        } else {
            if (prevIsGame) {
                listener?.onStop(info(pkgName))
            }
        }
    }

    private fun info(pkgName: String): ApplicationInfo {
        return context.packageManager.getApplicationInfo(pkgName, 0)
    }
}