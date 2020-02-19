package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.View.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.myapplication.MoviesModel.Moviedata;

public class HomeRecycleViewAdapter extends RecyclerView.Adapter<HomeRecycleViewAdapter.ViewHolder> {


    private Context context;
    private List<Moviedata>Movies;
    public RecycleViewClickInterface recycleViewClickInterface;



    public HomeRecycleViewAdapter(Context context, List<Moviedata> movies,RecycleViewClickInterface recycleViewClickInterface) {
        this.context = context;
        Movies = movies;
        this.recycleViewClickInterface=recycleViewClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.filmcard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.filmtitle.setText(Movies.get(position).getTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+Movies.get(position).getPoster_path()).into(holder.filmposter);

    }

    @Override
    public int getItemCount() {
        return Movies.size();
    }

     class ViewHolder extends RecyclerView .ViewHolder{

        public TextView filmtitle;
        public ImageView filmposter;

        CardView cardView;


    public ViewHolder(View itemView) {
            super(itemView);
            filmtitle=(TextView)itemView.findViewById(R.id.filmtitle);
            filmposter=(ImageView)itemView.findViewById(R.id.filmposter);
            cardView=(CardView)itemView.findViewById(R.id.filmcardid);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recycleViewClickInterface.onItemClick(getAdapterPosition(),filmposter);
                }
            });


        }
    }



}
