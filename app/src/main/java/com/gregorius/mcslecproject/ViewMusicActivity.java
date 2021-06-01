package com.gregorius.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewMusicActivity extends AppCompatActivity {

    RecyclerView rvMusic;
    EditText etKeyword;
    Button btnSearch;

    MusicAdapter musicAdapter;
    List<MediaResponse> listMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_music);

        rvMusic = findViewById(R.id.rvMusic);
        etKeyword = findViewById(R.id.etKeyword);
        btnSearch = findViewById(R.id.btnSearch);

        LinearLayoutManager llManager = new LinearLayoutManager(this);
        rvMusic.setLayoutManager(llManager);

        musicAdapter = new MusicAdapter(this);
        rvMusic.setAdapter(musicAdapter);

        // Loading Data for the first time with the term 'love'
        search("love");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String term = etKeyword.getText().toString();
                search(term);
            }
        });

    }

    private void search(String term) {
        Retrofit retrofit = APIClient.getRetrofit();
        iTunesService service = retrofit.create(iTunesService.class);

        Call<MediaListResponse> call = service.searchMedia("music", term);
        call.enqueue(new Callback<MediaListResponse>() {
            @Override
            public void onResponse(Call<MediaListResponse> call, Response<MediaListResponse> response) {
                if (response.isSuccessful()) {
                    MediaListResponse mediaListResponse = response.body();
                    listMusic = mediaListResponse.getResults();

                    musicAdapter.setListMedia((ArrayList<MediaResponse>) listMusic);

                    // Log.i("MY_TAG", "onResponse: " + listMusic.get(0).getTrackName());
                }
            }

            @Override
            public void onFailure(Call<MediaListResponse> call, Throwable t) {

            }
        });
    }
}