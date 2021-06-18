package com.gregorius.mcslecproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NameActivity extends AppCompatActivity {

    public static final String KEY_NICKNAME = "NICKNAME";
    public static final String KEY_DEFAULT_SEARCH = "DEFAULT_SEARCH";

    TextInputLayout nameTextField, searchTextField;
    String nickname, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        nameTextField = findViewById(R.id.nameTextField);
        searchTextField = findViewById(R.id.keywordTextField);

    }

    public void proceed(View view) {

        nickname = nameTextField.getEditText().getText().toString();
        search = searchTextField.getEditText().getText().toString();
        boolean anyErrors = false;

        if (search.isEmpty()){
            searchTextField.setError("Default search term can't be empty!");
            searchTextField.requestFocus();
            anyErrors = true;
        }
        if (nickname.isEmpty()){
            nameTextField.setError("Nickname can't be empty");
            nameTextField.requestFocus();
            anyErrors = true;
        }

        if (!anyErrors){
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            editor.putString(KEY_NICKNAME, nickname);
            editor.putString(KEY_DEFAULT_SEARCH, search);
            editor.apply();

            Intent intent = new Intent(NameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}