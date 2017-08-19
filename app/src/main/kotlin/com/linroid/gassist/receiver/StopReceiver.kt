package com.linroid.gassist.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.linroid.gassist.bus.Bus
import com.linroid.gassist.bus.events.StopEvent
import com.linroid.gassist.plugins.PluginManager

/**
 * @author linroid<linroid@gmail.com>
 * @since 8/19/17
 */
class StopReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (PluginManager.ACTION_STOP == intent.action) {
            Bus.send(StopEvent())
        }
    }
}
