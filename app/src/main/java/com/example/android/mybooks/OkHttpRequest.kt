package com.example.android.mybooks

import okhttp3.*

class OkHttpRequest {
    var client = OkHttpClient()

    fun requestBooks(url: String, callback: Callback): Call {
        val request = Request.Builder()
            .url(url)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }


}
