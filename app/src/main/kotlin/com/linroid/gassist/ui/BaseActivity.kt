package com.linroid.gassist.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import com.trello.rxlifecycle2.components.RxActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.setContentView

/**
 * @author linroid <linroid@gmail.com>
 * @since 17/07/2017
 */
@SuppressLint("Registered")
abstract class BaseActivity<out T : AnkoComponent<Activity>>(val component: T) : RxActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.setContentView(this)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}