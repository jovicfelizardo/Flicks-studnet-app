package seaney.humbolt.myapplication.adaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import seaney.humbolt.myapplication.Models.Movie;
import seaney.humbolt.myapplication.R;

public class MovieAdaptor extends RecyclerView.Adapter<MovieAdaptor.ViewHolder> {

    Context context;
    List<Movie> movieList;

    public MovieAdaptor(Context contextIn, List<Movie> movieListIn)
    {
        context = contextIn;
        movieList = movieListIn;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Movie movie = movieList.get(i);
        viewHolder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Overview;
        ImageView Portrate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Portrate = itemView.findViewById(R.id.idPoster);
            Title = itemView.findViewById(R.id.idTitle);
            Overview = itemView.findViewById(R.id.idOvervew);
        }

        public void bind(Movie movie)
        {
            Title.setText(movie.getTitle());
            Overview.setText(movie.getOverview());
            Glide.with(context).load(movie.getPosterpath()).into(Portrate);

        }
    }


}
