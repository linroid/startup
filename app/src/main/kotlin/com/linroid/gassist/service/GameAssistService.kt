package com.linroid.gassist.service

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.text.TextUtils
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityWindowInfo
import com.linroid.gassist.plugins.PluginManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import java.io.FileDescriptor
import java.io.PrintWriter


/**
 * @author linroid <linroid@gmail.com>
 * @since 20/07/2017
 */
class GameAssistService : AccessibilityService(), AnkoLogger {
    private var keyEventInterceptors = HashSet<KeyEventInterceptor>()

    companion object : AnkoLogger {
        fun isAccessibilitySettingsOn(context: Context): Boolean {
            var accessibilityEnabled = 0
            val service = context.packageName + "/" + GameAssistService::class.java.canonicalName
            try {
                accessibilityEnabled = Settings.Secure.getInt(
                        context.applicationContext.contentResolver,
                        Settings.Secure.ACCESSIBILITY_ENABLED)
            } catch (e: Settings.SettingNotFoundException) {
                e.printStackTrace()
            }

            val splitter = TextUtils.SimpleStringSplitter(':')

            if (accessibilityEnabled == 1) {
                val settingValue = Settings.Secure.getString(
                        context.applicationContext.contentResolver,
                        Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
                if (settingValue != null) {
                    splitter.setString(settingValue)
                    while (splitter.hasNext()) {
                        val accessibilityService = splitter.next()
                        if (accessibilityService.equals(service, ignoreCase = true)) {
                            return true
                        }
                    }
                }
            }

            return false
        }
    }

    override fun getWindows(): MutableList<AccessibilityWindowInfo> {
        debug("getWindows()")
        return super.getWindows()
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        debug("onKeyEvent, event=$event")
        if (keyEventInterceptors.size > 0) {
            keyEventInterceptors.forEach {
                if (it.onKeyEvent(event)) {
                    return true
                }
            }
        }
        return super.onKeyEvent(event)
    }

    override fun getRootInActiveWindow(): AccessibilityNodeInfo {
        debug("getRootInActiveWindow")
        return super.getRootInActiveWindow()
    }

    override fun findFocus(focus: Int): AccessibilityNodeInfo {
        debug("findFocus")
        return super.findFocus(focus)
    }

    override fun onGesture(gestureId: Int): Boolean {
        debug("onGesture")
        return super.onGesture(gestureId)
    }

    override fun onServiceConnected() {
        debug("onServiceConnected")
        PluginManager.bindService(this)
        super.onServiceConnected()
    }

    override fun onRebind(intent: Intent?) {
        debug("onRebind")
        super.onRebind(intent)
    }

    override fun dump(fd: FileDescriptor, writer: PrintWriter, args: Array<out String>) {
        debug("dump")
        super.dump(fd, writer, args)
    }

    override fun onCreate() {
        debug("onCreate")
        super.onCreate()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        debug("onUnbind")
        PluginManager.unbindService(this)
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        debug("onDestroy")
        super.onDestroy()
    }

    override fun onInterrupt() {
        debug("onInterrupt")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        debug("onAccessibilityEvent")
    }

    fun registerKeyEventInterceptor(interceptor: KeyEventInterceptor) {
        keyEventInterceptors.add(interceptor);
    }

    fun unregisterKeyEventInterceptor(interceptor: KeyEventInterceptor) {
        keyEventInterceptors.remove(interceptor)
    }

    interface KeyEventInterceptor {
        fun onKeyEvent(event: KeyEvent): Boolean
    }
}