package id.syizuril.app.mastsee;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.widget.Toast;

import java.util.Objects;

public class SettingReminderFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String DAILY, RELEASE, LOVE;
    private SwitchPreferenceCompat dailyPreference, releasePreferences;
    private DailyReminderReceiver dailyReminderReceiver;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
        init();
        setSettings();
    }

    private void init(){
        DAILY = getResources().getString(R.string.key_daily);
        RELEASE = getResources().getString(R.string.key_release);

        dailyPreference = (SwitchPreferenceCompat) findPreference(DAILY);
        releasePreferences =  (SwitchPreferenceCompat) findPreference(RELEASE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(DAILY)){
            boolean isActive = sharedPreferences.getBoolean(DAILY, false);
            dailyPreference.setChecked(isActive);

            if(isActive){
                dailyReminderReceiver.setDailyReminder(Objects.requireNonNull(getActivity()));
                Toast.makeText(getActivity(), "Reminder Berhasil Diaktifkan", Toast.LENGTH_SHORT).show();
            }else {
                dailyReminderReceiver.cancelDailyReminder(getActivity());
                Toast.makeText(getActivity(), "Reminder Berhasil Dinonaktifkan", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setSettings(){
        SharedPreferences sh = getPreferenceManager().getSharedPreferences();
        dailyPreference.setChecked(sh.getBoolean(DAILY, false));
        dailyReminderReceiver = new DailyReminderReceiver();
    }
}
