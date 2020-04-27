package com.search.coroutine.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("data")
    val data: AddressListData,
    @SerializedName("requestId")
    val requestId: String
)