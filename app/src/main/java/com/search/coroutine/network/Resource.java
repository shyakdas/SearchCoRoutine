package com.search.coroutine.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.search.coroutine.R;
import com.search.coroutine.utils.StringUtils;

public class Resource<T> {
    @NonNull
    public final NetworkStatus status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;
    public final String xRequestId;

    private Resource(@NonNull NetworkStatus status, @Nullable T data, @Nullable String message, String xRequestId) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.xRequestId = xRequestId;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(NetworkStatus.SUCCESS, data, StringUtils.Companion.getString(R.string.request_successful), "");
    }

    public static <T> Resource<T> error(String msg, @Nullable T data, String xRequestId) {
        return new Resource<>(NetworkStatus.ERROR, data, msg, xRequestId);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(NetworkStatus.ERROR, data, msg, "");
    }

    public static <T> Resource<T> error(String msg) {
        return error(msg, null);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return loading(StringUtils.Companion.getString(R.string.loading), data);
    }

    public static <T> Resource<T> loading(String message, T data) {
        return new Resource<>(NetworkStatus.LOADING, data, message, "");
    }

    public static <T> Resource<T> noInternet() {
        return new Resource<>(NetworkStatus.NO_INTERNET, null, StringUtils.Companion.getNoInternetConnection(), "");
    }
}