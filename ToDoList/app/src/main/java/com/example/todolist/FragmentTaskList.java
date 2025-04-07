package com.example.todolist;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentTaskList extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewTasks);
        fab = view.findViewById(R.id.fabAddTask);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MainActivity mainActivity = (MainActivity) getActivity();
        taskAdapter = new TaskAdapter(mainActivity.taskList, task -> {
            FragmentTaskDetail detailFragment = new FragmentTaskDetail();
            Bundle bundle = new Bundle();
            bundle.putInt("taskIndex", mainActivity.taskList.indexOf(task));
            detailFragment.setArguments(bundle);
            mainActivity.loadFragment(detailFragment, true);
        });

        recyclerView.setAdapter(taskAdapter);

        fab.setOnClickListener(v -> {
            mainActivity.loadFragment(new FragmentAddTask(), true);
        });

        return view;
    }

    public void refreshList() {
        taskAdapter.notifyDataSetChanged();
    }
}
