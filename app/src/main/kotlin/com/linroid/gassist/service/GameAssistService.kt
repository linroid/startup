package com.linroid.gassist.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityWindowInfo
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import java.io.FileDescriptor
import java.io.PrintWriter

/**
 * @author linroid <linroid@gmail.com>
 * @since 20/07/2017
 */
class GameAssistService : AccessibilityService(), AnkoLogger {
    override fun getWindows(): MutableList<AccessibilityWindowInfo> {
        debug("getWindows()")
        return super.getWindows()
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        debug("onKeyEvent, event=$event")
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
        debug("onAccessibilityEvent, event=$event")
    }

}