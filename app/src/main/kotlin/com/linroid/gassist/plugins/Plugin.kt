package com.linroid.gassist.plugins

import android.content.Context
import android.content.pm.ApplicationInfo
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
abstract class Plugin<T : PluginConfig>(val context: Context, val id: Int) {
    lateinit var config: T
    var enabled by Delegates.observable(true, { prop: KProperty<*>, oldVal: Boolean, newVal: Boolean ->

    })

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
    abstract fun shutdown(): Unit

    /**
     * 切换到了其他游戏
     */
    abstract fun onGameChanged(info: ApplicationInfo): Unit

    /**
     * 启动
     */
    abstract fun startup(): Unit

    fun switch() {
        enabled = !enabled
    }

}