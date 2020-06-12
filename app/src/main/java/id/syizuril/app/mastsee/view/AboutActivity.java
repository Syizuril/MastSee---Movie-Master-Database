package id.syizuril.app.mastsee.view;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import id.syizuril.app.mastsee.R;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar tbBack = findViewById(R.id.tbBack);
        TextView tvToolbarTitle = findViewById(R.id.toolbar_title);
        tvToolbarTitle.setText(this.getString(R.string.about));

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
        }else if(item.getItemId() == R.id.about){
            Intent mIntent = new Intent(this, AboutActivity.class);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}