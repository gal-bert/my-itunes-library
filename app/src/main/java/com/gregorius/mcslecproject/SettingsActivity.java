package com.gregorius.mcslecproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            CheckBoxPreference checkBoxPreference = findPreference("DARK_MODE");
            checkBoxPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();

                    if (checkBoxPreference.isChecked()){
                        checkBoxPreference.setChecked(true);
                        editor.putBoolean("DARK_MODE", true);
                        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else {
                        checkBoxPreference.setChecked(false);
                        editor.putBoolean("DARK_MODE", false);
                        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }

                    editor.apply();

                    return true;
                }
            });

            Preference preference = findPreference("RESET");
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(getActivity(), NameActivity.class);

                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                    editor.remove(NameActivity.KEY_NICKNAME);
                    editor.remove(NameActivity.KEY_DEFAULT_SEARCH);
                    editor.apply();

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    getActivity().finish();
                    return true;
                }
            });

        }
    }
}