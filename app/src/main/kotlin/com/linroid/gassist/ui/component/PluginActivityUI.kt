package com.linroid.gassist.ui.component

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import com.linroid.gassist.R
import com.linroid.gassist.attr
import org.jetbrains.anko.*

/**
 * @author linroid <linroid@gmail.com>
 * @since 27/07/2017
 */
class PluginActivityUI : AnkoComponent<Activity> {
    lateinit var pluginDes: TextView
    lateinit var enableSwitch: Switch

    override fun createView(ui: AnkoContext<Activity>) = ui.apply {
        verticalLayout {
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                horizontalPadding = dip(16)
                backgroundColor = Color.parseColor("#F0F0F0")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    foreground = context.getDrawable(attr(android.R.attr.selectableItemBackground))
                }
                textView {
                    textResource = com.linroid.gassist.R.string.label_turn_on_plugin
                    textSize = 16f
                    gravity = Gravity.CENTER_VERTICAL
                }.lparams {
                    width = 0
                    weight = 1f
                    height = matchParent
                }
                enableSwitch = switch {
                    id = R.id.btn_switch
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