package com.devibar.champ.Activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.devibar.champ.Adapter.ChildAdapter;
import com.devibar.champ.Controller.ChildController;
import com.devibar.champ.Fragments.AddChild;
import com.devibar.champ.Model.Child;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GuardianHomeActivity extends AppCompatActivity {


    Firebase guardianChildrenDB;
    Firebase childTaskDB;
    private RecyclerView mRvChildren;
    private ChildAdapter mAdapter;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ArrayList<Child> childlist;
// ...

    Query query;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_home);
        setTitle("Home");

        childlist = new ArrayList<>();


        id = getIntent().getStringExtra("id");
        if(getIntent().getStringExtra("type").equals("search")){

            search();

        }else{
            normal();
        }

        Log.e("line43atay",id);
        mAuth = FirebaseAuth.getInstance();





       /* query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("animal60",dataSnapshot.toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/


    }

    public void search(){

        childlist = (ArrayList<Child>)getIntent().getSerializableExtra("list");

        mRvChildren = (RecyclerView) findViewById(R.id.rvChild);

        mRvChildren.setLayoutManager(new GridLayoutManager(GuardianHomeActivity.this, 2));

        mAdapter = new ChildAdapter(childlist,id);

        mRvChildren.setAdapter(mAdapter);



    }

    public void normal(){
        guardianChildrenDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIANCHILDREN/"+id);

        guardianChildrenDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.e("sdsd",dataSnapshot.toString());
                Child child = dataSnapshot.getValue(Child.class);
                Log.e("childdasd",child.getFirstName());


                if(child.getGuardian_id().equals(id)) {
                    childlist.add(child);
                    Log.e("73atay",childlist.size()+" = "+dataSnapshot.getChildrenCount());


                    mRvChildren = (RecyclerView) findViewById(R.id.rvChild);

                    mRvChildren.setLayoutManager(new GridLayoutManager(GuardianHomeActivity.this, 2));

                    mAdapter = new ChildAdapter(childlist,id);

                    mRvChildren.setAdapter(mAdapter);


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
        getMenuInflater().inflate(R.menu.guardian_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.search:
                addChild();
                break;
            case R.id.logout:
                mAuth.signOut();
                Intent intent = new Intent(GuardianHomeActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addChild(){
        android.app.FragmentManager fragmentManager = getFragmentManager();
        AddChild addChild = new AddChild();
        Bundle n = new Bundle();
        n.putString("id",id);
        addChild.setArguments(n);
        addChild.show(fragmentManager,"Add Child");

    }


}
