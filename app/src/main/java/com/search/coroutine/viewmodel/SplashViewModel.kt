package com.search.coroutine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.search.coroutine.utils.SearchConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class SplashViewModel : ViewModel() {

    var splashData = liveData(Dispatchers.IO) {
        delay(SearchConstants.SPLASH_TIME)
        emit(true)
    }
}