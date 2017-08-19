package com.linroid.gassist.bus

import com.linroid.gassist.bus.events.IncomingEvent
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import kotlin.reflect.KClass

/**
 * Created by linroid on 8/19/17.
 */
object Bus {
    private val _bus = PublishProcessor.create<Any>()

    fun send(event: Any) {
        _bus.onNext(event)
    }

    fun <T> register(clazz: Class<T>): Flowable<T> {
        return _bus.ofType(clazz)
    }

    fun <T : Any> register(clazz: KClass<T>): Flowable<T> {
        return register(clazz.java)
    }
}