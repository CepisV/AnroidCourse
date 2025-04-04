package com.example.notesapp;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NoteListFragment extends Fragment {

    private List<Note> noteList;

    public NoteListFragment() {
        noteList = new ArrayList<>();
        noteList.add(new Note("Заметка 1", "Описание заметки 1", "2025-04-04"));
        noteList.add(new Note("Заметка 2", "Описание заметки 2", "2025-04-01"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        ListView listView = view.findViewById(R.id.noteListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                noteList.stream().map(Note::getTitle).collect(Collectors.toList())
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Note note = noteList.get(position);
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).openNoteDetail(note);
            }
        });

        return view;
    }
}
