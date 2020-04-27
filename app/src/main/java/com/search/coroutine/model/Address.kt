package com.search.coroutine.model

data class Address(
    val addressString: String,
    val city: String,
    val id: String,
    val label: String,
    val latitude: Double,
    val longitude: Double,
    val pinCode: String
)