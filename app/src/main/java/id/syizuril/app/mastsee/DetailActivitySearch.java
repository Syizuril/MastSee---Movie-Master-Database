package id.syizuril.app.mastsee;

import android.annotation.SuppressLint;
import android.app.SearchManager;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import id.syizuril.app.mastsee.models.SearchResult;

public class DetailActivitySearch extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

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
        TextView tvToolbarName = findViewById(R.id.toolbar_name);

        SearchResult movieResult = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String name = movieResult.getName();
        String title = movieResult.getTitle();
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
            String firstDate= formatter.format(movieResult.getFirstAirDate());
            tvFirstDate.setText(firstDate);
        } catch (NullPointerException e) {
            String firstDate= "";
            tvFirstDate.setText(firstDate);
        }
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
            String date = formatter.format(movieResult.getReleaseDate());
            tvDate.setText(date);
        } catch (NullPointerException e) {
            String date = " ";
            tvDate.setText(date);
        }
        Double score = movieResult.getVoteAverage();
        String overview = movieResult.getOverview();
        Double voteCount = movieResult.getVoteCount();
        String originalLanguage = movieResult.getOriginalLanguage();
        String originalTitle = movieResult.getOriginalTitle();
        String originalName = movieResult.getOriginalName();
        Double popularityPoint = movieResult.getPopularity();

        Glide.with(DetailActivitySearch.this)
                .load(movieResult.getPosterPath())
                .into(imgCover);

        Glide.with(DetailActivitySearch.this)
                .load(movieResult.getBackdropPath())
                .into(imgBanner);
        tvTitle.setText(title);
        tvName.setText(name);

        tvScore.setText(String.valueOf(new DecimalFormat("##.#").format(score)));
        tvOverview.setText(overview);
        tvVoteCount.setText(String.valueOf(voteCount));
        tvOriginalLanguage.setText(originalLanguage);
        tvOriginalTitle.setText(originalTitle);
        tvOriginalName.setText(originalName);
        tvPopularityPoint.setText(String.valueOf(popularityPoint));
        tvToolbarTitle.setText(title);
        tvToolbarName.setText(name);

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
}
