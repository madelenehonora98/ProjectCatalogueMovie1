package com.madelene.projectcataloguemovie1.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.madelene.projectcataloguemovie1.Entity.Movie;
import com.madelene.projectcataloguemovie1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    private ArrayList<Movie> movies;
    public Context context;

    public MovieAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    public ArrayList<Movie> getMovies() {
        if (movies == null) {
            movies = new ArrayList<>();
        }
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        getMovies().clear();
        getMovies().addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return getMovies().size();
    }

    @Override
    public Object getItem(int i) {
        return getMovies().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View convertview, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.list_movies, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        TextView txtReleaseDate = (TextView) view.findViewById(R.id.txtReleaseDate);
        ImageView imgPoster = (ImageView) view.findViewById(R.id.imgPoster);

        txtTitle.setText(getMovies().get(pos).getTitle());
        txtDescription.setText(getMovies().get(pos).getOverview());
        txtReleaseDate.setText(getMovies().get(pos).getRelease_date().toString());

        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w185" + movies.get(pos).getPoster_path())
                .error(R.mipmap.ic_launcher)
                .into(imgPoster);

        return view;
    }
}
