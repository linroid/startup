package com.linroid.gassist.plugins.notification

import android.content.Context
import android.content.pm.ApplicationInfo
import com.linroid.gassist.R
import com.linroid.gassist.plugins.Plugin

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
    }

    override fun onGameChanged(info: ApplicationInfo) {
    }

    override fun startup() {
    }

}