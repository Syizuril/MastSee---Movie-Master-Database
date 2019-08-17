package id.syizuril.app.mastsee.view;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.models.SearchResult;
import id.syizuril.app.mastsee.models.TvShowsResult;
import id.syizuril.app.mastsee.viewmodels.MovieFavoriteViewModel;
import id.syizuril.app.mastsee.viewmodels.TvShowsFavoriteViewModel;

public class DetailActivitySearch extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_FAVORIT = "extra_favorit";
    private ProgressBar mProgressBar;
    private MovieFavoriteViewModel movieFavoriteViewModel;
    private TvShowsFavoriteViewModel tvShowsFavoriteViewModel;
    private ImageView imgFavorite, imgUnfavorite;
    private SearchResult movieResult;
    private String mediaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_search);

        Toolbar tbBack = findViewById(R.id.tbBack);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvOverview = findViewById(R.id.tvOverview);
        TextView tvVoteCount = findViewById(R.id.tvCount);
        TextView tvOriginalLanguage = findViewById(R.id.tvOriginalLanguage);
        TextView tvOriginalTitle = findViewById(R.id.tvOriginalTitle);
        TextView tvOriginalName = findViewById(R.id.tvOriginalName);
        TextView tvPopularityPoint = findViewById(R.id.tvPopularityPoint);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvFirstDate = findViewById(R.id.tvFirstDate);
        ImageView imgCover = findViewById(R.id.cover);
        ImageView imgBanner = findViewById(R.id.banner);
        TextView tvToolbarTitle = findViewById(R.id.toolbar_title);
        imgFavorite = findViewById(R.id.ivFavorite);
        imgUnfavorite = findViewById(R.id.ivUnfavorite);
        mProgressBar = findViewById(R.id.progressBar);
        showProgressBar();

        movieFavoriteViewModel = ViewModelProviders.of(this).get(MovieFavoriteViewModel.class);
        tvShowsFavoriteViewModel = ViewModelProviders.of(this).get(TvShowsFavoriteViewModel.class);

        movieResult = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String favorit = getIntent().getStringExtra(EXTRA_FAVORIT);
        Integer id = movieResult.getId();
        if(favorit != null){
            Glide.with(DetailActivitySearch.this)
                    .load(movieResult.getPosterPathAlt())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            hideProgressBar();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            hideProgressBar();
                            return false;
                        }
                    })
                    .into(imgCover);

            Glide.with(DetailActivitySearch.this)
                    .load(movieResult.getBackdropPathAlt())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            hideProgressBar();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            hideProgressBar();
                            return false;
                        }
                    })
                    .into(imgBanner);
        }else{
            Glide.with(DetailActivitySearch.this)
                    .load(movieResult.getPosterPath())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            hideProgressBar();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            hideProgressBar();
                            return false;
                        }
                    })
                    .into(imgCover);

            Glide.with(DetailActivitySearch.this)
                    .load(movieResult.getBackdropPath())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            hideProgressBar();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            hideProgressBar();
                            return false;
                        }
                    })
                    .into(imgBanner);
        }
        mediaType = movieResult.getMediaType();
        if(mediaType.equals("movie")){
            movieFavoriteViewModel.getMoviesById(id).observe(this, movieId -> {
                assert movieId != null;
                if(movieId.isEmpty()){
                    favorite();
                }else{
                    unfavorite();
                }
            });
            try {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
                String date = formatter.format(movieResult.getReleaseDate());
                tvDate.setText(date);
            } catch (NullPointerException e) {
                String date = " ";
                tvDate.setText(date);
            }
            String title = movieResult.getTitle();
            String originalTitle = movieResult.getOriginalTitle();

            tvTitle.setText(title);
            tvOriginalTitle.setText(originalTitle);
            tvToolbarTitle.setText(title);
        }else if(mediaType.equals("tv")){
            tvShowsFavoriteViewModel.getMoviesById(id).observe(this, movieId -> {
                assert movieId != null;
                if(movieId.isEmpty()){
                    favorite();
                }else{
                    unfavorite();
                }
            });
            try {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
                String firstDate= formatter.format(movieResult.getFirstAirDate());
                tvFirstDate.setText(firstDate);
            } catch (NullPointerException e) {
                String firstDate= "";
                tvFirstDate.setText(firstDate);
            }
            String name = movieResult.getName();
            String originalName = movieResult.getOriginalName();

            tvName.setText(name);
            tvOriginalName.setText(originalName);
            tvToolbarTitle.setText(name);
        }
        Double score = movieResult.getVoteAverage();
        String overview = movieResult.getOverview();
        Integer voteCount = movieResult.getVoteCount();
        String originalLanguage = movieResult.getOriginalLanguage();
        Double popularityPoint = movieResult.getPopularity();

        tvScore.setText(String.valueOf(score));
        tvOverview.setText(overview);
        tvVoteCount.setText(String.valueOf(voteCount));
        tvOriginalLanguage.setText(originalLanguage);
        tvPopularityPoint.setText(String.valueOf(popularityPoint));

        setSupportActionBar(tbBack);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        imgUnfavorite.setOnClickListener(this);
        imgFavorite.setOnClickListener(this);
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
                    Intent moveIntent = new Intent(DetailActivitySearch.this, SeeMoreActivity.class);
                    moveIntent.putExtra(SeeMoreActivity.EXTRA_CATEGORY,query);
                    startActivity(moveIntent);
                    return false;
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

    @Override
    public void onClick(View v) {
        if(mediaType.equals("movie")){
            Integer voteCount = movieResult.getVoteCount();
            Integer id = movieResult.getId();
            Double voteAvg = movieResult.getVoteAverage();
            String title = movieResult.getTitle();
            Double popularityPoint = movieResult.getPopularity();
            String posterPath = movieResult.getPosterPathAlt().replace("https://image.tmdb.org/t/p/w600_and_h900_bestv2//","/");
            String originalLang = movieResult.getOriginalLanguage();
            String originalTitle = movieResult.getOriginalTitle();
            String backdropPath = movieResult.getBackdropPathAlt().replace("https://image.tmdb.org/t/p/w533_and_h300_bestv2//","/");
            String overview = movieResult.getOverview();
            Date releaseDate = movieResult.getReleaseDate();
            MovieResult movieResult = new MovieResult(voteCount, id, voteAvg, title, popularityPoint, posterPath, originalLang, originalTitle, backdropPath, overview, releaseDate);
            switch (v.getId()){
                case R.id.ivUnfavorite:
                    movieFavoriteViewModel.insert(movieResult);
                    unfavorite();
                    Toast.makeText(this, movieResult.getTitle() +" "+ getResources().getString(R.string.favorited), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ivFavorite:
                    favorite();
                    movieFavoriteViewModel.delete(movieResult);
                    Toast.makeText(this, movieResult.getTitle() +" "+ getResources().getString(R.string.unfavorited), Toast.LENGTH_SHORT).show();
                    break;
            }
        }else if(mediaType.equals("tv")){
            Integer voteCount = movieResult.getVoteCount();
            Integer id = movieResult.getId();
            Double voteAvg = movieResult.getVoteAverage();
            String name = movieResult.getName();
            Double popularityPoint = movieResult.getPopularity();
            String posterPath = movieResult.getPosterPathAlt().replace("https://image.tmdb.org/t/p/w600_and_h900_bestv2//","/");
            String originalLang = movieResult.getOriginalLanguage();
            String originalName = movieResult.getOriginalName();
            String backdropPath = movieResult.getBackdropPathAlt().replace("https://image.tmdb.org/t/p/w533_and_h300_bestv2//","/");
            String overview = movieResult.getOverview();
            Date releaseDate = movieResult.getFirstAirDate();
            TvShowsResult tvShowsResult = new TvShowsResult(originalName, name, popularityPoint, voteCount, releaseDate, backdropPath, originalLang, id, voteAvg, overview, posterPath);
            switch (v.getId()){
                case R.id.ivUnfavorite:
                    tvShowsFavoriteViewModel.insert(tvShowsResult);
                    unfavorite();
                    Toast.makeText(this, tvShowsResult.getName() +" "+ getResources().getString(R.string.favorited), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ivFavorite:
                    favorite();
                    tvShowsFavoriteViewModel.delete(tvShowsResult);
                    Toast.makeText(this, tvShowsResult.getName() +" "+ getResources().getString(R.string.unfavorited), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    private void favorite(){
        imgFavorite.setVisibility(View.GONE);
        imgUnfavorite.setVisibility(View.VISIBLE);
    }

    private void unfavorite(){
        imgUnfavorite.setVisibility(View.GONE);
        imgFavorite.setVisibility(View.VISIBLE);
    }
}
