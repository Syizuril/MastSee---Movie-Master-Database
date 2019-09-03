package id.syizuril.app.mastsee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_reminder);
        getSupportFragmentManager().beginTransaction().add(R.id.setting_reminder, new SettingReminderFragment()).commit();
    }
}
