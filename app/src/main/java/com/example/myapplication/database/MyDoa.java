package com.example.myapplication.database;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.Moviedata;

import java.util.List;


@Dao
public interface MyDoa {


    @Insert
    public void addMovie(Moviedata moviedata);

    @Query("select *from Movies")
    public List<Moviedata> getMovies();

    @Delete
    public void deleteMovie(Moviedata moviedata);


}
