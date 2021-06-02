package com.gregorius.mcslecproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.jar.Attributes;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sPrefs.getString("NICKNAME", null);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(name == null){
                        startActivity(new Intent(MainActivity.this, NameActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(MainActivity.this, ViewMusicActivity.class));
                        finish();
                    }
                }
            }
        };
        thread.start();

    }


}
