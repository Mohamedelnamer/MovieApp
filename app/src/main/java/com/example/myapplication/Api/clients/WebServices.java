package com.example.myapplication.Api.clients;

import com.example.myapplication.model.MovieDetails;
import com.example.myapplication.model.ResultsMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServices {
    @GET("/3/movie/{category}")
    Call<ResultsMovies> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
    @GET("/3/movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

}
