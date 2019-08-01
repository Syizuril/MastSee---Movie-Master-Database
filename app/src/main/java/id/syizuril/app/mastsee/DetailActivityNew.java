package id.syizuril.app.mastsee;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Date;

import id.syizuril.app.mastsee.models.MovieResult;

public class DetailActivityNew extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar tbBack = findViewById(R.id.tbBack);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvOverview = findViewById(R.id.tvOverview);
        TextView tvCrew = findViewById(R.id.tvCrew);
        TextView tvStatus = findViewById(R.id.tvStatus);
        TextView tvRuntime = findViewById(R.id.tvRuntime);
        TextView tvGenre = findViewById(R.id.tvGenre);
        ImageView imgCover = findViewById(R.id.cover);
        ImageView imgBanner = findViewById(R.id.banner);
        TextView tvToolbarTitle = findViewById(R.id.toolbar_title);

        MovieResult movieResult = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String title = movieResult.getOriginalTitle();
        Date date = movieResult.getReleaseDate();
        Double score = movieResult.getVoteAverage();
        String overview = movieResult.getOverview();
//        String crew = movieResult.getCrew();
//        String status = movieResult.getStatus();
//        String runtime = moviesTVShows.getRuntime();
//        String genre = moviesTVShows.getGenre();

        Glide.with(DetailActivityNew.this)
                .load(movieResult.getPosterPath())
                .into(imgCover);

        Glide.with(DetailActivityNew.this)
                .load(movieResult.getBackdropPath())
                .into(imgBanner);
        tvTitle.setText(title);
        tvDate.setText((CharSequence) date);
//        tvScore.setText(score);
        tvOverview.setText(overview);
//        tvCrew.setText(crew);
//        tvStatus.setText(status);
//        tvRuntime.setText(runtime);
//        tvGenre.setText(genre);
        tvToolbarTitle.setText(title);

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
                    Toast.makeText(DetailActivityNew.this, query, Toast.LENGTH_SHORT).show();
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
}
