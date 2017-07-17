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

    override fun name(): String {
        return context.resources.getString(R.string.plugin_call)
    }

    override fun change(config: CallConfig) {
    }

    override fun shutdown(info: ApplicationInfo) {
    }

    override fun startup(info: ApplicationInfo) {
    }

    override fun switch(info: ApplicationInfo) {
    }

}