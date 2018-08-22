package com.madelene.projectcataloguemovie1.Util;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madelene.projectcataloguemovie1.Activity.MainActivity;
import com.madelene.projectcataloguemovie1.Entity.Movie;
import com.madelene.projectcataloguemovie1.Entity.MovieWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

public class MyAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {


    public static String movieUrl = "https://api.themoviedb.org/3/search/movie?api_key=7e0307940400b39d1591212549cc87de";
    private Context context;

    public MyAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... strings) {
        ArrayList<Movie> movies = new ArrayList<>();
        Uri uri = Uri.parse(movieUrl + "&language=en-US&query=" + strings[0]).buildUpon().build();
        HttpURLConnection urlConnection = null;
        Movie movie = null;
        try {
            URL url = new URL(uri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(6000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
//                urlConnection.
//                JSONObject jsonObject = new JSONObject("results");
//                JSONArray moviesj = new JSONArray("results");

//                for (int i = 0; i <= moviesj.length(); i++) {
                ObjectMapper objectMapper = new ObjectMapper();
                MovieWrapper wrapper = objectMapper.readValue(url, MovieWrapper.class);
                movies.addAll(wrapper.getResults());
//                    movies.addAll(Arrays.asList(objectMapper.readValue(moviesj.toString(), Movie[].class)));
//                }

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return movies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        ((MainActivity) context).updateMovieData(movies);
    }
}

