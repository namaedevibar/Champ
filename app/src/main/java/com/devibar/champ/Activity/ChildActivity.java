package com.devibar.champ.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devibar.champ.Adapter.RewardAdapter;
import com.devibar.champ.Controller.RewardController;
import com.devibar.champ.Fragment.AddWishDialogFragment;
import com.devibar.champ.Fragment.EditChildDialogFragment;

import com.devibar.champ.Model.Child;
import com.devibar.champ.Model.Wish;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChildActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mEditProfile;
    private Button mAddWish;
    private RecyclerView mRvRewards;
    private TextView childName, guardianName;
    private String child_name, guardian_name, child_id;
    private RewardAdapter mAdapter;
    Firebase childWishlistDB;
    ArrayList<Wish> wishList;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Profile");
        childName = (TextView)findViewById(R.id.txtName);
        guardianName = (TextView)findViewById(R.id.txtGuardian);
        wishList = new ArrayList<>();

        child_name = getIntent().getStringExtra("childName");
        guardian_name = getIntent().getStringExtra("guardianName");
        child_id = getIntent().getStringExtra("id");
        mAuth = FirebaseAuth.getInstance();

       // Log.e("line54",child_id);
        childName.setText(child_name);
        guardianName.setText(guardian_name);

        setWishlist();


        mEditProfile = (Button) findViewById(R.id.btnEditChild);
        mAddWish = (Button) findViewById(R.id.btnAddWish);
        mRvRewards = (RecyclerView) findViewById(R.id.rvRewards);


        mEditProfile.setOnClickListener(this);
        mAddWish.setOnClickListener(this);


    }
    public void setWishlist(){

        childWishlistDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD_REWARD");

        Log.e("line54",child_id);
        childWishlistDB.child(child_id).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Wish wish = dataSnapshot.getValue(Wish.class);

                if(wish.getStatus().equals("wala pa")){
                    wishList.add(wish);

                    mRvRewards.setLayoutManager(new LinearLayoutManager(ChildActivity.this));
                    mAdapter = new RewardAdapter(wishList);

                    mRvRewards.setAdapter(mAdapter);
                }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout){
            //// TODO: naa ni problem sa pag sign out, wa ko kabaw nganu. please lang ko fix :)
            mAuth.signOut();
            Intent intent = new Intent(ChildActivity.this,LoginActivity.class);
            startActivity(intent);
        }else {
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnEditChild){
            EditChildDialogFragment editChildDialogFragment = EditChildDialogFragment.newInstance();
            editChildDialogFragment.show(getSupportFragmentManager(),"");
        }else {
            AddWishDialogFragment addWishDialogFragment = AddWishDialogFragment.newInstance(child_id);
            addWishDialogFragment.show(getSupportFragmentManager(),"");
        }
    }


}
