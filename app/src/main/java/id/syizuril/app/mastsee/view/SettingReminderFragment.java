package id.syizuril.app.mastsee.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.widget.Toast;

import java.util.Objects;

import id.syizuril.app.mastsee.services.DailyReleaseReceiver;
import id.syizuril.app.mastsee.services.DailyReminderReceiver;
import id.syizuril.app.mastsee.R;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class SettingReminderFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String DAILY;
    private String RELEASE;
    private SwitchPreferenceCompat dailyPreference, releasePreferences;
    private DailyReminderReceiver dailyReminderReceiver;
    private DailyReleaseReceiver dailyReleaseReceiver;

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
                Toast.makeText(getActivity(), getResources().getString(R.string.reminder_activated), Toast.LENGTH_SHORT).show();
            }else {
                dailyReminderReceiver.cancelDailyReminder(Objects.requireNonNull(getActivity()));
                Toast.makeText(getActivity(), getResources().getString(R.string.reminder_deactivated), Toast.LENGTH_SHORT).show();
            }
        }
        if(key.equals(RELEASE)){
            boolean isActive = sharedPreferences.getBoolean(RELEASE, false);
            releasePreferences.setChecked(isActive);

            if(isActive){
                dailyReleaseReceiver.setDailyReminder(Objects.requireNonNull(getActivity()));
                Toast.makeText(getActivity(), getResources().getString(R.string.reminder_activated), Toast.LENGTH_SHORT).show();
            }else {
                dailyReleaseReceiver.cancelDailyReminder(Objects.requireNonNull(getActivity()));
                Toast.makeText(getActivity(), getResources().getString(R.string.reminder_deactivated), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setSettings(){
        SharedPreferences sh = getPreferenceManager().getSharedPreferences();
        dailyPreference.setChecked(sh.getBoolean(DAILY, false));
        dailyReminderReceiver = new DailyReminderReceiver();
        releasePreferences.setChecked(sh.getBoolean(RELEASE, false));
        dailyReleaseReceiver = new DailyReleaseReceiver();
    }
}
