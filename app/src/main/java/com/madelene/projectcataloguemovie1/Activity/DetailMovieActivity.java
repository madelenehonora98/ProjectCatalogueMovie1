package com.madelene.projectcataloguemovie1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.madelene.projectcataloguemovie1.Entity.Movie;
import com.madelene.projectcataloguemovie1.R;
import com.squareup.picasso.Picasso;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class DetailMovieActivity extends AppCompatActivity {


    public static Movie EXTRA_MOVIE;
    public Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        movie = getIntent().getParcelableExtra("EXTRA_MOVIE");

        setTitle(movie.getTitle());

        TextView txtTitle = (TextView) this.findViewById(R.id.txtTitleDetail);
        JustifiedTextView txtDescription = (JustifiedTextView) this.findViewById(R.id.txtDescriptionDetail);
        TextView txtReleaseDate = (TextView) this.findViewById(R.id.txtReleaseDateDetail);
        TextView txtRating = (TextView) this.findViewById(R.id.txtRatingDetail);
        ImageView imgPoster = (ImageView) this.findViewById(R.id.imgPosterDetail);

        txtTitle.setText(movie.getTitle());
        txtDescription.setText(movie.getOverview());
        txtRating.setText( Double.toString(movie.getVote_average()));
        txtReleaseDate.setText(movie.getRelease_date().toString());

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w185" + movie.getPoster_path())
                .error(R.mipmap.ic_launcher)
                .into(imgPoster);
    }
}
