package com.linroid.gassist.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.linroid.gassist.R
import com.linroid.gassist.bus.Bus
import com.linroid.gassist.bus.events.StatusChangedEvent
import com.linroid.gassist.plugins.PluginManager
import com.linroid.gassist.ui.component.HomeActivityUI

class HomeActivity : BaseActivity<HomeActivityUI>(HomeActivityUI()) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.runBtn.setOnClickListener {
            if (!PluginManager.isRunning()) {
                PluginManager.turnOn()
            } else {
                PluginManager.turnOff()
            }
        }
//        if (!Settings.System.canWrite(this)) {
//            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
//                    Uri.parse("package:" + packageName))
//            startActivityForResult(intent, 0x1)
//        }
        Bus.register(StatusChangedEvent::class).subscribe {
            ui.runBtn.setImageResource(if (it.running) R.drawable.ic_action_stop else R.drawable.ic_action_run)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
