package com.devibar.champ.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.devibar.champ.Adapter.TaskAdapter;
import com.devibar.champ.Controller.TaskController;
import com.devibar.champ.Fragment.ManageTaskDialogFragment;
import com.devibar.champ.Fragment.ConfirmDialogFragment;
import com.devibar.champ.Interface.OnConfirmListener;
import com.devibar.champ.Interface.OnManageTaskListener;
import com.devibar.champ.Model.Task;
import com.devibar.champ.R;

public class ChildProfileActivity extends AppCompatActivity implements View.OnClickListener,
        OnConfirmListener, OnManageTaskListener {

    private TextView mChildName;
    private TextView mBirthday;
    private TextView mAddress;
    private TextView mGuardian;

    private RecyclerView rvTasks;

    private Button mAddChild;
    private Button mAddTask;

    private TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_child_profile);
        getSupportActionBar().hide();

        mChildName = (TextView) findViewById(R.id.txtName);
        mBirthday = (TextView) findViewById(R.id.txtBirthday);
        mAddress = (TextView) findViewById(R.id.txtAddress);
        mGuardian = (TextView) findViewById(R.id.txtGuardian);

        rvTasks = (RecyclerView) findViewById(R.id.rvTasks);

        mAddChild = (Button) findViewById(R.id.btnAddChild);
        mAddTask = (Button) findViewById(R.id.btnAddTask);


        mAdapter = new TaskAdapter(TaskController.getTasks(),getSupportFragmentManager());
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(mAdapter);


        mAddChild.setOnClickListener(this);
        mAddTask.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnAddChild){
            ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment
                    .newInstance("Are you sure you want to add this child?");
            confirmDialogFragment.show(getSupportFragmentManager(),"");
        }else {
            ManageTaskDialogFragment manageTaskDialogFragment = ManageTaskDialogFragment.newInstance("ADD");
            manageTaskDialogFragment.show(getSupportFragmentManager(),"");
        }

    }

    @Override
    public void response(Boolean b) {
        if (b){
            //TODO: Add Child
            Toast.makeText(this, "ADDED!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void manageTask(String purpose, Task task) {
        if (purpose.equals("ADD")){
            // TODO: Add Task 
            Toast.makeText(this, task.getTaskName() +" added!", Toast.LENGTH_SHORT).show();
        }else {
            // TODO: Edit Task 
            Toast.makeText(this, task.getTaskName() +" edited!", Toast.LENGTH_SHORT).show();
        }
    }
}
