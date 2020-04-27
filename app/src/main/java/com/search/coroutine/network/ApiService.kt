package com.search.coroutine.network

import com.search.coroutine.model.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("compassLocation/rest/address/autocomplete")
    fun getAddressData(
        @Query("queryString") address: String,
        @Query("city") city: String
    ): Deferred<Response<ApiResponse<BaseResponse>>>
}