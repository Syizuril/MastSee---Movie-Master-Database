package id.syizuril.app.mastsee.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import id.syizuril.app.mastsee.viewmodels.MovieFavoriteViewModel;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_FAVORIT = "extra_favorit";
    private ProgressBar mProgressBar;
    private MovieFavoriteViewModel movieFavoriteViewModel;
    private ImageView imgFavorite, imgUnfavorite;
    private MovieResult movieResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar tbBack = findViewById(R.id.tbBack);
        TextView tvTitle = findViewById(R.id.tvTitle);
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

        movieFavoriteViewModel = ViewModelProviders.of(this).get(MovieFavoriteViewModel.class);

        movieResult = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String favorit = getIntent().getStringExtra(EXTRA_FAVORIT);

        if(favorit != null){
            Glide.with(DetailActivity.this)
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

            Glide.with(DetailActivity.this)
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
            Glide.with(DetailActivity.this)
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

            Glide.with(DetailActivity.this)
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

        Long id = movieResult.getId();
        movieFavoriteViewModel.getMoviesById(id).observe(this, movieId -> {
            assert movieId != null;
            if(movieId.isEmpty()){
                favorite();
            }else{
                unfavorite();
            }
        });
        String title = movieResult.getTitle();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        String date = formatter.format(movieResult.getReleaseDate());
        Double score = movieResult.getVoteAverage();
        String overview = movieResult.getOverview();
        Integer voteCount = movieResult.getVoteCount();
        String originalLanguage = movieResult.getOriginalLanguage();
        String originalTitle = movieResult.getOriginalTitle();
        Double popularityPoint = movieResult.getPopularity();

        tvTitle.setText(title);
        tvDate.setText(String.valueOf(date));
        tvScore.setText(String.valueOf(score));
        tvOverview.setText(overview);
        tvVoteCount.setText(String.valueOf(voteCount));
        tvOriginalLanguage.setText(originalLanguage);
        tvOriginalTitle.setText(originalTitle);
        tvPopularityPoint.setText(String.valueOf(popularityPoint));
        tvToolbarTitle.setText(title);
        hideProgressBar();

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

        MenuItem search = menu.findItem(R.id.search);
        search.setVisible(false);
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
        }else if(item.getItemId() == R.id.setting_reminder){
            Intent mIntent = new Intent(this, SettingReminderActivity.class);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        Integer voteCount = movieResult.getVoteCount();
        Long id = movieResult.getId();
        Double voteAvg = movieResult.getVoteAverage();
        String title = movieResult.getTitle();
        Double popularityPoint = movieResult.getPopularity();
        String posterPath = movieResult.getPosterPath();
        String originalLang = movieResult.getOriginalLanguage();
        String originalTitle = movieResult.getOriginalTitle();
        String backdropPath = movieResult.getBackdropPath();
        String overview = movieResult.getOverview();
        Date releaseDate = movieResult.getReleaseDate();
        MovieResult movieResult = new MovieResult(voteCount, id, voteAvg, title, popularityPoint, posterPath, originalLang, originalTitle, backdropPath, overview, releaseDate);
        switch (v.getId()){
            case R.id.ivUnfavorite:
                movieFavoriteViewModel.insert(movieResult);
                unfavorite();
                Toast.makeText(this, movieResult.getTitle() +" "+getResources().getString(R.string.favorited), Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivFavorite:
                favorite();
                movieFavoriteViewModel.delete(movieResult);
                Toast.makeText(this, movieResult.getTitle() +" "+ getResources().getString(R.string.unfavorited), Toast.LENGTH_SHORT).show();
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
}
