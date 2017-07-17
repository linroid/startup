package com.linroid.gassist.monitor

import android.content.pm.ApplicationInfo

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
interface MonitorListener {
    fun onStart(info: ApplicationInfo)
    fun onStop(info: ApplicationInfo)
    /**
     * 切换到其他游戏
     */
    fun onSwitch(info: ApplicationInfo)
}