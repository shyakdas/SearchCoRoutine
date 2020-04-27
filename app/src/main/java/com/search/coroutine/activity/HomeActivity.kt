package com.search.coroutine.activity

import androidx.lifecycle.ViewModelProvider
import com.search.coroutine.R
import com.search.coroutine.base.BaseActivity
import com.search.coroutine.viewmodel.HomeViewModel

class HomeActivity : BaseActivity() {
    private lateinit var homeViewModel: HomeViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initSearch()
    }

    override fun initViewModel() {
        homeViewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory).get(HomeViewModel::class.java)
    }

    private fun initSearch() {
        homeViewModel.fetchAddress("Bharti")
    }
}