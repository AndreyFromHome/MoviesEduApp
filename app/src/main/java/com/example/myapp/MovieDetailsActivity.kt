package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.myapp.data.MoviesItemDetails
import com.example.myapp.model.apis.ApiInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var overView: TextView

    private lateinit var banner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val char_id = intent.getIntExtra("char_id", 0)
        Log.d("MyLog", "id $char_id")

        title = findViewById(R.id.movies_details_title)
        releaseDate = findViewById(R.id.movie_details_date)
        score = findViewById(R.id.movie_details_score)
        overView = findViewById(R.id.movie_details_body_overview)
        banner = findViewById(R.id.movie_details_image_banner)

        val apiInterface = char_id?.let { ApiInterface.create().getMovieDetails(it) }
        apiInterface?.enqueue( object : Callback<List<MoviesItemDetails>> {
            override fun onResponse(
                call: Call<List<MoviesItemDetails>>?,
                response: Response<List<MoviesItemDetails>>?
            ) {
                val responseList = response?.body()
                responseList?.forEach {
                    title.text = it.name
                    releaseDate.text = it.birthday
                    score.text = it.status
                    overView.text = it.nickname

                    Picasso.get().load(it.img).into(banner)
                }

            }

            override fun onFailure(call: Call<List<MoviesItemDetails>>, t: Throwable) {
                Log.d("MyLog", "OnFailure ${t?.message} ")
            }
        })
    }
}