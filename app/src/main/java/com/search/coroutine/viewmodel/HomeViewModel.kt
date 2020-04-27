package com.search.coroutine.viewmodel

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.search.coroutine.network.NetworkStatus
import com.search.coroutine.repository.AddressRepository

class HomeViewModel : ViewModel() {
    private val videoConferencingRepo: AddressRepository = AddressRepository(viewModelScope)

    fun fetchAddress(string: String) {
        videoConferencingRepo.fetchAddress(searchStr = string).observeForever(Observer {
            when (it.status) {
                NetworkStatus.SUCCESS -> {
                    Log.e("TAG", "data ${it.data?.data}")
                }
            }
        })
    }
}