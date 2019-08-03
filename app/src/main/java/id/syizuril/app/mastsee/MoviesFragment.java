package id.syizuril.app.mastsee;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.syizuril.app.mastsee.adapters.ListPopularMoviesAdapter;
import id.syizuril.app.mastsee.adapters.ListTopMoviesAdapter;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.models.MoviesTVShows;
import id.syizuril.app.mastsee.viewmodels.ListPopularMoviesViewModel;
import id.syizuril.app.mastsee.viewmodels.ListTopMoviesViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements View.OnClickListener {
    private RecyclerView rvUpcomingMovies, rvPopularMovies, rvTopMovies;
    private ArrayList<MovieResult> popularMovieList = new ArrayList<>();
    private ArrayList<MovieResult> topMovieList = new ArrayList<>();
    private ArrayList<MoviesTVShows> listUpcomingMovies = new ArrayList<>();
    private ListPopularMoviesViewModel mListPopularMoviesViewModel;
    private ListTopMoviesViewModel mListTopMoviesViewModel;
    private ListPopularMoviesAdapter mPopularAdapter;
    private ListTopMoviesAdapter mTopAdapter;
    private ProgressBar mProgressBar, mProgressBar2;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        rvUpcomingMovies = view.findViewById(R.id.rv_upcoming_movie);
        rvPopularMovies = view.findViewById(R.id.rv_populer_movie);
        rvTopMovies = view.findViewById(R.id.rv_top_movies);
        mProgressBar = view.findViewById(R.id.progressBarPopularMovies);
        mProgressBar2 = view.findViewById(R.id.progressBarTopMovies);
        TextView tvSeeMorePopular = view.findViewById(R.id.seeMorePopular);
        TextView tvSeeMoreTop = view.findViewById(R.id.seeMoreTop);

        rvPopularMovies.setHasFixedSize(true);
        rvUpcomingMovies.setHasFixedSize(true);
        rvTopMovies.setHasFixedSize(true);

        tvSeeMorePopular.setOnClickListener(this);
        tvSeeMoreTop.setOnClickListener(this);

        showProgressBar();

        mListPopularMoviesViewModel = ViewModelProviders.of(this).get(ListPopularMoviesViewModel.class);
        mListTopMoviesViewModel = ViewModelProviders.of(this).get(ListTopMoviesViewModel.class);
        mListPopularMoviesViewModel.init();
        mListTopMoviesViewModel.init();
        mListPopularMoviesViewModel.getMovieResultList().observe(this, movieResponse -> {
            assert movieResponse != null;
            List<MovieResult> movieResults = movieResponse.getResults();
            popularMovieList.addAll(movieResults);
            mPopularAdapter.notifyDataSetChanged();
        });
        mListTopMoviesViewModel.getMovieResultList().observe(this, movieResponse -> {
            List<MovieResult> movieResults = movieResponse.getResults();
            topMovieList.addAll(movieResults);
            mTopAdapter.notifyDataSetChanged();
            hideProgressBar();
        });
        listUpcomingMovies.addAll(UpcomingMoviesData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        if(mPopularAdapter == null){
            mPopularAdapter = new ListPopularMoviesAdapter(this.getActivity(), popularMovieList);
            rvPopularMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));
            rvPopularMovies.setAdapter(mPopularAdapter);
        }else {
            mPopularAdapter.notifyDataSetChanged();
        }
        if(mTopAdapter == null){
            mTopAdapter = new ListTopMoviesAdapter(this.getActivity(), topMovieList);
            rvTopMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));
            rvTopMovies.setAdapter(mTopAdapter);
        }else{
            mTopAdapter.notifyDataSetChanged();
        }
        rvUpcomingMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ListUpcomingMoviesAdapter listUpcomingMoviesAdapter = new ListUpcomingMoviesAdapter(listUpcomingMovies);
        rvUpcomingMovies.setAdapter(listUpcomingMoviesAdapter);

        mPopularAdapter.setOnItemClickCallback(this::showSelectedMovie);
        mTopAdapter.setOnItemClickCallback(this::showSelectedMovie);
    }

    @Override
    public void onClick(View v) {
        Intent moveIntent = new Intent(getActivity(), SeeMoreActivity.class);
        switch (v.getId()){
            case R.id.seeMorePopular:
                moveIntent.putExtra(SeeMoreActivity.EXTRA_CATEGORY,"listPopular");
                startActivity(moveIntent);
                break;
            case R.id.seeMoreTop:
                moveIntent.putExtra(SeeMoreActivity.EXTRA_CATEGORY,"listTop");
                startActivity(moveIntent);
                break;
        }
    }

    private void showSelectedMovie(MovieResult movieResult){
        Intent sendMovieTV = new Intent(this.getActivity(), DetailActivity.class);
        sendMovieTV.putExtra(DetailActivity.EXTRA_MOVIE, movieResult);
        startActivity(sendMovieTV);
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar2.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
        mProgressBar2.setVisibility(View.GONE);
    }
}
