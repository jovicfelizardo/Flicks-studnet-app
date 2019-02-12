package seaney.humbolt.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import seaney.humbolt.myapplication.Models.Movie;
import seaney.humbolt.myapplication.adaptors.MovieAdaptor;

public class MainActivity extends AppCompatActivity {

    // this in the future would idelie be held in a file not uploaded to github.
    private String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

    private String movieUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key="+API_KEY;
    private String GenraCodes = "https://api.themoviedb.org/3/genre/movie/list?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US";



    List<Movie> DownloadedMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        DownloadedMovies = new ArrayList<>();
        final MovieAdaptor movieAdaptor = new MovieAdaptor(this,DownloadedMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        rvMovies.setAdapter(movieAdaptor);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(movieUrl, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try
                {
                    JSONArray jMoveArray = response.getJSONArray("results");
                    DownloadedMovies.addAll(Movie.returnJsonArray(jMoveArray));
                    Log.d("tag_Example",DownloadedMovies.toString());
                    movieAdaptor.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Database is excesable", Toast.LENGTH_SHORT).show();
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
}
