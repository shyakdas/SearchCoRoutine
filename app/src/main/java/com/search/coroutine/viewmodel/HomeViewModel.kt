package com.search.coroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.search.coroutine.model.Address
import com.search.coroutine.network.NetworkStatus
import com.search.coroutine.repository.AddressRepository

class HomeViewModel : ViewModel() {
    private val videoConferencingRepo: AddressRepository = AddressRepository(viewModelScope)
    val mutableAddressData = MutableLiveData<List<Address>>()
    var mutableError = MutableLiveData<String>()

    fun fetchAddress(string: String) {
        videoConferencingRepo.fetchAddress(searchStr = string).observeForever {
            when (it.status) {
                NetworkStatus.SUCCESS -> {
                    mutableAddressData.value = it.data?.data?.addressList
                }
                NetworkStatus.ERROR -> {
                    mutableError.value = it.message
                }
                else -> mutableError.value = it.message
            }
        }
    }
}