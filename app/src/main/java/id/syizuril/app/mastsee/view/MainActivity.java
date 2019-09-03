package id.syizuril.app.mastsee.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.SettingReminderActivity;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                Fragment nav;
                switch (item.getItemId()) {
                    case R.id.navigation_movies:
                        nav = new MoviesFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.layout_main, nav, nav.getClass().getSimpleName())
                                .commit();
                        return true;
                    case R.id.navigation_tv_shows:
                        nav = new TvShowsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.layout_main, nav, nav.getClass().getSimpleName())
                                .commit();
                        return true;
                    case R.id.navigation_favorite:
                        nav = new FavoriteFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.layout_main, nav, nav.getClass().getSimpleName())
                                .commit();
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState == null){
            navView.setSelectedItemId(R.id.navigation_movies);
        }

        Toolbar tbMain = findViewById(R.id.main_toolbar);
        setSupportActionBar(tbMain);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_language){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        } else if(item.getItemId() == R.id.setting_reminder){
            Intent mIntent = new Intent(this, SettingReminderActivity.class);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
