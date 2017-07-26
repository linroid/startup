package com.linroid.gassist.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.linroid.gassist.R
import com.linroid.gassist.ui.component.HomeActivityUI

class HomeActivity : BaseActivity<HomeActivityUI>(HomeActivityUI()) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
