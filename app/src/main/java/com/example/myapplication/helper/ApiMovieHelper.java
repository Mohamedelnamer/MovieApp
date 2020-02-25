package com.example.myapplication.helper;

import java.util.ArrayList;

import com.example.myapplication.model.MovieDetails;
import com.example.myapplication.model.Moviedata;

public interface ApiMovieHelper {

    void setMoviesData(ArrayList<Moviedata> Movies);
    void setMovieDetailsData(MovieDetails movieDetails);
}
