package com.linroid.gassist.plugins.call

import android.content.Context
import android.content.pm.ApplicationInfo
import com.linroid.gassist.R
import com.linroid.gassist.plugins.Plugin

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
class CallPlugin(context: Context) : Plugin<CallConfig>(context, R.id.plugin_call) {
    override fun description(): String {
        return context.resources.getString(R.string.plugin_des_call)
    }

    override fun name(): String {
        return context.resources.getString(R.string.plugin_name_call)
    }

    override fun change(config: CallConfig) {
    }

    override fun shutdown() {
    }

    override fun startup() {
    }

    override fun onGameChanged(info: ApplicationInfo) {
    }

}