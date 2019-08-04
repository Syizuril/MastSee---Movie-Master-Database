package id.syizuril.app.mastsee;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.syizuril.app.mastsee.adapters.ListPopularMoviesAdapter;
import id.syizuril.app.mastsee.adapters.ListPopularTvShowsAdapter;
import id.syizuril.app.mastsee.adapters.ListTopMoviesAdapter;
import id.syizuril.app.mastsee.adapters.ListTopTvShowsAdapter;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.models.TvShowsResult;
import id.syizuril.app.mastsee.viewmodels.ListPopularMoviesViewModel;
import id.syizuril.app.mastsee.viewmodels.ListPopularTvShowsViewModel;
import id.syizuril.app.mastsee.viewmodels.ListTopMoviesViewModel;
import id.syizuril.app.mastsee.viewmodels.ListTopTvShowsViewModel;

public class SeeMoreActivity extends AppCompatActivity {
    private RecyclerView rvSeeMore;
    private ArrayList<MovieResult> popularMovieList = new ArrayList<>();
    private ArrayList<MovieResult> listTopMovies = new ArrayList<>();
    private ArrayList<TvShowsResult> popularTvList = new ArrayList<>();
    private ArrayList<TvShowsResult> topTvList = new ArrayList<>();
    private ListPopularMoviesViewModel mListPopularMoviesViewModel;
    private ListTopMoviesViewModel mListTopMoviesViewModel;
    private ListPopularTvShowsViewModel mListPopularTvShowsViewModel;
    private ListTopTvShowsViewModel mTopTvShowsViewModel;
    private ListPopularMoviesAdapter mPopularAdapter;
    private ListTopMoviesAdapter mTopAdapter;
    private ListPopularTvShowsAdapter mPopularTvAdapter;
    private ListTopTvShowsAdapter mTopTvAdapter;
    private ProgressBar mProgressBar;
    public static final String EXTRA_CATEGORY = "extra_category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);
        rvSeeMore = findViewById(R.id.rvSeeMoreMovie);
        TextView tvToolbarTitle = findViewById(R.id.toolbar_title);
        mProgressBar = findViewById(R.id.progressBar);
        rvSeeMore.setHasFixedSize(true);

        Toolbar tbBack = findViewById(R.id.tbBack);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        switch (category) {
            case "listPopular":
                showProgressBar();
                mListPopularMoviesViewModel = ViewModelProviders.of(this).get(ListPopularMoviesViewModel.class);
                mListPopularMoviesViewModel.init();
                mListPopularMoviesViewModel.getMovieResultList().observe(this, popularMovieResponse -> {
                    assert popularMovieResponse != null;
                    List<MovieResult> popularMovieResults = popularMovieResponse.getResults();
                    popularMovieList.addAll(popularMovieResults);
                    mPopularAdapter.notifyDataSetChanged();
                    hideProgressBar();
                });
                tvToolbarTitle.setText(R.string.popular);
                break;
            case "listTop":
                showProgressBar();
                mListTopMoviesViewModel = ViewModelProviders.of(this).get(ListTopMoviesViewModel.class);
                mListTopMoviesViewModel.init();
                mListTopMoviesViewModel.getMovieResultList().observe(this, movieResponse -> {
                    assert movieResponse != null;
                    List<MovieResult> movieResults = movieResponse.getResults();
                    listTopMovies.addAll(movieResults);
                    mTopAdapter.notifyDataSetChanged();
                    hideProgressBar();
                });
                tvToolbarTitle.setText(R.string.top_movies);
                break;
            case "listPopularTv":
                showProgressBar();
                mListPopularTvShowsViewModel = ViewModelProviders.of(this).get(ListPopularTvShowsViewModel.class);
                mListPopularTvShowsViewModel.init();
                mListPopularTvShowsViewModel.getTvShowsResultList().observe(this, popularTvResponse -> {
                    assert popularTvResponse != null;
                    List<TvShowsResult> popularTvResults = popularTvResponse.getResults();
                    popularTvList.addAll(popularTvResults);
                    mPopularTvAdapter.notifyDataSetChanged();
                    hideProgressBar();
                });
                tvToolbarTitle.setText(R.string.popular_tv_shows);
                break;
            case "listTopTv":
                showProgressBar();
                mTopTvShowsViewModel = ViewModelProviders.of(this).get(ListTopTvShowsViewModel.class);
                mTopTvShowsViewModel.init();
                mTopTvShowsViewModel.getTvShowsResultList().observe(this, topTvResponse ->{
                    assert topTvResponse != null;
                    List<TvShowsResult> topTvResults = topTvResponse.getResults();
                    topTvList.addAll(topTvResults);
                    mTopTvAdapter.notifyDataSetChanged();
                    hideProgressBar();
                });
                tvToolbarTitle.setText(R.string.top_tv_shows);
                break;
        }
        showRecyclerGrid();
        setSupportActionBar(tbBack);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(SeeMoreActivity.this, query, Toast.LENGTH_SHORT).show();
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }else if (item.getItemId() == R.id.change_language){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRecyclerGrid() {
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        switch (category) {
            case "listPopular":
                if(mPopularAdapter == null){
                    mPopularAdapter = new ListPopularMoviesAdapter(this, popularMovieList);
                    rvSeeMore.setLayoutManager(gridLayoutManager);
                    rvSeeMore.setAdapter(mPopularAdapter);
                }else {
                    mPopularAdapter.notifyDataSetChanged();
                }
                mPopularAdapter.setOnItemClickCallback(this::showSelectedMovie);
                break;
            case "listTop":
                if(mTopAdapter == null){
                    mTopAdapter = new ListTopMoviesAdapter(this, listTopMovies);
                    rvSeeMore.setLayoutManager(gridLayoutManager);
                    rvSeeMore.setAdapter(mTopAdapter);
                }else {
                    mTopAdapter.notifyDataSetChanged();
                }
                mTopAdapter.setOnItemClickCallback(this::showSelectedMovie);
                break;
            case "listPopularTv":
                if(mPopularTvAdapter == null){
                    mPopularTvAdapter = new ListPopularTvShowsAdapter(this, popularTvList);
                    rvSeeMore.setLayoutManager(gridLayoutManager);
                    rvSeeMore.setAdapter(mPopularTvAdapter);
                }else{
                    mPopularTvAdapter.notifyDataSetChanged();
                }
                mPopularTvAdapter.setOnItemClickCallback(this::showSelectedTvShows);
                break;
            case "listTopTv":
                if(mTopTvAdapter == null){
                    mTopTvAdapter = new ListTopTvShowsAdapter(this, topTvList);
                    rvSeeMore.setLayoutManager(gridLayoutManager);
                    rvSeeMore.setAdapter(mTopTvAdapter);
                }else{
                    mTopTvAdapter.notifyDataSetChanged();
                }
                mTopTvAdapter.setOnItemClickCallback(this::showSelectedTvShows);
                break;
        }
    }

    private void showSelectedMovie(MovieResult movieResult){
        Intent sendMovieTV = new Intent(this, DetailActivity.class);
        sendMovieTV.putExtra(DetailActivity.EXTRA_MOVIE, movieResult);
        startActivity(sendMovieTV);
    }

    private void showSelectedTvShows(TvShowsResult tvShowsResult){
        Intent sendMovieTV = new Intent(this, DetailActivityTv.class);
        sendMovieTV.putExtra(DetailActivityTv.EXTRA_MOVIE, tvShowsResult);
        startActivity(sendMovieTV);
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
}
