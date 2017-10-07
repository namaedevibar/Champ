package com.devibar.champ.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.devibar.champ.Adapter.TaskAdapter;
import com.devibar.champ.Adapter.ViewPagerAdapter;
import com.devibar.champ.Fragment.TaskFragment;
import com.devibar.champ.Model.Task;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class ChildHomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView guardian_name;
    String id;

    String firstName="";
    String lastName = "";
    String guardian_id;
    String guardianName = "";
    Firebase todosDB;
    Firebase guardianDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_home);
        setTitle("Home");
        id = getIntent().getStringExtra("id");
        guardian_name = (TextView)findViewById(R.id.txtGuardian);
        todosDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD_TASK");
        guardianDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIAN");
        guardian_id = getIntent().getStringExtra("guardian_id");
        Log.e("guardian_idline42",guardian_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if(guardian_id.equals("wala pa")){

        }else{
            setGuardian(viewPager);
        }



        //setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.mTabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setGuardian(final ViewPager viewPager){


        guardianDB.child(guardian_id).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //String guardianName = dataSnapshot.child("firstName");
                if(dataSnapshot.getKey().equals("firstName")){
                    firstName = dataSnapshot.getValue(String.class);

                }else if(dataSnapshot.getKey().equals("lastName")){
                    lastName = dataSnapshot.getValue(String.class);
                    guardian_name.setText(firstName + " "+ lastName);

                    guardianName = firstName + " " + lastName;
                    Log.e("childhomeactivity",guardianName);

                      setupViewPager(viewPager);

                }


//
                Log.e("line60",dataSnapshot.toString());
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
        getMenuInflater().inflate(R.menu.child_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.profile) {
            String name = getIntent().getStringExtra("name");

            Intent intent = new Intent(ChildHomeActivity.this, ChildActivity.class);


            intent.putExtra("childName",name);
            intent.putExtra("guardianName",guardianName);
            intent.putExtra("id",id);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
       // String guardianName = firstName + " " + lastName;

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        TaskFragment toDo = TaskFragment.newInstance("To Do", id, guardianName);
        TaskFragment onGoing = TaskFragment.newInstance("On-Going", id,guardianName);
        TaskFragment completed = TaskFragment.newInstance("Completed", id,guardianName);

        adapter.addFragment(toDo, "To Do");
        adapter.addFragment(onGoing, "On-Going");
        adapter.addFragment(completed, "Completed");
        viewPager.setAdapter(adapter);
    }




}
