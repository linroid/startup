package com.linroid.gassist.plugins.call

import android.content.Context
import android.content.pm.ApplicationInfo
import android.telephony.TelephonyManager
import com.android.internal.telephony.ITelephony
import com.linroid.gassist.R
import com.linroid.gassist.bus.Bus
import com.linroid.gassist.bus.events.IncomingEvent
import com.linroid.gassist.plugins.Plugin
import com.linroid.gassist.safeDispose
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.info
import android.content.pm.PackageManager
import android.content.ComponentName
import com.linroid.gassist.receiver.IncomingReceiver


/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
class CallPlugin(context: Context) : Plugin<CallConfig>(context, R.id.plugin_call) {
    var disposable: Disposable? = null

    override fun description(): String {
        return context.resources.getString(R.string.plugin_des_call)
    }

    override fun name(): String {
        return context.resources.getString(R.string.plugin_name_call)
    }

    override fun change(config: CallConfig) {
    }

    override fun shutdown() {
        setReceiverEnabled(false)
        disposable.safeDispose()
    }

    override fun startup() {
        setReceiverEnabled(true)
        disposable = Bus.register(IncomingEvent::class)
                .subscribe({ (number) ->
                    blockIncoming(number)
                }, { error ->
                    error.printStackTrace()
                })
    }

    override fun onGameChanged(info: ApplicationInfo) {
    }

    private fun setReceiverEnabled(enabled: Boolean) {
        val newState = if (enabled) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        val componentName = ComponentName(context, IncomingReceiver::class.java)
        context.packageManager.setComponentEnabledSetting(componentName, newState,
                PackageManager.DONT_KILL_APP)
    }

    private fun blockIncoming(number: String) {
        info("block incoming number $number")
        val manager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        try {
            val c = Class.forName(manager.javaClass.name)
            val m = c.getDeclaredMethod("getITelephony")
            m.isAccessible = true
            val telephony = m.invoke(manager) as ITelephony
            telephony.endCall()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}