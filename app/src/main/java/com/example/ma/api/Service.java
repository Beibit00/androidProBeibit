package com.example.ma.api;

import com.example.ma.model.MoviesResponse;
import com.example.ma.model.ReviewResponse;
import com.example.ma.model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/popular")// get annotation что бы получить данные сервера
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);
 // interface Service зовем класс MoviesResponse он находится в моделе
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovieTrailer(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse>getMovieReviews(@Path("movie_id") int id, @Query("api_key") String apiKey);
}
