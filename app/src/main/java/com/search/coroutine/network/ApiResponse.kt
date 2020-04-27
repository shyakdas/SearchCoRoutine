package com.search.coroutine.network

class ApiResponse<T> {
    val status: Any? = null
        get() {
            if (field is String) return Integer.valueOf((field as String?)!!) else if (field is Double) {
                return field.toInt()
            }
            return field as Int
        }
    val message: String? = null
    val errors: Any? = null
    val data: T? = null
    val request_uuid: String? = null
}