package seaney.humbolt.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONObject;
import org.parceler.Parcels;

import seaney.humbolt.myapplication.Models.Movie;
public class MovieDetailActvity extends AppCompatActivity {

    public String GenraCodes = "https://api.themoviedb.org/3/genre/movie/list?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US";

    TextView tvName;
    TextView tvDiscription, tvCount, tvLangOR, tvContryOR, tvDateOr;
    RatingBar rbRating;
    Button bttnBack;
    JSONObject jsGens;
    //List<Movie> DownloadedMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_actvity);

        initViews();


        Movie moveContiner = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvName.setText(moveContiner.getTitle());
        tvDiscription.setText(moveContiner.getOverview());
        rbRating.setRating(Float.parseFloat(moveContiner.getRating()));
        tvCount.setText("Votes : "+ moveContiner.getVotesTotal());
        tvLangOR.setText(moveContiner.getLangOR());
        bttnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    private void initViews() {
        tvName = findViewById(R.id.tvMovieName);
        tvDiscription = findViewById(R.id.tvDiscription);
        rbRating = findViewById(R.id.rbRating);
        tvCount = findViewById(R.id.tvVotesTotal);
        tvLangOR = findViewById(R.id.tvLangOR);
        tvDateOr = findViewById(R.id.tvDateOfRelase);
        bttnBack = findViewById(R.id.backButton);
    }

    public void populateData()
    {
        Movie moveContiner = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvName.setText(moveContiner.getTitle());
        tvDiscription.setText(moveContiner.getOverview());
        rbRating.setRating(Float.parseFloat(moveContiner.getRating()));
        tvCount.setText("Votes : "+ moveContiner.getVotesTotal());

        /*String[] temp = moveContiner.getGenres();
        String ToSet = "";
        for (int i = 0; i < temp.length; i++ )
        {
            try {
                ToSet.concat(jsGens.getString(temp[i]) + ", ");
            }
            catch (JSONException e)
            {
                //cry
            }
        }
        tvContryOR.setText(ToSet);*/

    }

}
