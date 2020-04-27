package com.search.coroutine

import android.app.Application
import android.content.Context

class SearchCoRoutineApplication : Application() {

    companion object {
        var isInApplication = false
        private var mContext: Context? = null

        fun getContext(): Context? {
            return mContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext;
    }
}