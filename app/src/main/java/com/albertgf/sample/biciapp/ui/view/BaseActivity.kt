package com.albertgf.sample.biciapp.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.albertgf.sample.biciapp.BiciApp
import com.albertgf.sample.biciapp.domain.common.DomainError

abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId)
    }

    open fun preparePresenter(intent: Intent?) { }

    open fun showError(error: DomainError) = Toast.makeText(this, error.toString(), Toast.LENGTH_LONG)

    val Activity.app: BiciApp get() = application as BiciApp
}