package com.devibar.champ.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.devibar.champ.Model.User;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLoginGuardian;
    Firebase IdReference;
    private Button mLoginChild;
    Firebase childDB, userDB, parentDB;
    private TextView mSignUp;
     FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Firebase.setAndroidContext(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mLoginGuardian = (Button) findViewById(R.id.btnLoginGuardian);
        mLoginChild = (Button) findViewById(R.id.btnLoginChild);
        mSignUp = (TextView) findViewById(R.id.txtSignUp);

        childDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD");
        userDB = new Firebase("https://finalsattendanceapp.firebaseio.com/USER");
        parentDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIAN");

        mLoginGuardian.setOnClickListener(this);
        mLoginChild.setOnClickListener(this);
        mSignUp.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!= null){

                    userDB.child(user.getUid()).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            User usermodel = dataSnapshot.getValue(User.class);
                            Log.e("loginactivity68",usermodel.getType());

                            if(usermodel.getType().equals("parent")){
                                IdReference = parentDB.child(mAuth.getCurrentUser().getUid());
                                IdReference.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                        String id = dataSnapshot.getKey();

                                        Log.e("loginactivity92",id);
                                        Log.e("loginactivity93",mAuth.getCurrentUser().getUid());

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(LoginActivity.this,GuardianHomeActivity.class);

                                        intent.putExtra("id",id);
                                        intent.putExtra("type","normal");
                                        startActivity(intent);


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

                            }else{
                                Intent intent = new Intent(LoginActivity.this,ChildProfileActivity.class);

                                startActivity(intent);
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



                }else{
                    Log.e("kobe","user signed out");
                }

            }
        };


    }


    @Override
    public void onStart() {
        super.onStart();
      //  mAuth.signOut();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent;

        if (id == R.id.btnLoginGuardian){
            intent = new Intent(LoginActivity.this,LoginPageActivity.class);
            intent.putExtra("LAYOUT","GUARDIAN");
            startActivity(intent);
        }else if (id == R.id.btnLoginChild){
            intent = new Intent(LoginActivity.this,LoginPageActivity.class);
            intent.putExtra("LAYOUT","CHILD");
            startActivity(intent);
        }else if(id == R.id.txtSignUp){
            intent = new Intent(LoginActivity.this,Register.class);
            startActivity(intent);
        }

    }
}
