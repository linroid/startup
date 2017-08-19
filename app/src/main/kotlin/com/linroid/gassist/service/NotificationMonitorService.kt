package com.linroid.gassist.service

import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug


/**
 * @author linroid <linroid@gmail.com>
 * @since 03/08/2017
 */
class NotificationMonitorService : NotificationListenerService(), AnkoLogger {

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        debug("onNotificationRemoved")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        super.onNotificationRemoved(sbn, rankingMap)
    }

    override fun onNotificationRankingUpdate(rankingMap: RankingMap?) {
        super.onNotificationRankingUpdate(rankingMap)
    }

    override fun onInterruptionFilterChanged(interruptionFilter: Int) {
        super.onInterruptionFilterChanged(interruptionFilter)
        debug("onInterruptionFilterChanged")
    }

    override fun onListenerHintsChanged(hints: Int) {
        super.onListenerHintsChanged(hints)
        debug("onListenerHintsChanged")
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        debug("onListenerConnected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        debug("onNotificationPosted")
        cancelNotification(sbn)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        super.onNotificationPosted(sbn, rankingMap)
        debug("onNotificationPosted rankingMap")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        debug("onListenerDisconnected")
    }

    override fun getActiveNotifications(): Array<StatusBarNotification> {
        return super.getActiveNotifications()
    }

    override fun getActiveNotifications(keys: Array<out String>?): Array<StatusBarNotification> {
        return super.getActiveNotifications(keys)
    }

    override fun getCurrentRanking(): RankingMap {
        return super.getCurrentRanking()
    }

    fun cancelNotification(sbn: StatusBarNotification) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cancelNotification(sbn.key)
        } else {
            cancelNotification(sbn.packageName, sbn.tag, sbn.id)
        }
    }
}