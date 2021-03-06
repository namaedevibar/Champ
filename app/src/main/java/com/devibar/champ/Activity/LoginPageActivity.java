package com.devibar.champ.Activity;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.devibar.champ.Model.Child;
import com.devibar.champ.R;
import com.devibar.champ.Utility.DialogUtility;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LoginPageActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    FirebaseAuth mAuth;
    Firebase parentDB;
    Firebase IdReference;
    Firebase childDB;
    private ProgressDialog progressDialog;


    private String from;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressDialog = new ProgressDialog(this);

        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        if (intent != null) {
            from = intent.getStringExtra("LAYOUT");
            if (from.equals("GUARDIAN")) {
                setContentView(R.layout.activity_login_guardian);
            } else {
                setContentView(R.layout.activity_login_child);
            }
        }
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();


        parentDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIAN");
        childDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD");

        mUsername = (EditText) findViewById(R.id.etUsername);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mLogin = (Button) findViewById(R.id.btnLogin);

        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            DialogUtility.progressDialogShow(progressDialog);
            final String email = mUsername.getText().toString();
            final String password = mPassword.getText().toString();

            if (email.equals("") || password.equals("")) {
                DialogUtility.messageDialog("Please don't leave any empty fields.", this);
            } else {

                if (from.equals("GUARDIAN")) {

                    Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information


                                        //DatabaseReference ref = database.getReference("")
                                        IdReference = parentDB.child(mAuth.getCurrentUser().getUid());
                                        IdReference.addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                                String id = dataSnapshot.getKey();

                                                Log.e("loginactivity92", id);
                                                Log.e("loginactivity93", mAuth.getCurrentUser().getUid());

                                                FirebaseUser user = mAuth.getCurrentUser();
                                                Intent intent = new Intent(LoginPageActivity.this, GuardianHomeActivity.class);

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
                                        DialogUtility.progressDialogDismiss(progressDialog);
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginPageActivity.this, "Please try again later.", Toast.LENGTH_SHORT).show();


                                    }

                                    // ...
                                }
                            });

                } else {
                  /*  Intent intent = new Intent(LoginPageActivity.this,ChildHomeActivity.class);
                    startActivity(intent);*/

                    Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Query query = database.getReference("CHILD");
                                        //Log.e("yawagawas","yawagawas");

                                        Log.e("bag o", "hahay");

                                        childDB.orderByChild("user_id").equalTo(mAuth.getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                Child child = dataSnapshot.getValue(Child.class);

                                                Log.e("ataysds", child.getFirstName());

                                                Intent intent = new Intent(LoginPageActivity.this, ChildHomeActivity.class);
                                                intent.putExtra("id", child.getChild_id());
                                                intent.putExtra("guardian_id", child.getGuardian_id());
                                                intent.putExtra("name", child.getFirstName() + " " + child.getLastName());
                                                intent.putExtra("email", email);
                                                intent.putExtra("password",password);
                                                intent.putExtra("user_id",mAuth.getCurrentUser().getUid());
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
                                        // If sign in fails, display a message to the user.
                                        DialogUtility.progressDialogDismiss(progressDialog);
                                        Toast.makeText(LoginPageActivity.this, "Please try again later.", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });


                }
            }


        }
    }
}
