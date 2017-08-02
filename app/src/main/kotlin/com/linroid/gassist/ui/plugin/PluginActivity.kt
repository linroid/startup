package com.linroid.gassist.ui.plugin

import android.os.Bundle
import com.linroid.gassist.EXTRA_PLUGIN
import com.linroid.gassist.plugins.Plugin
import com.linroid.gassist.plugins.PluginManager
import com.linroid.gassist.ui.BaseActivity
import com.linroid.gassist.ui.component.PluginActivityUI
import org.jetbrains.anko.debug

class PluginActivity : BaseActivity<PluginActivityUI>(PluginActivityUI()) {
    private lateinit var plugin: Plugin<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        val id = intent.getIntExtra(EXTRA_PLUGIN, -1);
        plugin = PluginManager.find(id)
        debug("load plugin $plugin")
        this.title = plugin.name()
        super.onCreate(savedInstanceState)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        ui.pluginDes.text = plugin.description()
        ui.enableSwitch.isEnabled = plugin.enabled
        ui.enableSwitch.setOnClickListener {
            plugin.switch()
        }
    }
}
