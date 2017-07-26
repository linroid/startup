package com.linroid.gassist.plugins

import android.content.Context
import android.content.pm.ApplicationInfo

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
abstract class Plugin<in T : PluginConfig>(val context: Context, val id: Int) {
    /**
     * 插件名字
     */
    abstract fun name(): String

    /**
     * 描述
     */
    abstract fun description(): String

    /**
     * 配置改变
     */
    abstract fun change(config: T)

    /**
     * 结束
     */
    abstract fun shutdown(info: ApplicationInfo): Unit

    /**
     * 切换到了其他游戏
     */
    abstract fun switch(info: ApplicationInfo): Unit

    /**
     * 启动
     */
    abstract fun startup(info: ApplicationInfo): Unit

    fun enabled(): Boolean {
        return true
    }
}