package com.example.intentapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private Button buttonUpper, buttonOriginal;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        buttonUpper = findViewById(R.id.buttonUpper);
        buttonOriginal = findViewById(R.id.buttonOriginal);

        name = getIntent().getStringExtra("user_name");

        buttonUpper.setOnClickListener(view -> returnResult(name.toUpperCase()));
        buttonOriginal.setOnClickListener(view -> returnResult(name));
    }

    private void returnResult(String result) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result_name", result);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
