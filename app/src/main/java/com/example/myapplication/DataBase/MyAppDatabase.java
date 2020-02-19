package com.example.myapplication.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.MoviesModel.Moviedata;

@Database(entities = {Moviedata.class},version = 1, exportSchema = false)

public abstract class MyAppDatabase extends RoomDatabase {

    public abstract MyDoa myDoa();
}
