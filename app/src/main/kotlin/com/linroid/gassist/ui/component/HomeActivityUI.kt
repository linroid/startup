package com.linroid.gassist.ui.component

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.clans.fab.FloatingActionButton
import com.linroid.gassist.BuildConfig
import com.linroid.gassist.R
import com.linroid.gassist.attr
import com.linroid.gassist.floatingActionButton
import com.linroid.gassist.ui.PluginAdapter
import com.linroid.gassist.ui.widget.DividerItemDecoration
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * @author linroid <linroid@gmail.com>
 * @since 26/07/2017
 */
class HomeActivityUI : AnkoComponent<Activity> {
    lateinit var runBtn: FloatingActionButton

    override fun createView(ui: AnkoContext<Activity>) = ui.apply {
        relativeLayout {
            val header = relativeLayout() {
                id = View.generateViewId()
                backgroundResource = R.drawable.bg_home_header
                elevation = dip(16f).toFloat()
                val appName = textView(R.string.app_name) {
                    id = View.generateViewId()
                    textSize = 26f
                    typeface = Typeface.DEFAULT_BOLD
                    setShadowLayer(sp(2).toFloat(), sp(2).toFloat(), 0f, Color.WHITE)
                }.lparams {
                    centerInParent()
                }
                textView("v${BuildConfig.VERSION_NAME}") {
                    textSize = 12f
                }.lparams {
                    below(appName)
                    centerHorizontally()
                    topMargin = dip(16)
                }
            }.lparams {
                width = matchParent
                height = dip(240)
                alignParentTop()
            }

            recyclerView {
                adapter = PluginAdapter()
                layoutManager = LinearLayoutManager(ui.ctx, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                val dividerDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                dividerDecoration.padding = dip(16)
                val divider = context.getDrawable(attr(android.R.attr.listDivider)).mutate()
                dividerDecoration.setDrawable(divider)
                addItemDecoration(dividerDecoration)
            }.lparams {
                height = matchParent
                width = matchParent
                below(header)
                alignParentBottom()
            }

            runBtn = floatingActionButton {
                id = R.id.fab_run
                imageResource = R.drawable.ic_play_arrow
                elevation = dip(32f).toFloat()
//                colorNormal = Color.parseColor("#FF4CAF50")
            }.lparams {
                width = dip(64)
                height = dip(64)
                bottomOf(header)
                topMargin = dip(-35f)
                rightMargin = dip(8)
                alignParentRight()
            }
        }
    }.view
}