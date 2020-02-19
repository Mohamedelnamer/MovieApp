package com.example.myapplication.presenter;

import com.example.myapplication.Api.clients.ApiService;
import com.example.myapplication.MoviesModel.MovieDetails;
import com.example.myapplication.MoviesModel.Moviedata;
import com.example.myapplication.MoviesModel.ResultsMovies;
import com.example.myapplication.helper.ApiMovieDetailsHelper;
import com.example.myapplication.helper.ApiMovieHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsPresenter {
    private ApiMovieDetailsHelper apiMovieDetailsHelper;
    public MovieDetailsPresenter(ApiMovieDetailsHelper view) {
        this.apiMovieDetailsHelper = view;
    }

    public void getMovieDetails(int id){


        ApiService
                .getAPI()
                .getMovieDetails(id,ApiService.key,ApiService.LANGUAGE)
                .enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                        MovieDetails data = response.body();
                        apiMovieDetailsHelper.SetMovieDetailsData(data);


                    }

                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                    }
                });




    }



}
