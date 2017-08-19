package com.linroid.gassist.plugins.notification

import android.annotation.TargetApi
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.text.TextUtils
import com.linroid.gassist.R
import com.linroid.gassist.plugins.Plugin
import org.jetbrains.anko.debug
import org.jetbrains.anko.toast


/**
 * @author linroid <linroid@gmail.com>
 * @since 20/07/2017
 */
class NotificationPlugin(context: Context) : Plugin<NotificationConfig>(context, R.id.plugin_notification) {

    override fun name(): String {
        return context.resources.getString(R.string.plugin_name_notification)
    }

    override fun description(): String {
        return context.resources.getString(R.string.plugin_des_notification)
    }

    override fun change(config: NotificationConfig) {
    }

    override fun shutdown() {
//        setHeadsUpEnabled(false)
    }

    override fun onGameChanged(info: ApplicationInfo) {
    }

    override fun startup() {
//        setHeadsUpEnabled(true)
    }

    private fun setHeadsUpEnabled(enabled: Boolean) {
        val process = Runtime.getRuntime().exec("sh")
//        adb shell pm grant com.linroid.gassist.dev android.permission.WRITE_SECURE_SETTINGS
        process.outputStream.bufferedWriter().write("settings put global heads_up_notifications_enabled " + (if (enabled) 1 else 0) + "\n")
//        debug("error:" + process.errorStream.bufferedReader().readText())
        debug("output:" + process.inputStream.bufferedReader().readText())
        process.waitFor()
        process.destroy()
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun checkPrecondition(): Boolean {
        if (!Settings.System.canWrite(context)) {
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                    Uri.parse("package:" + context.packageName))
            context.startActivity(intent)
            return false
        }
        if (!isNotificationListenerEnabled()) {
            navToNotificationSettings()
            return false
        }
        return super.checkPrecondition()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun navToNotificationSettings() {
        context.toast("todo > 开启通知权限")
        context.startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
    }

    override fun isAvailable(): Boolean {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) and super.isAvailable()
    }

    private fun isNotificationListenerEnabled(): Boolean {
        val pkgName = context.packageName
        val flat = Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
        if (!TextUtils.isEmpty(flat)) {
            val names = flat.split(":".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            names.indices
                    .map { ComponentName.unflattenFromString(names[it]) }
                    .filter { it != null && TextUtils.equals(pkgName, it.packageName) }
                    .forEach { return true }
        }
        return false
    }
}