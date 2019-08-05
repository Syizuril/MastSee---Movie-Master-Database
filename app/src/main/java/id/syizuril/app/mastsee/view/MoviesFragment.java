package id.syizuril.app.mastsee.view;


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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.adapters.ListPopularMoviesAdapter;
import id.syizuril.app.mastsee.adapters.ListTopMoviesAdapter;
import id.syizuril.app.mastsee.adapters.ListUpcomingMoviesAdapter;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.viewmodels.ListPopularMoviesViewModel;
import id.syizuril.app.mastsee.viewmodels.ListTopMoviesViewModel;
import id.syizuril.app.mastsee.viewmodels.ListUpcomingMoviesViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements View.OnClickListener {
    private RecyclerView rvUpcomingMovies, rvPopularMovies, rvTopMovies;
    private TextView tvPopularTitle, tvTopTitle, tvConnectionError, tvSeeMorePopular, tvSeeMoreTop;
    private ArrayList<MovieResult> popularMovieList = new ArrayList<>();
    private ArrayList<MovieResult> topMovieList = new ArrayList<>();
    private ArrayList<MovieResult> upcomingMovieList = new ArrayList<>();
    private ListPopularMoviesAdapter mPopularAdapter;
    private ListTopMoviesAdapter mTopAdapter;
    private ListUpcomingMoviesAdapter mUpcomingAdapter;
    private ProgressBar mProgressBar;
    private ImageView mConnectionError;

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
        mProgressBar = view.findViewById(R.id.progressBarTopMovies);
        tvPopularTitle = view.findViewById(R.id.popular_movie_title);
        tvTopTitle = view.findViewById(R.id.top_movies_title);
        mConnectionError = view.findViewById(R.id.ivConnectionError);
        tvConnectionError = view.findViewById(R.id.tvConnectionError);
        tvSeeMorePopular = view.findViewById(R.id.seeMorePopular);
        tvSeeMoreTop = view.findViewById(R.id.seeMoreTop);

        rvPopularMovies.setHasFixedSize(true);
        rvUpcomingMovies.setHasFixedSize(true);
        rvTopMovies.setHasFixedSize(true);

        tvSeeMorePopular.setOnClickListener(this);
        tvSeeMoreTop.setOnClickListener(this);

        showProgressBar();

        ListPopularMoviesViewModel mListPopularMoviesViewModel = ViewModelProviders.of(this).get(ListPopularMoviesViewModel.class);
        ListTopMoviesViewModel mListTopMoviesViewModel = ViewModelProviders.of(this).get(ListTopMoviesViewModel.class);
        ListUpcomingMoviesViewModel mListUpcomingMoviesViewModel = ViewModelProviders.of(this).get(ListUpcomingMoviesViewModel.class);
        mListPopularMoviesViewModel.init();
        mListTopMoviesViewModel.init();
        mListUpcomingMoviesViewModel.init();
        mListPopularMoviesViewModel.getMovieResultList().observe(this, movieResponse -> {
            assert movieResponse != null;
            List<MovieResult> movieResults = movieResponse.getResults();
            popularMovieList.addAll(movieResults);
            mPopularAdapter.notifyDataSetChanged();
        });
        mListTopMoviesViewModel.getMovieResultList().observe(this, movieResponse -> {
            assert movieResponse != null;
            List<MovieResult> movieResults = movieResponse.getResults();
            topMovieList.addAll(movieResults);
            mTopAdapter.notifyDataSetChanged();
        });
        mListUpcomingMoviesViewModel.getMovieResultList().observe(this, movieUpcomingResponse -> {
            assert movieUpcomingResponse != null;
            List<MovieResult> movieUpcomingResults = movieUpcomingResponse.getResults();
            upcomingMovieList.addAll(movieUpcomingResults);
            mUpcomingAdapter.notifyDataSetChanged();
            hideProgressBar();
        });
        mListPopularMoviesViewModel.getIsConnected().observe(this, aBoolean -> {
            if(aBoolean){
                mConnectionError.setVisibility(View.GONE);
                tvConnectionError.setVisibility(View.GONE);
            }else{
                hideProgressBar();
                mConnectionError.setVisibility(View.VISIBLE);
                tvConnectionError.setVisibility(View.VISIBLE);
                tvTopTitle.setVisibility(View.GONE);
                tvPopularTitle.setVisibility(View.GONE);
                tvSeeMorePopular.setVisibility(View.GONE);
                tvSeeMoreTop.setVisibility(View.GONE);
            }

        });
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
        if(mUpcomingAdapter == null){
            mUpcomingAdapter = new ListUpcomingMoviesAdapter(this.getActivity(), upcomingMovieList);
            rvUpcomingMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));
            rvUpcomingMovies.setAdapter(mUpcomingAdapter);
        }
        mUpcomingAdapter.setOnItemClickCallback(this::showSelectedMovie);
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
        tvConnectionError.setVisibility(View.GONE);
        tvTopTitle.setVisibility(View.GONE);
        tvPopularTitle.setVisibility(View.GONE);
        tvSeeMorePopular.setVisibility(View.GONE);
        tvSeeMoreTop.setVisibility(View.GONE);

    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
        tvTopTitle.setVisibility(View.VISIBLE);
        tvPopularTitle.setVisibility(View.VISIBLE);
        tvSeeMorePopular.setVisibility(View.VISIBLE);
        tvSeeMoreTop.setVisibility(View.VISIBLE);
    }
}
