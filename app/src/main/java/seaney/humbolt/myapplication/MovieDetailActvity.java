package seaney.humbolt.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;
import seaney.humbolt.myapplication.Models.Movie;
public class MovieDetailActvity extends YouTubeBaseActivity {

    public String GenraCodes = "https://api.themoviedb.org/3/genre/movie/list?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US";
    private final String GOOGLE_API = "AIzaSyA-lQ5QUFtu6qmfRbk3LqCbwv-cXVpw2o4";
    private final String TRAILER_API_REQUEST = "https://api.themoviedb.org/3/movie/%s/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";


    TextView tvName;
    TextView tvDiscription, tvCount, tvLangOR, tvContryOR, tvDateOr;
    RatingBar rbRating;
    Button bttnBack;
    JSONObject jsGens;
    YouTubePlayerView ytView;
    //List<Movie> DownloadedMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_actvity);

        initViews();


        Movie movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvName.setText(movie.getTitle());
        tvDiscription.setText(movie.getOverview());
        rbRating.setRating(Float.parseFloat(movie.getRating()));
        tvCount.setText("Votes : "+ movie.getVotesTotal());
        tvLangOR.setText(movie.getLangOR());
        bttnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(String.format(TRAILER_API_REQUEST,movie.getMovieID()), new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray results = response.getJSONArray("results");
                    if (results.length() == 0)
                    {
                        return;
                    }
                    else
                    {
                        JSONObject trailer = results.getJSONObject(0);
                        final String youtubeKey = trailer.getString("key");
                        initYoutube(youtubeKey);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });





    }

    private void initYoutube(final String youtubeKey) {
        ytView.initialize(GOOGLE_API, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("youtube", "we did it");
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

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
        ytView = findViewById(R.id.player);

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
