package com.nagy.movieapp.common.data.api.interceptors

import com.nagy.movieapp.common.data.api.ApiConstants
import com.nagy.movieapp.common.data.api.ApiParameters
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter(ApiParameters.API_KEY, ApiConstants.API_KEY)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}