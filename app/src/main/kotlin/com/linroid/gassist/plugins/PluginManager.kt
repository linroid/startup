package com.linroid.gassist.plugins

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.widget.Toast
import com.linroid.gassist.App
import com.linroid.gassist.BuildConfig
import com.linroid.gassist.R
import com.linroid.gassist.bus.Bus
import com.linroid.gassist.bus.events.StatusChangedEvent
import com.linroid.gassist.bus.events.StopEvent
import com.linroid.gassist.monitor.GameMonitor
import com.linroid.gassist.monitor.MonitorListener
import com.linroid.gassist.plugins.call.CallPlugin
import com.linroid.gassist.plugins.keyboard.KeyboardPlugin
import com.linroid.gassist.plugins.notification.NotificationPlugin
import com.linroid.gassist.safeDispose
import com.linroid.gassist.service.GameAssistService
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
@SuppressLint("StaticFieldLeak")
object PluginManager : MonitorListener, AnkoLogger {
    val NOTIFICATION_ID = 0x110
    val ACTION_STOP = "${BuildConfig.APPLICATION_ID}.action.stop"

    val plugins = ArrayList<Plugin<*>>()
    val monitor: GameMonitor
    var service: GameAssistService? = null
    var running = false
    val context: Context
    val notificationManager: NotificationManager
    var stopDisposable: Disposable? = null

    init {
        context = App.get()
        monitor = GameMonitor()
        monitor.listener = this
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        registerPlugins()
    }

    fun turnOn() {
        plugins.filter { it.enabled }.forEach {
            if (!it.checkPrecondition()) {
                warn("${it.name()} check precondition failed")
                return
            }
        }
        info("turn on all plugins")
        running = true
        plugins.filter { it.enabled }
                .forEach { it.startup() }
        stopDisposable.safeDispose()
        stopDisposable = Bus.register(StopEvent::class).subscribe { turnOff() }
        showNotification()
        sendEvent()
        Toast.makeText(context, R.string.toast_running, Toast.LENGTH_SHORT).show()
    }

    fun turnOff() {
        warn("turn off all plugins")
        running = false
        plugins.filter { it.enabled }
                .forEach { it.shutdown() }
        cancelNotification()
        sendEvent()
        Toast.makeText(context, R.string.toast_stopped, Toast.LENGTH_SHORT).show()
    }

    private fun sendEvent() {
        Bus.send(StatusChangedEvent(running))
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


    private fun registerPlugins() {
        plugins.add(KeyboardPlugin(context))
        plugins.add(CallPlugin(context))
        plugins.add(NotificationPlugin(context))
    }

    private fun showNotification() {
        val notification = Notification.Builder(context).apply {
            setOngoing(true)
            setContentTitle(context.getString(R.string.title_notification_running))
            setContentText(context.getString(R.string.content_notification_running))
            setSmallIcon(R.mipmap.ic_launcher)
            val stopIntent = Intent(ACTION_STOP)
            setContentIntent(PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT))
        }.build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
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