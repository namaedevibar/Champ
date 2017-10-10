package com.devibar.champ.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.devibar.champ.Model.Child;
import com.devibar.champ.Model.User;
import com.devibar.champ.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreenActivity extends AppCompatActivity {
    Firebase childDB, userDB, parentDB;
    FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        Firebase.setAndroidContext(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        childDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD");
        userDB = new Firebase("https://finalsattendanceapp.firebaseio.com/USER");
        parentDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIAN");

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    userDB.child(user.getUid()).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            User usermodel = dataSnapshot.getValue(User.class);
                            Log.e("loginactivity68", usermodel.getType());

                            if (usermodel.getType().equals("parent")) {

                                parentDB.orderByChild("user_id").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                        String id = dataSnapshot.getKey();

                                        Log.e("loginactivity92", id);
                                        Log.e("loginactivity93", mAuth.getCurrentUser().getUid());

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(SplashScreenActivity.this, GuardianHomeActivity.class);

                                        intent.putExtra("id", id);
                                        intent.putExtra("type", "normal");

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

                            } else {

                             /*   Intent intent = new Intent(LoginActivity.this,ChildProfileActivity.class);

                                startActivity(intent);*/
                                childDB.orderByChild("user_id").equalTo(mAuth.getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        Child child = dataSnapshot.getValue(Child.class);

                                        Log.e("ataysds", child.getChild_id());

                                        Intent intent = new Intent(SplashScreenActivity.this, ChildHomeActivity.class);
                                        intent.putExtra("id", child.getChild_id());
                                        intent.putExtra("guardian_id", child.getGuardian_id());
                                        intent.putExtra("name", child.getFirstName() + " " + child.getLastName());

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


                } else {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                    }, 3000);

                }

            }
        };

    }


    @Override
    public void onStart() {
        super.onStart();
        // mAuth.signOut();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
