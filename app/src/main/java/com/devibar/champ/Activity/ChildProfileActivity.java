package com.devibar.champ.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.devibar.champ.Model.Wish;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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
    Firebase taskReward;

    ArrayList<Task> tasksList;
    private TaskAdapter mAdapter;
    Firebase guardianChildDB, childDB, childTaskDB, guardianTaskDB, guardianDB;
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
        tasksList = new ArrayList<>();

        mChildName = (TextView) findViewById(R.id.txtName);
        mBirthday = (TextView) findViewById(R.id.txtBirthday);
        mAddress = (TextView) findViewById(R.id.txtAddress);
        mGuardian = (TextView) findViewById(R.id.txtGuardian);



        rvTasks = (RecyclerView) findViewById(R.id.rvTasks);

        mAddChild = (Button) findViewById(R.id.btnAddChild);
        mAddTask = (Button) findViewById(R.id.btnAddTask);
        mAddChild.setOnClickListener(this);
        mAddTask.setOnClickListener(this);

        firstName = getIntent().getStringExtra("first name");
        lastName = getIntent().getStringExtra("last name");
        user_id = getIntent().getStringExtra("user_id");
        status = getIntent().getStringExtra("status");
        child_id = getIntent().getStringExtra("child_id");
        guardian_id = getIntent().getStringExtra("guardian_id");


        taskReward = new Firebase("https://finalsattendanceapp.firebaseio.com/TASK_REWARD");
        guardianChildDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIANCHILDREN");
        childDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD");
        guardianTaskDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIAN_TASKS");
        childTaskDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD_TASK");
        guardianDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIAN");

        Log.e("fuck","firstName "+ firstName + "lastName "+lastName + "user_id "+user_id + " status "+status + " child_id"+child_id+"guardian_id"+guardian_id);

        if(getIntent().getStringExtra("classBefore").equals("RegisterActivity")){

            //i disable ang add child button og add task button
            if(guardian_id != null){
                mAddChild.setVisibility(View.GONE);
            }

        }else{
            parent_id = getIntent().getStringExtra("parent_id");

            if(guardian_id != null){
                //nanay mama
                if(guardian_id.equals(parent_id)){//if ang guardian sa child kay ang ga view sa profile sa child
                    //ipakita tong button nga add task nya katong textview kay your child
                    mAddChild.setVisibility(View.GONE);
                    mAddTask.setVisibility(View.VISIBLE);

                    setGuardian();
                }else{
                    //i disable ang add task nga button og ang textview himoa lang sa og dili ni imong anak haha
                    mAddTask.setVisibility(View.GONE);
                }

            }else{
               mAddChild.setText(View.VISIBLE);
            }
        }

        mChildName.setText(firstName + " "+ lastName);







        tasks();

    }

    public void setGuardian(){
        Log.e("fuck2",guardian_id);
        guardianDB.child(guardian_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("atayasdas",dataSnapshot.toString());
                String name = dataSnapshot.child("firstName").getValue(String.class) + " " + dataSnapshot.child("lastName").getValue(String.class);
                mGuardian.setText(name);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void tasks(){

        childTaskDB.child(child_id).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Task task = dataSnapshot.getValue(Task.class);

                tasksList.add(task);
                mAdapter = new TaskAdapter(tasksList,getSupportFragmentManager(),child_id);
                rvTasks.setLayoutManager(new LinearLayoutManager(ChildProfileActivity.this));
                rvTasks.setAdapter(mAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnAddChild){
            ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment
                    .newInstance("Are you sure you want to add this child?");
            confirmDialogFragment.show(getSupportFragmentManager(),"");
        }else {
            ManageTaskDialogFragment manageTaskDialogFragment = ManageTaskDialogFragment.newInstance("ADD",child_id);
            manageTaskDialogFragment.show(getSupportFragmentManager(),"");
        }

    }

    @Override
    public void response(Boolean b) {
        if (b){
            //Toast.makeText(this, "ADDED!", Toast.LENGTH_SHORT).show();
            String child_id = getIntent().getStringExtra("child_id");
            String parent_id = getIntent().getStringExtra("parent_id");
            childDB.child(child_id).child("guardian_id").setValue(parent_id);

            
            Child child = new Child(user_id,child_id,parent_id,firstName,lastName,status);
            guardianChildDB.child(parent_id).child(child_id).setValue(child);

            Toast.makeText(this, "Succesfully added "+firstName + " "+lastName, Toast.LENGTH_SHORT).show();


        }
    }


    @Override
    public void manageTask(String purpose, Task task, Wish wish) {

        if (purpose.equals("ADD")){

            String task_id = childTaskDB.push().getKey();

            task.setTaskId(task_id);
            task.setStatus("To Do");

            childTaskDB.child(child_id).child(task_id).setValue(task);
            guardianTaskDB.child(parent_id).child(child_id).child(task_id).setValue(task);
            
            if(wish!=null)
            taskReward.child(task_id).setValue(wish);


            Toast.makeText(this, task.getTaskName() +" added!", Toast.LENGTH_SHORT).show();
        }else   if (purpose.equals("DONE")){
            childTaskDB.child(child_id).child(task.getTaskId()).setValue(task);
            //taskReward.child(task.getTaskId()).setValue(wish);
            Toast.makeText(this, "Task is completed!", Toast.LENGTH_SHORT).show();
        } else {
            childTaskDB.child(child_id).child(task.getTaskId()).setValue(task);
            taskReward.child(task.getTaskId()).setValue(wish);
            //

            Toast.makeText(this, task.getTaskName() +" edited!", Toast.LENGTH_SHORT).show();
        }

    }


}
