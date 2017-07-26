package com.linroid.gassist.ui.component

import android.R
import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.linroid.gassist.attr
import org.jetbrains.anko.*

/**
 * @author linroid <linroid@gmail.com>
 * @since 27/07/2017
 */
class PluginActivityUI : AnkoComponent<Activity> {
    lateinit var pluginDes: TextView

    override fun createView(ui: AnkoContext<Activity>) = ui.apply {
        verticalLayout {
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                horizontalPadding = dip(16)
                backgroundColor = Color.parseColor("#F0F0F0")
                foreground = context.getDrawable(attr(R.attr.selectableItemBackground))
                textView {
                    textResource = com.linroid.gassist.R.string.label_turn_on_plugin
                    textSize = 16f
                    gravity = Gravity.CENTER_VERTICAL
                }.lparams {
                    width = 0
                    weight = 1f
                    height = matchParent
                }
                switch {
                    id = com.linroid.gassist.R.id.btn_switch
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                }
            }.lparams {
                height = dip(56)
                width = matchParent
                gravity = Gravity.CENTER_VERTICAL
            }
            pluginDes = textView() {
                padding = dip(8)
            }.lparams {
                width = matchParent
            }
        }
    }.view
}