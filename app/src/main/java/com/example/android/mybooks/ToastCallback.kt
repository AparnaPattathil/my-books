package com.example.android.mybooks

import android.app.Activity
import android.content.Context
import android.widget.Toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class ToastCallback(val context: Activity) : Callback {

    override fun onFailure(call: Call, e: IOException) {
        context.runOnUiThread {
            Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }

    }

    override fun onResponse(call: Call, response: Response) {
        context.runOnUiThread {
            Toast.makeText(context, "Response: ${response.body.toString()}", Toast.LENGTH_LONG).show()
        }

    }

}
