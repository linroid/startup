package com.linroid.gassist.plugins.keyboard

import android.content.Context
import android.content.pm.ApplicationInfo
import android.view.KeyEvent
import com.linroid.gassist.R
import com.linroid.gassist.plugins.Plugin
import com.linroid.gassist.plugins.PluginManager
import com.linroid.gassist.service.GameAssistService

/**
 * 按键屏蔽
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
class KeyboardPlugin(context: Context) : Plugin<KeyboardConfig>(context, R.id.plugin_keyboard), GameAssistService.KeyEventInterceptor {

    override fun description(): String {
        return context.resources.getString(R.string.plugin_des_keyboard)
    }

    override fun name(): String {
        return context.resources.getString(R.string.plugin_name_keyboard)
    }

    override fun change(config: KeyboardConfig) {
    }

    override fun shutdown() {
        PluginManager.service?.unregisterKeyEventInterceptor(this)
    }

    override fun startup() {
        PluginManager.service?.registerKeyEventInterceptor(this)
    }

    override fun onGameChanged(info: ApplicationInfo) {
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        if (enabled) {
            when (event.keyCode) {
                KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_MUTE -> {
//                    if (!config.volumeKey) {
//                        return false
//                    }
                }
            }
            return true
        }
        return false
    }

}