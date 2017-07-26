package com.linroid.gassist.monitor

import android.content.Context
import android.content.pm.ApplicationInfo
import android.text.TextUtils
import com.linroid.gassist.App
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.warn

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
class GameMonitor : AnkoLogger {
    val context: Context = App.get()
    var listener: MonitorListener? = null
    var prevPkgName: String? = null
    val games: MutableSet<String> = HashSet<String>()

    fun onAppLaunched(pkgName: String) {
        if (TextUtils.equals(pkgName, prevPkgName)) {
            warn("$pkgName is current now, ignore it")
            return
        }
        debug("onAppLaunched: prev=$prevPkgName, cur=$pkgName")
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