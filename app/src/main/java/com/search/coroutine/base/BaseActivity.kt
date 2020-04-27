package com.search.coroutine.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViewModel()
        initView()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initViewModel()
}