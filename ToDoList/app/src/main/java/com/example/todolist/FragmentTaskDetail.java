package com.example.todolist;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentTaskDetail extends Fragment {

    private TextView textTitle, textDescription;
    private Button btnDelete;
    private int taskIndex = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        textTitle = view.findViewById(R.id.textDetailTitle);
        textDescription = view.findViewById(R.id.textDetailDescription);
        btnDelete = view.findViewById(R.id.btnDelete);

        Bundle args = getArguments();
        if (args != null) {
            taskIndex = args.getInt("taskIndex", -1);
        }

        MainActivity mainActivity = (MainActivity) getActivity();

        if (taskIndex >= 0 && taskIndex < mainActivity.taskList.size()) {
            Task task = mainActivity.taskList.get(taskIndex);
            textTitle.setText(task.getTitle());
            textDescription.setText(task.getDescription());

            btnDelete.setOnClickListener(v -> {
                mainActivity.taskList.remove(taskIndex);
                mainActivity.getSupportFragmentManager().popBackStack();
            });
        }

        return view;
    }
}
