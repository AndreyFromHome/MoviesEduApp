package com.example.myapp.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var mTitle: TextView
    private lateinit var mReleaseDate: TextView
    private lateinit var mScore: TextView
    private lateinit var mOverview: TextView

    private lateinit var mBanner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val char_id = intent.getIntExtra("char_id", 0)
        // Log.d("MyLog", "id $char_id")
        initViews()
        initObservers()
        mViewModel.getMovieDetails(char_id)

    }

    private fun setMovieInformation(movieDetails: MovieDetails?) {
        mTitle.text = movieDetails?.name
        mReleaseDate = movieDetails?.birthday
        mScore = movieDetails?.status
        mOverview = movieDetails?.nickname

        Picasso.get().load(movieDetails.img).into(mBanner)
    }

    private fun initViews() {
        mTitle = findViewById(R.id.movies_details_title)
        mReleaseDate = findViewById(R.id.movie_details_date)
        mScore = findViewById(R.id.movie_details_score)
        mOverview = findViewById(R.id.movie_details_body_overview)
        mBanner = findViewById(R.id.movie_details_image_banner)
    }

}

/*
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
}*/
