package com.example.android.mybooks

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), Callback {

    private lateinit var container:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        container = findViewById(R.id.linearLayout)

        button_click_me.setOnClickListener {
            loading.visibility = View.VISIBLE
//            bookTitle.visibility = View.VISIBLE

            val url =
                "https://api.nytimes.com/svc/books/v3/lists/2019-09-01/combined-print-and-e-book-fiction.json?api-key=3GiIylwhoiL0USYYl7IIMDwjqe5FNtqR"

            val request = OkHttpRequest()
            request.requestBooks(url, this)

        }
    }

    private fun fetchComplete(json: JSONObject) {
        Log.d("Books", json.toString())
    }

    override fun onResponse(call: Call, response: Response) {
        runOnUiThread {
            loading.visibility = View.GONE
//            list.visibility = View.VISIBLE
//            bookTitle.visibility = View.VISIBLE
button_click_me.visibility = View.GONE
//            Log.d("moshi", moshi.)
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()


//            jsonString = adapter.toJson(book)
            response.body?.let {
                    val jsonObject = JSONObject(it.string())
//                Log.d("results", jsonObject.toString())

                var jsonString = jsonObject.toString()
                val adapter = moshi.adapter(NetworkResult::class.java)
                val result = adapter.fromJson(jsonString)
//Log.d("book",book.toString())
                result?.results?.books?.forEach{
                   addTextView( it.title)
                }
                }

//            }
        }
    }

    override fun onFailure(call: Call, e: IOException) {
        runOnUiThread {
            loading.visibility = View.GONE
        }
    }
    private fun addTextView(label: String) {
        val view = TextView(this)
        view.text = label
        view.textSize = 28f
        view.setTextColor(Color.parseColor("#ff0000"))
        linearLayout.addView(view)
    }

}
