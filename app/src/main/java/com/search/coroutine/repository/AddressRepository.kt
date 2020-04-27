package com.search.coroutine.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.search.coroutine.base.BaseRepository
import com.search.coroutine.model.BaseResponse
import com.search.coroutine.network.ApiCallManager
import com.search.coroutine.network.Resource
import com.search.coroutine.utils.ErrorMessageHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class AddressRepository(scope: CoroutineScope) : BaseRepository(scope) {

    fun fetchAddress(searchStr: String): LiveData<Resource<BaseResponse>> {
        return liveData(scope.coroutineContext + Dispatchers.IO) {
            delay(500)
            var baseResponse: BaseResponse? = null
            try {
                val addressService = ApiCallManager.api

                val addressRes = addressService.getAddressData(searchStr, "gurgaon").await()

                when (true) {
                    addressRes.isSuccessful && addressRes.body() != null -> {
                        baseResponse = addressRes.body()
                        emit(Resource.success(baseResponse!!))
                    }
                }
            } catch (e: Throwable) {
                emit(Resource.error(ErrorMessageHandler.getExceptionMessage(e), baseResponse, ""))
            }
        }
    }
}
