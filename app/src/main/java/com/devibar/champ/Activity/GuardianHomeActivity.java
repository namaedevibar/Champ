package com.devibar.champ.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.devibar.champ.Adapter.ChildAdapter;
import com.devibar.champ.Fragment.AddChildDialogFragment;
import com.devibar.champ.Model.Child;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

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
        Firebase.setAndroidContext(this);

        childlist = new ArrayList<>();


        id = getIntent().getStringExtra("id");
        if(getIntent().getStringExtra("type").equals("search")){

            search();

        }else{
            normal();
            getNumberofTasks();
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

        // TODO: Palihug ko butang sa card ang number sa tasks nga pending  

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

    public void getNumberofTasks(){
        childTaskDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD_TASK");
         Log.e("w3w","wala daw");
        for(int i = 0; i < childlist.size(); i++){
            childTaskDB.child(childlist.get(i).getChild_id()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.e("160guardianhome",dataSnapshot.getChildrenCount()+"");
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
        //android.app.FragmentManager fragmentManager = getFragmentManager();
        AddChildDialogFragment addChildDialogFragment = new AddChildDialogFragment();
        Bundle n = new Bundle();
        n.putString("id",id);
        addChildDialogFragment.setArguments(n);
        addChildDialogFragment.show(getSupportFragmentManager(),"Add Child");

    }


}
