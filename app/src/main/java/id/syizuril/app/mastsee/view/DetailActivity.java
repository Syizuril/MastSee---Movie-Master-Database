package id.syizuril.app.mastsee.view;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
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

import java.text.SimpleDateFormat;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.viewmodels.MovieFavoriteViewModel;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_MOVIE = "extra_movie";
    private ProgressBar mProgressBar;
    private MovieFavoriteViewModel movieFavoriteViewModel;
    private ImageView imgFavorite, imgUnfavorite;
    private TextView tvTitle;
    private MovieResult movieResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

        movieResult = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String title = movieResult.getTitle();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        String date = formatter.format(movieResult.getReleaseDate());
        Double score = movieResult.getVoteAverage();
        String overview = movieResult.getOverview();
        Integer voteCount = movieResult.getVoteCount();
        String originalLanguage = movieResult.getOriginalLanguage();
        String originalTitle = movieResult.getOriginalTitle();
        Double popularityPoint = movieResult.getPopularity();

        Glide.with(DetailActivity.this)
                .load(movieResult.getPosterPath())
                .into(imgCover);

        Glide.with(DetailActivity.this)
                .load(movieResult.getBackdropPath())
                .into(imgBanner);

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

        movieFavoriteViewModel = ViewModelProviders.of(this).get(MovieFavoriteViewModel.class);

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
                    Intent moveIntent = new Intent(DetailActivity.this, SeeMoreActivity.class);
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

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ivUnfavorite:
                movieFavoriteViewModel.insert(movieResult);
                imgUnfavorite.setVisibility(View.GONE);
                imgFavorite.setVisibility(View.VISIBLE);
                Toast.makeText(this, movieResult.getTitle() + " Telah Anda Favoritkan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivFavorite:
                imgFavorite.setVisibility(View.GONE);
                imgUnfavorite.setVisibility(View.VISIBLE);
        }
    }
}
