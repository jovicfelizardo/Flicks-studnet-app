package seaney.humbolt.myapplication.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String posterpath,
            backdrop_path,
            title,
            overview,
            Rating,
            totalvotes,
            releaseDate,
            langOR,
            movieID;

    String[] genres;

    public Movie()
    {

    }


    public Movie(JSONObject jsonObject) throws JSONException
    {
        posterpath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdrop_path = jsonObject.getString("backdrop_path");
        Rating = jsonObject.getString("vote_average");
        totalvotes = jsonObject.getString("vote_count");
        langOR = jsonObject.getString("original_language");
        movieID = jsonObject.getString("id");

        // fuck this part for real, why in the world would they
        genres = jsonObject.getString("genre_ids").split(",");
        genres[0] = genres[0].substring(1);
        genres[genres.length - 1] = genres[genres.length - 1].substring(0,genres[genres.length -1].length() - 1);

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
        Log.d("URl","https://image.tmdb.org/t/p/w342" + posterpath);
        return "https://image.tmdb.org/t/p/w342" + posterpath;

    }

    public String getBackdrop_path()
    {
        return "https://image.tmdb.org/t/p/w342" + backdrop_path;
    }

    public String getRating()
    {
        return Rating;
    }

    public String getVotesTotal()
    {
        return totalvotes;
    }

    public String[] getGenres()
    {
        return genres;
    }

    public String getLangOR() {return langOR;}

    public String getReleaseDate() {return releaseDate;}

    public String getMovieID() {
        Log.d("movie ID", "returning ID :" + movieID + " for movie " + title );
        return movieID;
    }


}
