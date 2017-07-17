package com.linroid.gassist.plugins

import android.content.Context
import android.content.pm.ApplicationInfo
import com.linroid.gassist.monitor.GameMonitor
import com.linroid.gassist.monitor.MonitorListener
import com.linroid.gassist.plugins.call.CallPlugin
import com.linroid.gassist.plugins.keyboard.KeyboardPlugin

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
class PluginManager(val context: Context) : MonitorListener {

    val plugins = ArrayList<Plugin<*>>()
    val monitor: GameMonitor

    init {
        registerPlugins()
        monitor = GameMonitor(context)
        monitor.listener = this
    }

    fun turnOn() {

    }

    fun turnOff() {

    }

    private fun registerPlugins() {
        plugins.add(KeyboardPlugin(context))
        plugins.add(CallPlugin(context))
    }

    override fun onStart(info: ApplicationInfo) {
        plugins.filter { it.enabled() }
                .forEach { it.startup(info) }
    }

    override fun onStop(info: ApplicationInfo) {
        plugins.filter { it.enabled() }
                .forEach { it.shutdown(info) }
    }

    override fun onSwitch(info: ApplicationInfo) {
        plugins.filter { it.enabled() }
                .forEach { it.switch(info) }
    }
}