package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(com.google.android.gms.auth.api.R.drawable.common_full_open_on_phone, "Item " + i))
        }

        val apiInterface = ApiInterface.create().getMovies()

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<MoviesItem> {
            override fun onResponse(call: Call<MoviesItem>?, response: Response<MoviesItem>?) {

            //    val data = ArrayList<MoviesItem>()
                Log.d("MyLog", "OnResponse success ${response?.body()?.status} ")
                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(response?.body().)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

            }

            override fun onFailure(call: Call<MoviesItem>?, t: Throwable?) {
                Log.d("MyLog", "OnFailure ${t?.message} ")
            }
        })


   }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
        Log.d("MyLog", "Application been closed")
    }

}