package com.linroid.gassist.ui

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.linroid.gassist.EXTRA_PLUGIN
import com.linroid.gassist.R
import com.linroid.gassist.attr
import com.linroid.gassist.plugins.Plugin
import com.linroid.gassist.plugins.PluginManager
import com.linroid.gassist.ui.plugin.PluginActivity
import org.jetbrains.anko.*

/**
 * @author linroid <linroid@gmail.com>
 * @since 18/07/2017
 */
class PluginAdapter : RecyclerView.Adapter<PluginAdapter.PluginViewHolder>() {
    override fun getItemCount(): Int = PluginManager.plugins.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PluginViewHolder {
        return PluginViewHolder(PluginItemUI().createView(AnkoContext.Companion.create(parent.context, parent, false)))
    }

    override fun onBindViewHolder(holder: PluginViewHolder, position: Int) {
        val plugin = PluginManager.plugins[position]
        holder.bind(plugin)
    }

    class PluginItemUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            verticalLayout {
                lparams(width = matchParent, height = dip(64))
                backgroundResource = attr(android.R.attr.selectableItemBackground);
                horizontalPadding = dip(16)
                textView {
                    id = R.id.title
                    gravity = Gravity.BOTTOM
                    bottomPadding = dip(2)
                    setTextAppearance(context, R.style.TextAppearance_Title)
                    textSize = 16f
                    textColor = Color.BLACK
                }.lparams {
                    weight = 1f
                    height = 0
                    width = matchParent
                }
                textView {
                    id = R.id.subtitle
                    gravity = Gravity.TOP
                    topPadding = dip(2)
                    text = "已启用"
                    textSize = 10f
                    setTextAppearance(context, R.style.TextAppearance_SubTitle)
                }.lparams {
                    weight = 1f
                    height = 0
                    width = wrapContent
                }

            }
        }
    }

    class PluginViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val titleView by lazy {
            this.itemView.find<TextView>(R.id.title)
        }

        fun bind(plugin: Plugin<*>) {
            titleView.text = plugin.name()
            itemView.setOnClickListener {
                val intent = Intent(titleView.context, PluginActivity::class.java)
                intent.putExtra(EXTRA_PLUGIN, plugin.id)
                titleView.context.startActivity(intent)
            }
        }
    }
}
