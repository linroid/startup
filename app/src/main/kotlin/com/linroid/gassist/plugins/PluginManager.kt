package com.linroid.gassist.plugins

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import com.linroid.gassist.App
import com.linroid.gassist.monitor.GameMonitor
import com.linroid.gassist.monitor.MonitorListener
import com.linroid.gassist.plugins.call.CallPlugin
import com.linroid.gassist.plugins.keyboard.KeyboardPlugin
import com.linroid.gassist.plugins.notification.NotificationPlugin

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
@SuppressLint("StaticFieldLeak")
object PluginManager : MonitorListener {
    val plugins = ArrayList<Plugin<*>>()
    val monitor: GameMonitor

    init {
        registerPlugins()
        monitor = GameMonitor()
        monitor.listener = this
    }

    fun turnOn() {
    }

    fun turnOff() {

    }

    private fun registerPlugins() {
        val context: Context = App.get();
        plugins.add(KeyboardPlugin(context))
        plugins.add(CallPlugin(context))
        plugins.add(NotificationPlugin(context))
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

    /**
     * 根据 ID 寻找插件
     */
    fun find(id: Int): Plugin<*> {
        return plugins.find { it.id == id }!!
    }
}