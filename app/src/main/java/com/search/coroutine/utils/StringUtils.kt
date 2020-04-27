package com.search.coroutine.utils

import android.os.Build
import com.search.coroutine.R
import com.search.coroutine.SearchCoRoutineApplication


class StringUtils {
    companion object {
        fun getString(resId: Int): String? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                SearchCoRoutineApplication.getContext()?.getString(resId)
            } else SearchCoRoutineApplication.getContext()?.resources?.getString(resId)
        }

        fun getNoInternetConnection(): String? {
            return getString(R.string.no_internet_error)
        }

        fun getLoadingMessage(): String {
            return getString(R.string.loading)!!
        }

        fun getSomeThingWrong(): String {
            return getString(R.string.something_went_wrong)!!
        }

        fun getTimeOutError(): String {
            return getString(R.string.connection_time_out)!!
        }

        fun getUnKnownHostError(): String? {
            return getString(R.string.host_name_error)
        }
    }
}