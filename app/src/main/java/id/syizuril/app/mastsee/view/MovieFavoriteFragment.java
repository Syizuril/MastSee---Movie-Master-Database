package id.syizuril.app.mastsee.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.adapters.MovieFavoriteAdapter;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.viewmodels.MovieFavoriteViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {
    private MovieFavoriteViewModel movieFavoriteViewModel;
    private ArrayList<MovieResult> movieList = new ArrayList<>();
    private RecyclerView rvMovies;

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = view.findViewById(R.id.rvMovie);

        rvMovies.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvMovies.setHasFixedSize(true);

        final MovieFavoriteAdapter adapter = new MovieFavoriteAdapter();
        rvMovies.setAdapter(adapter);

        movieFavoriteViewModel = ViewModelProviders.of(this).get(MovieFavoriteViewModel.class);
        movieFavoriteViewModel.getAllMovies().observe(this, new Observer<List<MovieResult>>() {
            @Override
            public void onChanged(@Nullable List<MovieResult> movieResults) {
                adapter.setMovies(movieResults);
                adapter.setOnItemClickCallback(this::showSelectedMovie);
                Toast.makeText(getActivity(), "onChange", Toast.LENGTH_SHORT).show();
            }

            private void showSelectedMovie(MovieResult movieResult) {
                Intent sendMovieTV = new Intent(getActivity(), DetailActivity.class);
                sendMovieTV.putExtra(DetailActivity.EXTRA_MOVIE, movieResult);
                startActivity(sendMovieTV);
            }
        });
    }

}
