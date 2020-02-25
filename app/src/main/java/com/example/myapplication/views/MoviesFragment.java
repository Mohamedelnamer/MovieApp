package com.example.myapplication.views;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.myapplication.adapter.HomeRecycleViewAdapter;
import com.example.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.myapplication.helper.DatabaseMovieHelper;
import com.example.myapplication.model.MovieDetails;
import com.example.myapplication.model.Moviedata;

import com.example.myapplication.adapter.MovieItemClickListener;
import com.example.myapplication.helper.ApiMovieHelper;
import com.example.myapplication.presenter.ApiPresenter;
import com.example.myapplication.presenter.DatabasePresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class    MoviesFragment extends Fragment implements DatabaseMovieHelper,ApiMovieHelper, MovieItemClickListener, Serializable {
    RecyclerView recyclerViewofmovies;
    ArrayList<Moviedata> movielist;
    DatabasePresenter databasePresenter;
    ApiPresenter apiPresenter;
    String choice;
    HomeRecycleViewAdapter homeRecycleViewAdapter;
    LayoutAnimationController controller=null;
    ProgressBar progressBar;
    int page=0;

    public MoviesFragment() {
        movielist=new ArrayList<>();
    }
    public MoviesFragment(String choice) {
        this.choice=choice;
       movielist=new ArrayList<>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.moviesfragment, container, false);
        progressBar=view.findViewById(R.id.movie_loading);
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewofmovies=(RecyclerView)view.findViewById(R.id.testrec);


        homeRecycleViewAdapter=new HomeRecycleViewAdapter(getActivity().getApplicationContext(),movielist,this);
        recyclerViewofmovies.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        recyclerViewofmovies.setAdapter(homeRecycleViewAdapter);

        Context context=recyclerViewofmovies.getContext();
        controller= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_slide_bottom);
        recyclerViewofmovies.setLayoutAnimation(controller);
        recyclerViewofmovies.getAdapter().notifyDataSetChanged();
        recyclerViewofmovies.scheduleLayoutAnimation();
        databasePresenter=new DatabasePresenter(this);
        apiPresenter=new ApiPresenter(this);


        recyclerViewofmovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(!choice.equals("Favourite")) {
                    if (dy > 0) {

                        if ((movielist.size() - 4) == ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition()) {
                            callPresenter();
                        }
                    }
                }
            }
        });

        callPresenter();
        return view;
    }
    public void callPresenter(){
        if(choice.equals("Favourite")){
            databasePresenter.getFavouriteMovies(getContext());
        }
        else{
        page+=1;
        apiPresenter.getMovies(choice,page);}
    }



    @Override
    public void onItemClick(int position, ImageView poster) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),poster,poster.getTransitionName());
        intent.putExtra("MyClass",movielist.get(position));
        startActivity(intent,options.toBundle());
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


    }


    @Override
    public void setFavouriteMovie(ArrayList<Moviedata> Movies) {
        progressBar.setVisibility(View.GONE);
        movielist.addAll(Movies);
        homeRecycleViewAdapter.notifyDataSetChanged();
        recyclerViewofmovies.scheduleLayoutAnimation();
    }

    @Override
    public void setMoviesData(ArrayList<Moviedata> Movies) {

        progressBar.setVisibility(View.GONE);
        movielist.addAll(Movies);
        homeRecycleViewAdapter.notifyDataSetChanged();
        recyclerViewofmovies.scheduleLayoutAnimation();
    }

    @Override
    public void setMovieDetailsData(MovieDetails movieDetails) {

    }
}
