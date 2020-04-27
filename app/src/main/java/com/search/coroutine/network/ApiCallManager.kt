package com.search.coroutine.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.search.coroutine.utils.SearchConstants.Companion.TIME_OUT_DURATION
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiCallManager {

    companion object {

        private val clientBuilder = OkHttpClient.Builder()
            .readTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
        private val okHttpClient = clientBuilder.build()
        private val retroBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .client(okHttpClient)

        /**
         * This method gets used to create an instance of given service class
         *
         * @param service Generic service class
         * @param <T> Type of Service class
        </T> */
        fun <T> createService(service: Class<T>): T {
            val retrofit = retroBuilder
                .baseUrl("https://digi-api.airtel.in/")
                .build()
            return retrofit.create(service)
        }

        fun cancelAllCall() {
            okHttpClient.dispatcher().cancelAll()
        }
    }
}