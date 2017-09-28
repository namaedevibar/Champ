package com.devibar.champ.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.devibar.champ.Adapter.ViewPagerAdapter;
import com.devibar.champ.Fragment.TaskFragment;
import com.devibar.champ.R;

public class ChildHomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_home);
        setTitle("Home");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.mTabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.child_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.profile){
            Intent intent = new Intent(ChildHomeActivity.this,ChildActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        TaskFragment toDo = TaskFragment.newInstance("To Do");
        TaskFragment onGoing = TaskFragment.newInstance("On-Going");
        TaskFragment completed = TaskFragment.newInstance("Completed");

        adapter.addFragment(toDo,"To Do");
        adapter.addFragment(onGoing,"On-Going");
        adapter.addFragment(completed,"Completed");
        viewPager.setAdapter(adapter);
    }

}
