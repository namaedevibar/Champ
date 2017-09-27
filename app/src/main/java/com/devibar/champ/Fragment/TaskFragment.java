package com.devibar.champ.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devibar.champ.Adapter.TaskAdapter;
import com.devibar.champ.Controller.TaskController;
import com.devibar.champ.Model.Task;
import com.devibar.champ.R;

import java.util.ArrayList;

/**
 * Created by namai on 9/27/2017.
 */

public class TaskFragment extends Fragment {

    private RecyclerView mRvTasks;
    private TaskAdapter mAdapter;


    public static TaskFragment newInstance(String status) {

        Bundle args = new Bundle();
        args.putString("STATUS",status);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task, container, false);

        String status = getArguments().getString("STATUS");

        mRvTasks = view.findViewById(R.id.rvTasks);
        mRvTasks.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Task> tasks = TaskController.getTasks();
        ArrayList<Task> statusTask = new ArrayList<>();

        for (Task t:tasks) {
            if (t.getStatus().equals(status)){
                statusTask.add(t);
            }
        }

        mAdapter = new TaskAdapter(statusTask);
        mRvTasks.setAdapter(mAdapter);


        return view;

    }





}
