package com.search.coroutine.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.search.coroutine.SearchCoRoutineApplication


object NetworkChangeManager {
    fun isConnected(): Boolean {
        val isConnected: Boolean
        val cm = SearchCoRoutineApplication.getContext()
            ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo?
        activeNetwork = cm.activeNetworkInfo
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
        return isConnected
    }

    fun notConnected(): Boolean {
        return !isConnected()
    }
}