package com.devibar.champ.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.devibar.champ.Adapter.RewardAdapter;
import com.devibar.champ.Controller.RewardController;
import com.devibar.champ.Fragment.AddWishDialogFragment;
import com.devibar.champ.Fragment.EditChildDialogFragment;
import com.devibar.champ.Interface.OnEditChildListener;
import com.devibar.champ.Model.Child;
import com.devibar.champ.R;

public class ChildActivity extends AppCompatActivity implements View.OnClickListener, OnEditChildListener {

    private Button mEditProfile;
    private Button mAddWish;
    private RecyclerView mRvRewards;

    private RewardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Profile");

        mEditProfile = (Button) findViewById(R.id.btnEditChild);
        mAddWish = (Button) findViewById(R.id.btnAddWish);
        mRvRewards = (RecyclerView) findViewById(R.id.rvRewards);

        mRvRewards.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RewardAdapter(RewardController.getRewards());

        mRvRewards.setAdapter(mAdapter);


        mEditProfile.setOnClickListener(this);
        mAddWish.setOnClickListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnEditChild){
            EditChildDialogFragment editChildDialogFragment = EditChildDialogFragment.newInstance();
            editChildDialogFragment.show(getSupportFragmentManager(),"");
        }else {
            AddWishDialogFragment addWishDialogFragment = AddWishDialogFragment.newInstance();
            addWishDialogFragment.show(getSupportFragmentManager(),"");
        }
    }

    @Override
    public void editChild(Child child) {
        // TODO: Edit Child
        Toast.makeText(this, "Edited", Toast.LENGTH_SHORT).show();
    }
}
