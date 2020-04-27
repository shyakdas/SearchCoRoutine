package com.search.coroutine.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.search.coroutine.R
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import java.net.SocketTimeoutException
import java.net.URI
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


object ErrorMessageHandler {
    private var message: String? = StringUtils.getSomeThingWrong()
    private fun initMessage() {
        message = StringUtils.getSomeThingWrong()
    }

    fun <T> getErrorForSuccessCall(errorResponse: Any?, call: Call<T>): String? {
        try {
            val errorMessage = getError(errorResponse)
            if (errorMessage != null) message =
                errorMessage as String? else initMessage()
        } catch (e: Exception) {
            initMessage()
        }
        return message
    }

    fun <T> getMessage(errorResponse: ResponseBody, call: Call<T>): String? {
        try {
            val responseStr = errorResponse.string()
            message = parseErrorResponse(responseStr)
        } catch (e: Exception) {
            initMessage()
        }
        return message
    }

    fun getMessage(errorResponse: ResponseBody): String? {
        try {
            val responseStr = errorResponse.string()
            message = parseErrorResponse(responseStr)
        } catch (e: Exception) {
            initMessage()
        }
        return message
    }

    fun <T> getNotAuthorizedError(responseBody: ResponseBody, call: Call<T>?): String? {
        try {
            val jsonObject = JSONObject(responseBody.string())
            if (jsonObject.optString("error_description").isNotEmpty()
            ) message =
                jsonObject.optString("error_description") else if (jsonObject.optString(
                    "error"
                ).isNotEmpty()
            ) message = jsonObject.optString("error")
        } catch (e: Exception) {
            initMessage()
        }
        return message
    }

    private fun parseErrorResponse(errorResponse: String): String? {
        if (errorResponse.isEmpty()) return message
        val gson = Gson()
        val errorRes = gson.fromJson(errorResponse, ErrorResponse::class.java)
        val error =
            errorRes.errors ?: errorRes.error
        val errorMessage = getError(error)
        if (errorMessage != null) message = errorMessage as String?
        return message
    }

    private fun getError(error: Any?): Any? {
        when (error) {
            null -> return null
            is String -> {
                return error
            }
            is List<*> -> {
                return if (error.size == 0) null else {
                    TextUtils.join(", ", error)
                }
            }
            is Map<*, *> -> {
                val entryMap =
                    error.entries.iterator().next()
                return getError(entryMap.value)
            }
            else -> return null
        }
    }

    fun <T> getExceptionError(throwable: Throwable?, call: Call<T>): String? {
        getExceptionMessage(throwable)
        return message
    }

    fun getExceptionMessage(throwable: Throwable?): String? {
        initMessage()
        if (throwable is IllegalArgumentException) {
            message =
                StringUtils.getString(R.string.json_field_error)
        } else if (throwable is JsonSyntaxException) {
            message =
                StringUtils.getString(R.string.json_parsing_error)
        } else if (throwable is SocketTimeoutException) {
            message = StringUtils.getTimeOutError()
        } else if (throwable is TimeoutException) {
            message = StringUtils.getTimeOutError()
        } else if (throwable is UnknownHostException) {
            message =
                StringUtils.getUnKnownHostError()
            if (NetworkChangeManager.notConnected()) message =
                StringUtils.getNoInternetConnection()
        }
        return message
    }

    private fun <T> getUrl(call: Call<T>?): String {
        if (call != null) {
            val uri: URI = call.request().url().uri()
            return uri.host + uri.path
        }
        return ""
    }

    private fun <T> getEndPoint(call: Call<T>?): String {
        if (call != null) {
            val uri: URI = call.request().url().uri()
            return uri.path
        }
        return ""
    }

    private fun trackAccessTokenAPI() {
        val path = "oauth/token"
    }

    private class ErrorResponse {
        val errors: Any? = null
        val error: Any? = null

    }
}