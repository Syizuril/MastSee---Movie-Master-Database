package id.syizuril.app.mastsee.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.adapters.MovieFavoriteAdapter;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.viewmodels.MovieFavoriteViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment implements View.OnClickListener{
    private MovieFavoriteViewModel movieFavoriteViewModel;
    private RecyclerView rvMovies;
    private TextView tvDeleteAll, tvNoData;
    private ImageView imgNoData;

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = view.findViewById(R.id.rvMovie);
        tvDeleteAll = view.findViewById(R.id.deleteAll);
        tvNoData = view.findViewById(R.id.tvNotFound);
        imgNoData = view.findViewById(R.id.ivNotFound);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvMovies.setLayoutManager(new GridLayoutManager(getContext(), 5));
        } else {
            rvMovies.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }
        rvMovies.setHasFixedSize(true);

        final MovieFavoriteAdapter adapter = new MovieFavoriteAdapter();
        rvMovies.setAdapter(adapter);

        movieFavoriteViewModel = ViewModelProviders.of(this).get(MovieFavoriteViewModel.class);
        movieFavoriteViewModel.getAllMovies().observe(this, new Observer<List<MovieResult>>() {
            @Override
            public void onChanged(@Nullable List<MovieResult> movieResults) {
                assert movieResults != null;
                if(movieResults.isEmpty()){
                    tvDeleteAll.setVisibility(View.GONE);
                    rvMovies.setVisibility(View.GONE);
                    imgNoData.setVisibility(View.VISIBLE);
                    tvNoData.setVisibility(View.VISIBLE);
                }else{
                    imgNoData.setVisibility(View.GONE);
                    tvNoData.setVisibility(View.GONE);
                    adapter.setMovies(movieResults);
                    adapter.setOnItemClickCallback(this::showSelectedMovie);
                }
            }

            private void showSelectedMovie(MovieResult movieResult) {
                Intent sendMovieTV = new Intent(getActivity(), DetailActivity.class);
                sendMovieTV.putExtra(DetailActivity.EXTRA_MOVIE, movieResult);
                sendMovieTV.putExtra(DetailActivity.EXTRA_FAVORIT, "favorit");
                startActivity(sendMovieTV);
            }
        });

        tvDeleteAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.deleteAll) {
            showAlertDialog();
        }
    }

    private void showAlertDialog(){
        String dialogTitle, dialogMessage;
        dialogTitle = getResources().getString(R.string.delete_all_favorite);
        dialogMessage = getResources().getString(R.string.are_you_sure);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(this.getActivity()));

        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), (dialog, which) -> {
                    movieFavoriteViewModel.deleteAllMovies();
                    Toast.makeText(getContext(), getResources().getString(R.string.favorite_deleted), Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(getResources().getString(R.string.no), (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
