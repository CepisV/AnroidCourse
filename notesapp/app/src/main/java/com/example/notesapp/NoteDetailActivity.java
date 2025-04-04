package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class NoteDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        Intent intent = getIntent();
        Note note = new Note(
                intent.getStringExtra("title"),
                intent.getStringExtra("desc"),
                intent.getStringExtra("date")
        );

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.note_detail_container, NoteDetailFragment.newInstance(note))
                .commit();
    }
}

