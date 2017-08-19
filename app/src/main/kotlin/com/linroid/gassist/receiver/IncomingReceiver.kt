package com.linroid.gassist.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import com.linroid.gassist.bus.Bus
import com.linroid.gassist.bus.events.IncomingEvent


/**
 * Created by linroid on 8/19/17.
 */
class IncomingReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val telephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        //Assign a phone state listener.
        telephony.listen(IncomingBlockerListener(context), PhoneStateListener.LISTEN_CALL_STATE)
    }

    companion object {
        class IncomingBlockerListener(val context: Context) : PhoneStateListener() {
            override fun onCallStateChanged(state: Int, incomingNumber: String) {
                super.onCallStateChanged(state, incomingNumber)
                when (state) {
                    TelephonyManager.CALL_STATE_IDLE -> {
                    }
                    TelephonyManager.CALL_STATE_OFFHOOK -> {
                        //handle out going call
                        Bus.send(IncomingEvent(incomingNumber))
                    }
                    TelephonyManager.CALL_STATE_RINGING -> {
                        //handle in coming call
                        Bus.send(IncomingEvent(incomingNumber))
                    }
                    else -> {
                    }
                }
            }
        }
    }
}