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
import com.devibar.champ.Model.Child;
import com.devibar.champ.Model.Task;
import com.devibar.champ.R;
import com.firebase.client.Firebase;

import java.util.ArrayList;

public class ChildProfileActivity extends AppCompatActivity implements View.OnClickListener,
        OnConfirmListener, OnManageTaskListener {

    private TextView mChildName;
    private TextView mBirthday;
    private TextView mAddress;
    private TextView mGuardian;

    private RecyclerView rvTasks;

    private Button mAddChild;
    private Button mAddTask;
    ArrayList<Child> child;
    private TaskAdapter mAdapter;
    Firebase guardianChildDB, childDB;
    String firstName, parent_id;
    String lastName;
    String user_id, status, child_id, guardian_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_child_profile);
        getSupportActionBar().hide();
        child = new ArrayList<>();

        firstName = getIntent().getStringExtra("first name");
        lastName = getIntent().getStringExtra("last name");
        user_id = getIntent().getStringExtra("user_id");
        status = getIntent().getStringExtra("status");
        child_id = getIntent().getStringExtra("child_id");
        guardian_id = getIntent().getStringExtra("guardian_id");
        parent_id = getIntent().getStringExtra("parent_id");


        //yoww himoi og if diri nga if guardian_id == parent_id kay dili nah add child ang button kundi text nah nga your child


        guardianChildDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIANCHILDREN");
        childDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD");

        mChildName = (TextView) findViewById(R.id.txtName);
        mBirthday = (TextView) findViewById(R.id.txtBirthday);
        mAddress = (TextView) findViewById(R.id.txtAddress);
        mGuardian = (TextView) findViewById(R.id.txtGuardian);

        mChildName.setText(firstName + " "+ lastName);

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
            //Toast.makeText(this, "ADDED!", Toast.LENGTH_SHORT).show();
            String child_id = getIntent().getStringExtra("child_id");
            String parent_id = getIntent().getStringExtra("parent_id");
            childDB.child(child_id).child("info").child("guardian_id").setValue(parent_id);
            
            Child child = new Child(user_id,child_id,parent_id,firstName,lastName,status);
            guardianChildDB.child(parent_id).child(child_id).setValue(child);

            Toast.makeText(this, "Succesfully added "+firstName + " "+lastName, Toast.LENGTH_SHORT).show();


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
