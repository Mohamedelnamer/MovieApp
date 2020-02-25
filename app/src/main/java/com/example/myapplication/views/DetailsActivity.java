package com.example.myapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.database.MyAppDatabase;
import com.example.myapplication.helper.ApiMovieHelper;
import com.example.myapplication.model.MovieDetails;
import com.example.myapplication.model.Moviedata;
import com.example.myapplication.R;
import com.example.myapplication.presenter.ApiPresenter;
import com.example.myapplication.utills.AppConstants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements ApiMovieHelper {
    public static MyAppDatabase myAppDatabase;
    ApiPresenter apiPresenter;
    Moviedata moviedata;
    TextView movieTitle,movieCategoty,movieStatue,movieOverView,movieRunTime,movieBudget,movieAdult;
    ImageView moviePoster,movieBackground,movieStatueImg;
    RatingBar movieRate;
    Toolbar toolbar;
    FloatingActionButton fav_Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        movieTitle=findViewById(R.id.movie_title);
        movieCategoty=findViewById(R.id.movie_categories);
        movieStatue=findViewById(R.id.movie_status);
        movieOverView=findViewById(R.id.movie_overview);
        movieRunTime=findViewById(R.id.movie_run_time);
        movieBudget=findViewById(R.id.movie_budget);
        movieAdult=findViewById(R.id.movie_adult);
        moviePoster=findViewById(R.id.movie_poster);
        movieBackground=findViewById(R.id.movie_background);
        movieRate=findViewById(R.id.movie_rating);
        movieStatueImg=findViewById(R.id.movie_status_image);
        toolbar = findViewById(R.id.toolbar);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();
        fav_Btn=findViewById(R.id.fab_favorite);

        apiPresenter=new ApiPresenter( this);

        moviedata= (Moviedata) getIntent().getSerializableExtra("MyClass");

        fav_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAppDatabase.myDoa().addMovie(moviedata);
                fav_Btn.setImageResource(R.drawable.ic_favorite);
                Toast.makeText(getApplicationContext(),"Add To Your Favourite ",Toast.LENGTH_LONG).show();


            }
        });

        callPresenter();

    }

    private void callPresenter() {
        apiPresenter.getMovieDetails(Integer.valueOf(moviedata.getId()));

    }

    @Override
    public void setMoviesData(ArrayList<Moviedata> Movies) {

    }

    @Override
    public void setMovieDetailsData(MovieDetails movieDetails) {
        loadImage(movieDetails);
        setMovieCategoty(movieDetails);
        setActionBar(movieDetails);
        setMovieAdult(movieDetails);
        setMovieStatueImage(movieDetails);
        setMovieTexts(movieDetails);
    }

    public void loadImage(MovieDetails movieDetails){
        Picasso.get().load(AppConstants.imageUrl+movieDetails.getPoster_path()).into(moviePoster);
        Picasso.get().load(AppConstants.imageUrl+movieDetails.getBackdrop_path()).into(movieBackground);

    }
    public void setMovieCategoty(MovieDetails movieDetails){

        for(int i=0;i<movieDetails.getGenres().size();i++){
            if(movieCategoty.getText()!=""){
                movieCategoty.setText(movieCategoty.getText()+","+movieDetails.getGenres().get(i).getName());}
            else{
                movieCategoty.setText(movieDetails.getGenres().get(i).getName());}

        }
    }
    public void setActionBar(MovieDetails movieDetails){

        toolbar.setTitle(movieDetails.getTitle());
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void setMovieAdult(MovieDetails movieDetails){
        if(movieDetails.isAdult()){movieAdult.setText("Yes");} else{movieAdult.setText("No");}

    }
    public void setMovieStatueImage(MovieDetails movieDetails){

        if(movieDetails.getStatus().equals("Released")){movieStatueImg.setImageResource(R.drawable.ic_released);}
        else{movieStatueImg.setImageResource(R.drawable.ic_un_released);}

    }
    public void setMovieTexts(MovieDetails movieDetails){
        movieTitle.setText(movieDetails.getTitle());
        movieBudget.setText(String.valueOf(movieDetails.getBudget()));
        movieStatue.setText(movieDetails.getStatus());
        movieOverView.setText(movieDetails.getOverview());
        movieRunTime.setText(String.valueOf(movieDetails.getRuntime()));
        movieRate.setRating( movieDetails.getVote_average().floatValue()/2);

    }

}
