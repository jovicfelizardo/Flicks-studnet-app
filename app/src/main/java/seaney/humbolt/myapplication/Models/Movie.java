package seaney.humbolt.myapplication.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String posterpath,
                   title,
                   overview;

    public Movie(JSONObject jsonObject) throws JSONException
    {
        posterpath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    public static List<Movie> returnJsonArray(JSONArray jsonMovieArry) throws JSONException
    {
        List<Movie> listOfMovies = new ArrayList<>();

        for (int i = 0; i < jsonMovieArry.length(); i++)
        {
            listOfMovies.add(new Movie(jsonMovieArry.getJSONObject(i)));

        }

        return listOfMovies;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterpath() {
        Log.d("URl","https://image.tmdb.org/t/p/w342" +posterpath);
        return String.format("https://image.tmdb.org/t/p/w342"+posterpath);

    }
}
