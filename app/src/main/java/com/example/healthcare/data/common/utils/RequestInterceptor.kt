package com.example.healthcare.data.common.utils

import android.content.Context
import com.example.healthcare.common.Prefs
import com.example.healthcare.common.checkInternet
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(private val prefs: Prefs, private val context: Context) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = prefs.getToken()
        request = if (checkInternet(context))
            request
                .newBuilder()
                .header("Cache-Control", "public, max-age=" + 5)
                .addHeader("hc-auth-tkn",token?:"")
                .build()
        else
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                .build()
        return chain.proceed(request)
    }
}