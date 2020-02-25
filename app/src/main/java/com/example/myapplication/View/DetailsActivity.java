package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.MyAppDatabase;
import com.example.myapplication.MoviesModel.MovieDetails;
import com.example.myapplication.MoviesModel.Moviedata;
import com.example.myapplication.R;
import com.example.myapplication.helper.ApiMovieDetailsHelper;
import com.example.myapplication.presenter.MovieDetailsPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements ApiMovieDetailsHelper {
    public static MyAppDatabase myAppDatabase;
    MovieDetailsPresenter movieDetailsPresenter;
    Moviedata moviedata;
    TextView MovieTitle,MovieCategoty,MovieStatue,MovieOverView,MovieRunTime,MovieBudget,MovieAdult;
    ImageView MoviePoster,MovieBackground,MovieStatueImg;
    RatingBar MovieRate;
    Toolbar toolbar;
    FloatingActionButton Fav_Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //todo
        MovieTitle=(TextView)findViewById(R.id.movie_title);
        MovieCategoty=(TextView)findViewById(R.id.movie_categories);
        MovieStatue=(TextView)findViewById(R.id.movie_status);
        MovieOverView=(TextView)findViewById(R.id.movie_overview);
        MovieRunTime=(TextView)findViewById(R.id.movie_run_time);
        MovieBudget=(TextView)findViewById(R.id.movie_budget);
        MovieAdult=(TextView)findViewById(R.id.movie_adult);
        MoviePoster=(ImageView)findViewById(R.id.movie_poster);
        MovieBackground=(ImageView)findViewById(R.id.movie_background);
        MovieRate=(RatingBar)findViewById(R.id.movie_rating);
        MovieStatueImg=(ImageView)findViewById(R.id.movie_status_image);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();
        Fav_Btn=(FloatingActionButton)findViewById(R.id.fab_favorite);

        movieDetailsPresenter=new MovieDetailsPresenter(this);
        moviedata= (Moviedata) getIntent().getSerializableExtra("MyClass");

        Fav_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAppDatabase.myDoa().addMovie(moviedata);
                Fav_Btn.setImageResource(R.drawable.ic_favorite);
                Toast.makeText(getApplicationContext(),"Add To Your Favourite ",Toast.LENGTH_LONG).show();


            }
        });

        callPresenter();

    }

    private void callPresenter() {
        movieDetailsPresenter.getMovieDetails(Integer.valueOf(moviedata.getId()));

    }

//todo
    @Override
    public void SetMovieDetailsData(MovieDetails Movie_Details) {

        toolbar.setTitle(Movie_Details.getTitle());
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MovieTitle.setText(Movie_Details.getTitle());
        MovieBudget.setText(String.valueOf(Movie_Details.getBudget()));
        for(int i=0;i<Movie_Details.getGenres().size();i++){
            if(MovieCategoty.getText()!=""){
            MovieCategoty.setText(MovieCategoty.getText()+","+Movie_Details.getGenres().get(i).getName());}
            else{
                MovieCategoty.setText(Movie_Details.getGenres().get(i).getName());}

        }

        MovieStatue.setText(Movie_Details.getStatus());
        MovieOverView.setText(Movie_Details.getOverview());
        MovieRunTime.setText(String.valueOf(Movie_Details.getRuntime()));
        if(Movie_Details.isAdult()){MovieAdult.setText("Yes");} else{MovieAdult.setText("No");}
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+Movie_Details.getPoster_path()).into(MoviePoster);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+Movie_Details.getBackdrop_path()).into(MovieBackground);
        MovieRate.setRating( Movie_Details.getVote_average().floatValue()/2);
        if(Movie_Details.getStatus().equals("Released")){MovieStatueImg.setImageResource(R.drawable.ic_released);}
        else{MovieStatueImg.setImageResource(R.drawable.ic_un_released);}


    }
}
