package com.linroid.gassist.plugins.keyboard

import android.content.Context
import android.content.pm.ApplicationInfo
import com.linroid.gassist.R
import com.linroid.gassist.plugins.Plugin

/**
 * 按键屏蔽
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
class KeyboardPlugin(context: Context) : Plugin<KeyboardConfig>(context, R.id.plugin_keyboard) {
    override fun description(): String {
        return context.resources.getString(R.string.plugin_des_keyboard)
    }

    override fun name(): String {
        return context.resources.getString(R.string.plugin_name_keyboard)
    }

    override fun change(config: KeyboardConfig) {
    }

    override fun shutdown(info: ApplicationInfo) {
    }

    override fun startup(info: ApplicationInfo) {
    }

    override fun switch(info: ApplicationInfo) {
    }
}