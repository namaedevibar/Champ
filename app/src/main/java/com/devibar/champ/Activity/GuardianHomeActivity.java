package com.devibar.champ.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.devibar.champ.Adapter.ChildAdapter;
import com.devibar.champ.Controller.ChildController;
import com.devibar.champ.R;

public class GuardianHomeActivity extends AppCompatActivity {

    private RecyclerView mRvChildren;
    private ChildAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_home);
        setTitle("Home");

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

        return super.onOptionsItemSelected(item);
    }

}
