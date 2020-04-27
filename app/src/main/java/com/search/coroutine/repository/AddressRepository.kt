package com.search.coroutine.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.search.coroutine.base.BaseRepository
import com.search.coroutine.model.BaseResponse
import com.search.coroutine.network.ApiCallManager
import com.search.coroutine.network.ApiService
import com.search.coroutine.network.Resource
import com.search.coroutine.utils.ErrorMessageHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class AddressRepository(scope: CoroutineScope) : BaseRepository(scope) {

    fun fetchAddress(searchStr: String): LiveData<Resource<BaseResponse>> {
        return liveData(scope.coroutineContext + Dispatchers.IO) {
            var baseResponse: BaseResponse? = null
          //  emit(Resource.loading(StringUtils.getLoadingMessage(), baseResponse))
            try {
                val addressService = ApiCallManager.createService(ApiService::class.java)

                val addressRes = addressService.getAddressData(searchStr, "gurgaon").await()

                when (true) {
                    addressRes.isSuccessful && addressRes.body() != null -> {
                        baseResponse = addressRes.body()?.request_uuid?.let {
                            addressRes.body()?.data?.data?.let { it1 ->
                                BaseResponse(
                                    it1,
                                    it
                                )
                            }
                        }
                        emit(Resource.success(baseResponse!!))
                    }
                }
            } catch (e: Throwable) {
                emit(Resource.error(ErrorMessageHandler.getExceptionMessage(e), baseResponse, ""))
            }
        }
    }
}
