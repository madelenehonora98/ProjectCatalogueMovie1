package com.madelene.projectcataloguemovie1.Activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.madelene.projectcataloguemovie1.Adapter.MovieAdapter;
import com.madelene.projectcataloguemovie1.Entity.Movie;
import com.madelene.projectcataloguemovie1.R;
import com.madelene.projectcataloguemovie1.Util.MyAsyncTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    MyAsyncTask myAsyncTask;
    private MovieAdapter movieAdapter;
    @BindView(R.id.txtMovie)
    EditText txtMovie;
    @BindView(R.id.listMovie)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myAsyncTask = new MyAsyncTask(this);
        listView.setAdapter(getMovieAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent detailIntent = new Intent(MainActivity.this, DetailMovieActivity.class);
                detailIntent.putExtra("EXTRA_MOVIE", (Parcelable) parent.getItemAtPosition(position));
                startActivity(detailIntent);
            }
        });
        setTitle("Catalogue Movie");
    }

    public void updateMovieData(ArrayList<Movie> movies) {
        getMovieAdapter().setMovies(movies);
    }

    public MovieAdapter getMovieAdapter() {
        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter(this);
        }
        return movieAdapter;
    }

    @OnClick(R.id.btnCari)
    public void searchAction() {
        if (!txtMovie.getText().toString().isEmpty()) {

            MyAsyncTask task = new MyAsyncTask(this);
            task.execute(txtMovie.getText().toString());

        } else {
            Toast.makeText(MainActivity.this, "Mohon masukkan nama film", Toast.LENGTH_SHORT).show();
        }

    }



}
