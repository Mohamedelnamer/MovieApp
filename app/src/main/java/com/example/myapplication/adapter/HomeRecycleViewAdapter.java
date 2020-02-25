package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.utills.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.myapplication.model.Moviedata;

public class HomeRecycleViewAdapter extends RecyclerView.Adapter<HomeRecycleViewAdapter.ViewHolder> {


    private Context context;

    private List<Moviedata>moviesList;
    public MovieItemClickListener movieItemClickListener;



    public HomeRecycleViewAdapter(Context context, List<Moviedata> movies, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        moviesList = movies;
        this.movieItemClickListener = movieItemClickListener;
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
        holder.setItemData(position);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

     class ViewHolder extends RecyclerView .ViewHolder{


        public TextView filmtitle;
        public ImageView filmposter;
        CardView cardView;


    public ViewHolder(View itemView) {
            super(itemView);
            filmtitle=itemView.findViewById(R.id.filmtitle);
            filmposter=itemView.findViewById(R.id.filmposter);
            cardView=itemView.findViewById(R.id.filmcardid);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onItemClick(getAdapterPosition(),filmposter);
                }
            });

        }
         public void setItemData( int position){
             filmtitle.setText(moviesList.get(position).getTitle());
             Picasso.get().load(AppConstants.imageUrl+moviesList.get(position).getPoster_path()).into(filmposter);
         }
    }



}
