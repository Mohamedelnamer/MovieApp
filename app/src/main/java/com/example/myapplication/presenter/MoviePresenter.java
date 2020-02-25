package com.example.myapplication.presenter;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.util.ArrayList;

import com.example.myapplication.DataBase.MyAppDatabase;
import com.example.myapplication.MoviesModel.Moviedata;
import com.example.myapplication.MoviesModel.ResultsMovies;
import com.example.myapplication.Api.clients.ApiService;
import com.example.myapplication.helper.ApiMovieHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MoviePresenter {

    private ApiMovieHelper ApiMovieHelper;
    int x;
    private ApiService apiService = ApiService.GetInstance();


    public MoviePresenter(ApiMovieHelper view) {
        this.ApiMovieHelper = view;
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
                        ApiMovieHelper.SetMoviesData(movieslist);

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
    public void getFavouriteMovies(Context context){

        MyAppDatabase myAppDatabase;
        myAppDatabase= Room.databaseBuilder(context,MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();
        ApiMovieHelper.SetMoviesData(new ArrayList<Moviedata>(myAppDatabase.myDoa().getMovies()));


    }


}
