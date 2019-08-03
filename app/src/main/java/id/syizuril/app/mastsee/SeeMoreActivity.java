package id.syizuril.app.mastsee;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.syizuril.app.mastsee.adapters.ListPopularMoviesAdapter;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.models.MoviesTVShows;
import id.syizuril.app.mastsee.viewmodels.ListPopularMoviesViewModel;

public class SeeMoreActivity extends AppCompatActivity {
    private RecyclerView rvSeeMore;
    private ArrayList<MoviesTVShows> listTopMovies = new ArrayList<>();
    private ArrayList<MoviesTVShows> listPopularTv = new ArrayList<>();
    private ArrayList<MoviesTVShows> listTopTv = new ArrayList<>();
    private ArrayList<MovieResult> popularMovieList = new ArrayList<>();
    private ListPopularMoviesViewModel mListPopularMoviesViewModel;
    private ListPopularMoviesAdapter mAdapter;
    private ProgressBar mProgressBar;
    public static final String EXTRA_CATEGORY = "extra_category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);
        rvSeeMore = findViewById(R.id.rvSeeMoreMovie);
        TextView tvToolbarTitle = findViewById(R.id.toolbar_title);
        rvSeeMore.setHasFixedSize(true);

        Toolbar tbBack = findViewById(R.id.tbBack);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        switch (category) {
            case "listPopular":
                mListPopularMoviesViewModel = ViewModelProviders.of(this).get(ListPopularMoviesViewModel.class);
                mListPopularMoviesViewModel.init();
                mListPopularMoviesViewModel.getMovieResultList().observe(this, movieResponse -> {
                    List<MovieResult> movieResults = movieResponse.getResults();
                    popularMovieList.addAll(movieResults);
                    mAdapter.notifyDataSetChanged();
                });
                tvToolbarTitle.setText(R.string.popular);
                break;
            case "listTop":
                listTopMovies.addAll(TopMoviesData.getListData());
                tvToolbarTitle.setText(R.string.top_movies);
                break;
            case "listPopularTv":
                listPopularTv.addAll(PopularTvShowsData.getListData());
                tvToolbarTitle.setText(R.string.popular_tv_shows);
                break;
            case "listTopTv":
                listTopTv.addAll(TopTvShowsData.getListData());
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
        switch (category) {
            case "listPopular":
                RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
                if(mAdapter == null){
                    mAdapter = new ListPopularMoviesAdapter(this, popularMovieList);
                    rvSeeMore.setLayoutManager(linearLayoutManager);
                    rvSeeMore.setItemAnimator(new DefaultItemAnimator());
                    rvSeeMore.setNestedScrollingEnabled(true);
                }else {
                    mAdapter.notifyDataSetChanged();
                }
                mAdapter.setOnItemClickCallback(new ListPopularMoviesAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(MovieResult data) {
                        showSelectedMovie2(data);
                    }
                });
                break;
            case "listTop":
                rvSeeMore.setLayoutManager(new GridLayoutManager(this, 3));
                ListTopMoviesAdapter listTopMoviesAdapter = new ListTopMoviesAdapter(listTopMovies);
                rvSeeMore.setAdapter(listTopMoviesAdapter);
                listTopMoviesAdapter.setOnItemClickCallback(new ListTopMoviesAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(MoviesTVShows data) {
                        showSelectedMovie(data);
                    }
                });
                break;
            case "listPopularTv":
                rvSeeMore.setLayoutManager(new GridLayoutManager(this, 3));
                ListPopularTvShowsAdapter listPopularTvShowsAdapter = new ListPopularTvShowsAdapter(listPopularTv);
                rvSeeMore.setAdapter(listPopularTvShowsAdapter);
                listPopularTvShowsAdapter.setOnItemClickCallback(new ListPopularTvShowsAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(MoviesTVShows data) {
                        showSelectedMovie(data);
                    }
                });
                break;
            case "listTopTv":
                rvSeeMore.setLayoutManager(new GridLayoutManager(this, 3));
                ListTopTvShowsAdapter listTopTvShowsAdapter = new ListTopTvShowsAdapter(listTopTv);
                rvSeeMore.setAdapter(listTopTvShowsAdapter);
                listTopTvShowsAdapter.setOnItemClickCallback(new ListTopTvShowsAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(MoviesTVShows data) {
                        showSelectedMovie(data);
                    }
                });
                break;
        }
    }

    private void showSelectedMovie(MoviesTVShows moviesTVShows){
        Intent sendDataMovieTV = new Intent(this, DetailActivity.class);
        sendDataMovieTV.putExtra(DetailActivity.EXTRA_MOVIE, moviesTVShows);
        startActivity(sendDataMovieTV);
    }

    private void showSelectedMovie2(MovieResult movieResult){
        Intent senDataMovieTV = new Intent(this, DetailActivity.class);
        senDataMovieTV.putExtra(DetailActivity.EXTRA_MOVIE, movieResult);
    }
}
