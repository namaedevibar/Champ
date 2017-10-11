package com.devibar.champ.Fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devibar.champ.Activity.ChildProfileActivity;
import com.devibar.champ.Adapter.TaskAdapter;
import com.devibar.champ.Controller.TaskController;
import com.devibar.champ.Model.Task;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Created by namai on 9/27/2017.
 */

public class TaskFragment extends Fragment {

    private RecyclerView mRvTasks;
    private TaskAdapter mAdapter;
    private ConstraintLayout mNoTasks;
    ArrayList<Task> taskList;
    Firebase todosDB;
    String status;


    public static TaskFragment newInstance(String status, String id, String guardianName) {

        Bundle args = new Bundle();
        args.putString("STATUS",status);
        args.putString("id",id);
        args.putString("guardianName",guardianName);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static TaskFragment newInstance(String status, String id) {

        Bundle args = new Bundle();
        args.putString("STATUS",status);
        args.putString("id",id);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task, container, false);
        taskList = new ArrayList<>();
        mNoTasks = view.findViewById(R.id.noTasks);

        status = getArguments().getString("STATUS");
        String id = getArguments().getString("id");



        todosDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD_TASK");


        mRvTasks = view.findViewById(R.id.rvTasks);
        mRvTasks.setLayoutManager(new LinearLayoutManager(getContext()));



        tasks();

        return view;

    }

    public void tasks(){

        final String id = getArguments().getString("id");
        final String guardianName = getArguments().getString("guardianName");
        if (guardianName!=null){
            Log.e("PISTE ATAY",guardianName + " ");
            todosDB.child(id).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Task task = dataSnapshot.getValue(Task.class);
                    Log.e("taskfragmentline100",dataSnapshot.toString());

                    if (status.equals("On-Going")){
                        if (task.getStatus().equals(status) || task.getStatus().equals("Pending")){
                            taskList.add(task);
                        }
                    }else if(status.equals("To Do")){
                        if (task.getStatus().equals(status)){
                            taskList.add(task);
                        }
                    }else{
                        if (task.getStatus().equals(status)){
                            taskList.add(task);
                        }
                    }



                    Log.e("line96",task.getTaskName());

                    if  (!taskList.isEmpty()){

                        mNoTasks.setVisibility(View.GONE);
                        mAdapter = new TaskAdapter(taskList,guardianName,id);
                        mRvTasks.setAdapter(mAdapter);
                    }

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Task task = dataSnapshot.getValue(Task.class);
                   /* for(int i = 0; i < taskList.size(); i++){
                            if(taskList.get(i).getTaskId().equals(task.getTaskId())){
                                taskList.set(i,task);
                                mAdapter.notifyDataSetChanged();

                                mNoTasks.setVisibility(View.GONE);
                                mAdapter = new TaskAdapter(taskList,guardianName,id);
                                mRvTasks.setAdapter(mAdapter);
                            }
                    }
*/
                    taskList.clear();
                    Log.e("paghumanClear",taskList.size()+"");
                    if(mAdapter!=null)
                    mAdapter.notifyDataSetChanged();
                    tasks();

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }

    }








}
