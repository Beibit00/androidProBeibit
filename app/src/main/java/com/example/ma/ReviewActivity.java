package com.example.ma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ma.adapter.ReviewAdapter;
import com.example.ma.adapter.TrailerAdapter;
import com.example.ma.api.Client;
import com.example.ma.api.Service;
import com.example.ma.model.Movie;
import com.example.ma.model.Review;
import com.example.ma.model.ReviewResponse;
import com.example.ma.model.Trailer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView author,  content, url;
    private ReviewAdapter adapter;
    private List<Review> reviewList;

    int movie_id;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        author = (TextView) findViewById(R.id.author);
        content = (TextView) findViewById(R.id.content);
        url = (TextView) findViewById(R.id.url);
        initViews();

    }

    private void initViews(){
        reviewList = new ArrayList<>(); // иницилизировал reviewlist
        adapter = new ReviewAdapter(this, reviewList); // иницилизировал adapter

        recyclerView = (RecyclerView) findViewById(R.id.recycler_review);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        loadJSONReview();

    }
    private void loadJSONReview(){

        try{
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please obtain your API Key from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }
            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<ReviewResponse> call = apiService.getMovieReviews(movie_id, BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<ReviewResponse>() {
                @Override
                public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                    List<Review> reviews = response.body().getResults();
                    recyclerView.setAdapter(new ReviewAdapter(getApplicationContext(), reviews));
                    recyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<ReviewResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(ReviewActivity.this, "Error fetching review data", Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
