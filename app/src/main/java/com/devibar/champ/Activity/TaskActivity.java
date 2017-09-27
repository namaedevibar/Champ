package com.devibar.champ.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.devibar.champ.Model.Task;
import com.devibar.champ.R;

public class TaskActivity extends AppCompatActivity {

    private TextView mTask;
    private TextView mDescription;
    private TextView mStatus;
    private TextView mLocation;
    private TextView mReward;
    private TextView mGuardian;

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

        if (getIntent()!=null){
            task = getIntent().getParcelableExtra("TASK");
            if (task!=null){
                mTask.setText(task.getTaskName());
                mDescription.setText(task.getTaskDescription());
                mStatus.setText(task.getStatus());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
