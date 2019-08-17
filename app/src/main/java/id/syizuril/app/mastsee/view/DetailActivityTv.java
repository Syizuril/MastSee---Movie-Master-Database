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
import java.util.List;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.models.TvShowsResult;
import id.syizuril.app.mastsee.viewmodels.TvShowsFavoriteViewModel;

public class DetailActivityTv extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_FAVORIT = "extra_favorit";
    private ProgressBar mProgressBar;
    private TvShowsFavoriteViewModel tvShowsFavoriteViewModel;
    private ImageView imgFavorite, imgUnfavorite;
    private TextView tvTitle;
    private TvShowsResult tvShowsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        Toolbar tbBack = findViewById(R.id.tbBack);
        tvTitle = findViewById(R.id.tvTitle);
        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvOverview = findViewById(R.id.tvOverview);
        TextView tvVoteCount = findViewById(R.id.tvCount);
        TextView tvOriginalLanguage = findViewById(R.id.tvOriginalLanguage);
        TextView tvOriginalTitle = findViewById(R.id.tvOriginalTitle);
        TextView tvPopularityPoint = findViewById(R.id.tvPopularityPoint);
        mProgressBar = findViewById(R.id.progressBar);
        ImageView imgCover = findViewById(R.id.cover);
        ImageView imgBanner = findViewById(R.id.banner);
        imgFavorite = findViewById(R.id.ivFavorite);
        imgUnfavorite = findViewById(R.id.ivUnfavorite);
        TextView tvToolbarTitle = findViewById(R.id.toolbar_title);
        showProgressBar();

        tvShowsFavoriteViewModel = ViewModelProviders.of(this).get(TvShowsFavoriteViewModel.class);
        tvShowsResult = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String favorit = getIntent().getStringExtra(EXTRA_FAVORIT);
        if(favorit != null){
            Glide.with(DetailActivityTv.this)
                    .load(tvShowsResult.getPosterPathAlt())
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

            Glide.with(DetailActivityTv.this)
                    .load(tvShowsResult.getBackdropPathAlt())
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
            Glide.with(DetailActivityTv.this)
                    .load(tvShowsResult.getPosterPath())
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

            Glide.with(DetailActivityTv.this)
                    .load(tvShowsResult.getBackdropPath())
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

        Integer id = tvShowsResult.getId();
        tvShowsFavoriteViewModel.getMoviesById(id).observe(this, movieId -> {
            List<TvShowsResult> movieById = movieId;
            assert movieById != null;
            if(movieById.isEmpty()){
                favorite();
            }else{
                unfavorite();
            }
        });
        String title = tvShowsResult.getName();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        String date = formatter.format(tvShowsResult.getFirstAirDate());
        Double score = tvShowsResult.getVoteAverage();
        String overview = tvShowsResult.getOverview();
        Integer voteCount = tvShowsResult.getVoteCount();
        String originalLanguage = tvShowsResult.getOriginalLanguage();
        String originalTitle = tvShowsResult.getOriginalName();
        Double popularityPoint = tvShowsResult.getPopularity();

        tvTitle.setText(title);
        tvDate.setText(String.valueOf(date));
        tvScore.setText(String.valueOf(score));
        tvOverview.setText(overview);
        tvVoteCount.setText(String.valueOf(voteCount));
        tvOriginalLanguage.setText(originalLanguage);
        tvOriginalTitle.setText(originalTitle);
        tvPopularityPoint.setText(String.valueOf(popularityPoint));

        tvToolbarTitle.setText(title);

        setSupportActionBar(tbBack);
        if (getSupportActionBar() != null) {
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
                    Intent moveIntent = new Intent(DetailActivityTv.this, SeeMoreActivity.class);
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
        } else if (item.getItemId() == R.id.change_language) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivUnfavorite:
                tvShowsFavoriteViewModel.insert(tvShowsResult);
                unfavorite();
                Toast.makeText(this, tvShowsResult.getName() + getResources().getString(R.string.favorited), Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivFavorite:
                favorite();
                tvShowsFavoriteViewModel.delete(tvShowsResult);
                Toast.makeText(this, tvShowsResult.getName() + getResources().getString(R.string.unfavorited), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void favorite(){
        imgFavorite.setVisibility(View.GONE);
        imgUnfavorite.setVisibility(View.VISIBLE);
    }

    private void unfavorite(){
        imgUnfavorite.setVisibility(View.GONE);
        imgFavorite.setVisibility(View.VISIBLE);
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
}
