package com.gregorius.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*
Ivan
- Get music / podcast / musicVideo / from iTunes
	- Init Retrofit
	- Search bar
	- Init class Media
		trackId (Hidden)
		artworkUrl100 (Rv)
		trackName (Rv)
		artistName (Rv)
		collectionName (Dtl)
		trackViewUrl (Dtl Button)
		category (bikin sendiri)
	- Recycler view and bind to page
	- Detail page di willchris
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}