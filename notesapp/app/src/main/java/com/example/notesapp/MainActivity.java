package com.example.notesapp;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new NoteListFragment())
                    .commit();
        }
    }

    public void openNoteDetail(Note note) {
        if (getResources().getBoolean(R.bool.is_landscape)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, NoteDetailFragment.newInstance(note))
                    .commit();
        } else {
            Intent intent = new Intent(this, NoteDetailActivity.class);
            intent.putExtra("title", note.getTitle());
            intent.putExtra("desc", note.getDescription());
            intent.putExtra("date", note.getDate());
            startActivity(intent);
        }
    }
}
