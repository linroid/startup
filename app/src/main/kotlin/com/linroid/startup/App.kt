package com.linroid.startup

import android.app.Application

/**
 * @author linroid <linroid@gmail.com>
 * @since 16/07/2017
 */
class App : Application() {

    companion object {
        private lateinit var instance: App

        @JvmStatic
        fun get(): App {
            return instance;
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}