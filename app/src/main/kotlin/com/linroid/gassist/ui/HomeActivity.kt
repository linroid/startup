package com.linroid.gassist.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.linroid.gassist.R
import com.linroid.gassist.plugins.PluginManager
import com.linroid.gassist.service.GameAssistService
import com.linroid.gassist.ui.component.HomeActivityUI
import org.jetbrains.anko.toast

class HomeActivity : BaseActivity<HomeActivityUI>(HomeActivityUI()) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.runBtn.setOnClickListener {
            if (!PluginManager.isRunning()) {
                if (!GameAssistService.isAccessibilitySettingsOn(this)) {
                    navToAccessibilitySettings()
                    return@setOnClickListener
                }
                PluginManager.turnOn()
            } else {
                PluginManager.turnOff()
            }
        }
    }

    private fun navToAccessibilitySettings() {
        toast("// todo > 开启辅助服务")
        startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
