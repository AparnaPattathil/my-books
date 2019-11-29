package com.example.android.mybooks

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class LoggingCallback: Callback {
    override fun onFailure(call: Call, e: IOException) {
        Log.d("Callback", "failed: ${e.localizedMessage}")
    }

    override fun onResponse(call: Call, response: Response) {
        Log.d("Callback", "successful: ${response.body.toString()}")
    }

}
