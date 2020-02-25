package com.example.myapplication.presenter;

import android.content.Context;

import androidx.room.Room;

import com.example.myapplication.database.MyAppDatabase;
import com.example.myapplication.helper.ApiMovieHelper;
import com.example.myapplication.helper.DatabaseMovieHelper;
import com.example.myapplication.model.Moviedata;
import java.util.ArrayList;
public class DatabasePresenter {
    DatabaseMovieHelper databaseMovieHelper;
    ApiMovieHelper apiMovieHelper;

    public DatabasePresenter(DatabaseMovieHelper view) {
        this.databaseMovieHelper = view;
    }


    public  void getFavouriteMovies(Context context){

        MyAppDatabase myAppDatabase;
        myAppDatabase= Room.databaseBuilder(context,MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();
        databaseMovieHelper.setFavouriteMovie(new ArrayList<Moviedata>(myAppDatabase.myDoa().getMovies()));


    }



}
