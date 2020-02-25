package com.example.myapplication.presenter;

import java.util.ArrayList;

import com.example.myapplication.model.MovieDetails;
import com.example.myapplication.model.Moviedata;
import com.example.myapplication.model.ResultsMovies;
import com.example.myapplication.Api.clients.ApiService;
import com.example.myapplication.helper.ApiMovieHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiPresenter {

    private ApiService apiService = ApiService.GetInstance();

    private ApiMovieHelper apiMovieHelper;


    public ApiPresenter(ApiMovieHelper view) {
        this.apiMovieHelper = view;
    }

    public void getMovies(String category,int page) {

        apiService
                .getAPI()
                .getMovies(category,ApiService.key,ApiService.LANGUAGE,page)
                .enqueue(new Callback<ResultsMovies>() {
                    @Override
                    public void onResponse(Call<ResultsMovies> call, Response<ResultsMovies> response) {

                        ResultsMovies data = response.body();
                        ArrayList<Moviedata> movieslist=new ArrayList<>();
                        for(Moviedata m:data.getResults()){

                            movieslist.add(m);
                        }
                        apiMovieHelper.setMoviesData(movieslist);

                    }

                    @Override
                    public void onFailure(Call<ResultsMovies> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println("nnoooooooooo");

                        }
                    }
                });
    }
    public  void getMovieDetails(int id){


        ApiService
                .getAPI()
                .getMovieDetails(id,ApiService.key,ApiService.LANGUAGE)
                .enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                        MovieDetails data = response.body();
                        apiMovieHelper.setMovieDetailsData(data);
                    }

                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                    }
                });




    }



}
