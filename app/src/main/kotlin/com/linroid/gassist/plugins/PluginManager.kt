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
import com.linroid.gassist.service.GameAssistService
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
@SuppressLint("StaticFieldLeak")
object PluginManager : MonitorListener, AnkoLogger {
    val plugins = ArrayList<Plugin<*>>()
    val monitor: GameMonitor
    var service: GameAssistService? = null
    var running = false

    init {
        registerPlugins()
        monitor = GameMonitor()
        monitor.listener = this
    }

    fun turnOn() {
        info("turn on all plugins")
        running = true
        plugins.filter { it.enabled }
                .forEach { it.startup() }
    }

    fun turnOff() {
        warn("turn off all plugins")
        running = false
        plugins.filter { it.enabled }
                .forEach { it.shutdown() }
    }

    private fun registerPlugins() {
        val context: Context = App.get()
        plugins.add(KeyboardPlugin(context))
        plugins.add(CallPlugin(context))
        plugins.add(NotificationPlugin(context))
    }

    override fun onStart(info: ApplicationInfo) {

    }

    override fun onStop(info: ApplicationInfo) {
    }

    override fun onSwitch(info: ApplicationInfo) {
        if (isRunning()) {
            plugins.filter { it.enabled }
                    .forEach { it.onGameChanged(info) }
        }
    }

    /**
     * 根据 ID 寻找插件
     */
    fun find(id: Int): Plugin<*> {
        return plugins.find { it.id == id }!!
    }

    fun isRunning(): Boolean {
        return running
    }

    fun bindService(service: GameAssistService) {
        this.service = service
    }

    fun unbindService(service: GameAssistService) {
        this.service = null
    }
}