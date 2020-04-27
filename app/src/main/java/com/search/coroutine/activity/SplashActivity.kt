package com.search.coroutine.activity

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.search.coroutine.R
import com.search.coroutine.base.BaseActivity
import com.search.coroutine.viewmodel.SplashViewModel

class SplashActivity : BaseActivity() {
    private lateinit var splashViewModel: SplashViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        initSplash()
    }

    override fun initViewModel() {
        splashViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(SplashViewModel::class.java)
    }

    private fun initSplash() {
        splashViewModel.splashData.observe(this, Observer {
            run {
                navigateToHome()
            }
        })
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
