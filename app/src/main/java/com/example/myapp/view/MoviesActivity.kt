package com.example.myapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.*
import com.example.myapp.data.MoviesItem
import com.example.myapp.model.apis.ApiInterface
import com.example.myapp.view.adapters.CustomAdapter
import com.example.myapp.viewmodel.ItemsViewModel
import com.example.myapp.viewmodel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesActivity : AppCompatActivity() {

    private lateinit var mVievModel : MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this,2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        mVievModel = MoviesViewModel()
        val result = mVievModel.getMovies()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(com.google.android.gms.auth.api.R.drawable.common_full_open_on_phone, "Item " + i))
        }

        val apiInterface = ApiInterface.create().getMovies()

        apiInterface.enqueue( object : Callback<List<MoviesItem>>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<List<MoviesItem>>, response: Response<List<MoviesItem>>) {

                Log.d("MyLog", "OnResponse success ${response?.body()} ")
                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(response?.body(), this)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

            }

            override fun onFailure(call: Call<List<MoviesItem>>?, t: Throwable?) {
                Log.d("MyLog", "OnFailure ${t?.message} ")
            }

            override fun onItemClick(char_id: Int) {
                val intent = Intent(this@MoviesActivity, MovieDetailsActivity::class.java)
                intent.putExtra("char_id", char_id)
                startActivity(intent)
/*                Toast.makeText(this@MoviesActivity, "click $position", Toast.LENGTH_SHORT).show()
                try {
                    val notify: Uri =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val r = RingtoneManager.getRingtone(applicationContext, notify)
                    r.play()
                } catch (e: Exception) {
                    e.printStackTrace()
                }*/
            }
        })
   }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
        Log.d("MyLog", "Application been closed")
    }

}