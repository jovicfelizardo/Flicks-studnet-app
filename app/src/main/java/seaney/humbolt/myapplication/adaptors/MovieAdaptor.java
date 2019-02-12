package seaney.humbolt.myapplication.adaptors;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import seaney.humbolt.myapplication.GlideApp;
import seaney.humbolt.myapplication.Models.Movie;
import seaney.humbolt.myapplication.MovieDetailActvity;
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
        RelativeLayout rlLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Portrate = itemView.findViewById(R.id.idPoster);
            Title = itemView.findViewById(R.id.idTitle);
            Overview = itemView.findViewById(R.id.idOvervew);
            rlLayout = (RelativeLayout) itemView.findViewById(R.id.rlLayout);
        }

        public void bind(final Movie movie)
        {

            rlLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent i = new Intent(context, MovieDetailActvity.class);

                    i.putExtra("movie", Parcels.wrap(movie));

                    context.startActivity(i);
                }
            });

            Title.setText(movie.getTitle());
            Overview.setText(movie.getOverview());
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            {
                GlideApp.with(context)
                        .load(movie.getPosterpath())
                        //.placeholder(new ColorDrawable(Color.GREEN))
                        .into(Portrate);
            }
            else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                GlideApp.with(context).load(movie.getBackdrop_path()).into(Portrate);
            }
        }
    }


}
