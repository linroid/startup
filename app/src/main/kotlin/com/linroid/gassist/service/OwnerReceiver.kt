package com.linroid.gassist.service

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

/**
 * @author linroid <linroid@gmail.com>
 * @since 04/08/2017
 */
class OwnerReceiver : DeviceAdminReceiver(), AnkoLogger {
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        debug("onReceive, intent=${intent}")
    }

    override fun getManager(context: Context?): DevicePolicyManager {
        return super.getManager(context)
    }
}