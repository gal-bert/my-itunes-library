package com.gregorius.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMedia;
    EditText etKeyword;
    Button btnSearch;
    ProgressBar progressBar;

    MediaAdapter mediaAdapter;
    List<MediaResponse> listMusic;

    /*Default search media*/
    String media = "music";
    String term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        term = sPrefs.getString("DEFAULT_SEARCH", null);

        renderBottomNavbar();

        rvMedia = findViewById(R.id.rvMedia);
        etKeyword = findViewById(R.id.etKeyword);
        btnSearch = findViewById(R.id.btnSearch);
        progressBar = findViewById(R.id.progressBar);

        // Hide progress bar
        progressBar.setVisibility(ProgressBar.GONE);

        LinearLayoutManager llManager = new LinearLayoutManager(this);
        rvMedia.setLayoutManager(llManager);

        mediaAdapter = new MediaAdapter(this);
        rvMedia.setAdapter(mediaAdapter);

        // Loading Data for the first time with the term from Shared Preferences
        search(media, term);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String term = etKeyword.getText().toString();

                if(term.isEmpty()){
                    etKeyword.setError("Term must be filled");
                    etKeyword.requestFocus();
                }
                else search(media, term);
            }
        });

    }

    private void search(String media, String term) {
        progressBar.setVisibility(ProgressBar.VISIBLE);

        Retrofit retrofit = APIClient.getRetrofit();
        iTunesService service = retrofit.create(iTunesService.class);

        Call<MediaListResponse> call = service.searchMedia(media, term);
        call.enqueue(new Callback<MediaListResponse>() {
            @Override
            public void onResponse(Call<MediaListResponse> call, Response<MediaListResponse> response) {
                progressBar.setVisibility(ProgressBar.GONE);

                if (response.isSuccessful()) {
                    MediaListResponse mediaListResponse = response.body();
                    listMusic = mediaListResponse.getResults();

                    mediaAdapter.setListMedia((ArrayList<MediaResponse>) listMusic);

                    // Log.i("MY_TAG", "onResponse: " + listMusic.get(0).getTrackName());
                }
            }

            @Override
            public void onFailure(Call<MediaListResponse> call, Throwable t) {
                progressBar.setVisibility(ProgressBar.GONE);
            }
        });
    }

    private void renderBottomNavbar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.menuMusic:
                        bottomNavigationView.getMenu().findItem(R.id.menuMusic).setChecked(true);
                        etKeyword.setHint("Find music...");
                        media = "music";
                        search(media, term);
                        break;

                    case R.id.menuVideo:
                        bottomNavigationView.getMenu().findItem(R.id.menuVideo).setChecked(true);
                        etKeyword.setHint("Find music video...");
                        media = "musicVideo";
                        search(media,term);
                        break;

                    case R.id.menuPodcast:
                        bottomNavigationView.getMenu().findItem(R.id.menuPodcast).setChecked(true);
                        etKeyword.setHint("Find podcast...");
                        media = "podcast";
                        search(media, term);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuBookmark) {
            /*TODO: Assign to bookmark Activity*/
            startActivity(new Intent(this, BookmarksActivity.class));
        }
        if (item.getItemId() == R.id.menuSettings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}