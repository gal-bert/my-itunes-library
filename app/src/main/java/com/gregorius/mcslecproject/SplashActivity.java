package com.gregorius.mcslecproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkMode = sPrefs.getBoolean("DARK_MODE", false);

        if(darkMode){
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        String name = sPrefs.getString(NameActivity.KEY_NICKNAME, null);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent;
                    if(name == null){
                        intent = new Intent(SplashActivity.this, NameActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };
        thread.start();

    }


}


