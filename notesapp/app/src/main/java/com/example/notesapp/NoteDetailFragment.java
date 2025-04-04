package com.example.notesapp;

import android.app.DatePickerDialog;
import androidx.fragment.app.Fragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NoteDetailFragment extends Fragment {
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESC = "desc";
    private static final String ARG_DATE = "date";

    public static androidx.fragment.app.Fragment newInstance(Note note) {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, note.getTitle());
        args.putString(ARG_DESC, note.getDescription());
        args.putString(ARG_DATE, note.getDate());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);

        TextView title = view.findViewById(R.id.noteTitle);
        TextView description = view.findViewById(R.id.noteDesc);
        TextView date = view.findViewById(R.id.noteDate);
        Button dateButton = view.findViewById(R.id.datePickerButton);

        if (getArguments() != null) {
            title.setText(getArguments().getString(ARG_TITLE));
            description.setText(getArguments().getString(ARG_DESC));
            date.setText(getArguments().getString(ARG_DATE));
        }

        dateButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(
                    requireContext(),
                    (view1, year, month, day) -> {
                        String newDate = String.format("%04d-%02d-%02d", year, month + 1, day);
                        date.setText(newDate);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            dialog.show();
        });

        return view;
    }
}
