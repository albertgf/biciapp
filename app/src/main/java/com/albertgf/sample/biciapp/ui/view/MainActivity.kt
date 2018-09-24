package com.albertgf.sample.biciapp.ui.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.albertgf.sample.biciapp.BiciApp
import com.albertgf.sample.biciapp.R
import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.stations.StationMinimalDomain
import com.albertgf.sample.biciapp.ui.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainPresenter.View {
    override val layoutId: Int = R.layout.activity_main

    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BiciApp.component.inject(this)
    }

    override fun onStart() {
        super.onStart()

        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.update()
    }

    override fun onStop() {
        super.onStop()

        presenter.deattachView()
    }

    override fun hideLoadind() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDomainError(error: DomainError) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptyCase() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showStations(list: List<StationMinimalDomain>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
