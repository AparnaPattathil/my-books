package com.example.android.mybooks

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
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
//            list.visibility = View.VISIBLE
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


//            call.execute().use { response ->
                response.body?.let {
                    val jsonObject = JSONObject(it.string())
                    val results = jsonObject.getJSONObject("results")
                    val books = results.getJSONArray("books")

                    for (book in 0 until books.length()){
                        val bookItem =books.getJSONObject(book)
                        val title = bookItem.getString("title")
                        val rank = bookItem.getString("rank")
                        Log.d("rank",bookItem.toString())
                        addTextView("$rank | $title")
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

