package com.devibar.champ.Activity;

import android.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.devibar.champ.Adapter.ChildAdapter;
import com.devibar.champ.Controller.ChildController;
import com.devibar.champ.Fragments.AddChild;
import com.devibar.champ.R;
import com.google.firebase.auth.FirebaseAuth;

public class GuardianHomeActivity extends AppCompatActivity {

    private RecyclerView mRvChildren;
    private ChildAdapter mAdapter;
    private FirebaseAuth mAuth;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_home);
        setTitle("Home");


        id = getIntent().getStringExtra("id");
        mAuth = FirebaseAuth.getInstance();
        mRvChildren = (RecyclerView) findViewById(R.id.rvChild);

        mRvChildren.setLayoutManager(new GridLayoutManager(this,2));

        mAdapter = new ChildAdapter(ChildController.getChildren());

        mRvChildren.setAdapter(mAdapter);


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
