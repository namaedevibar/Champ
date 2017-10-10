package com.devibar.champ.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devibar.champ.Model.Task;
import com.devibar.champ.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTask;
    private TextView mDescription;
    private TextView mStatus;
    private TextView mLocation;
    private TextView mReward;
    private TextView mGuardian;
    private Button mDone;
    private Firebase taskRewardDB;
    private Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Task");

        mTask = (TextView) findViewById(R.id.txtTask);
        mDescription = (TextView) findViewById(R.id.txtDescription);
        mStatus = (TextView) findViewById(R.id.txtStatus);
        mLocation = (TextView) findViewById(R.id.txtLocation);
        mReward = (TextView) findViewById(R.id.txtReward);
        mGuardian = (TextView) findViewById(R.id.txtGuardian);
        mDone = (Button) findViewById(R.id.btnDone);
        taskRewardDB = new Firebase("https://finalsattendanceapp.firebaseio.com/TASK_REWARD");



        if (getIntent()!=null){
            task = getIntent().getParcelableExtra("TASK");
            String guardianName = getIntent().getStringExtra("guardianName");
            mGuardian.setText(guardianName);

            if (task!=null){
                mTask.setText(task.getTaskName());
                mDescription.setText(task.getTaskDescription());
                mStatus.setText(task.getStatus());
                setReward();

                if (task.getStatus().equals("To Do")){
                    mDone.setText("START TASK");
                }

            }
        }

        mDone.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void setReward(){
        taskRewardDB.child(task.getTaskId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String rewardName = dataSnapshot.child("name").getValue(String.class);
                Log.e("taskactivity",dataSnapshot.toString());
                mReward.setText(rewardName);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnDone){
            if (task.getStatus().equals("On-Going")){
                // TODO: Start ang task
            }else {
                // TODO: Finish ang task
            }
        }
    }
}
