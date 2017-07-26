package com.linroid.gassist

import android.view.ViewManager
import com.github.clans.fab.FloatingActionButton
import org.jetbrains.anko.custom.ankoView

/**
 * @author linroid <linroid@gmail.com>
 * @since 25/07/2017
 */
inline fun ViewManager.floatingActionButton() = floatingActionButton() {}

inline fun ViewManager.floatingActionButton(init: FloatingActionButton.() -> Unit): FloatingActionButton {
    return ankoView({ FloatingActionButton(it) }, theme = 0) {
        init()
    }
}