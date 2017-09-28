package com.devibar.champ.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.devibar.champ.Model.Child;
import com.devibar.champ.Model.Guardian;
import com.devibar.champ.Model.User;
import com.devibar.champ.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText firstName;
    EditText lastName;
    Button register;
    private MaterialSpinner mUserType;

    FirebaseAuth mAuth;
    Firebase userDB, childDB, parentDB;

    String type;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Register");


        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        firstName = (EditText)findViewById(R.id.etFirstName);
        lastName = (EditText)findViewById(R.id.etLastName);
        register = (Button)findViewById(R.id.btnRegister);
        mUserType = (MaterialSpinner) findViewById(R.id.spnUserType);

        final ArrayList<String> userType = new ArrayList<>();
        userType.add("Select user type.");
        userType.add("Guardian");
        userType.add("Child");

        mUserType.setItems(userType);
        mUserType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if (flag == 0) {
                    userType.remove(0);
                    mUserType.setItems(userType);
                    flag = 1;
                    mUserType.setSelectedIndex(position - 1);
                }

                if (item.equals("Guardian")){
                    type = "parent";
                }else {
                    type = "child";
                }

            }
        });




        mAuth = FirebaseAuth.getInstance();

        userDB = new Firebase("https://finalsattendanceapp.firebaseio.com/USER");
        childDB = new Firebase("https://finalsattendanceapp.firebaseio.com/CHILD");
        parentDB = new Firebase("https://finalsattendanceapp.firebaseio.com/GUARDIAN");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser fbuser = mAuth.getCurrentUser();
                                    User user = new User(fbuser.getUid(),firstName.getText().toString(),lastName.getText().toString(),
                                                            password.getText().toString(),email.getText().toString(),type);

                                    userDB.child(fbuser.getUid()).child("info").setValue(user);

                                    if(type.equals("child")){
                                        //childDB.child(fbuser.getUid()).push().child("Guardian_id").setValue("wala pay guardian");
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("guardian_id", "wala pa");
                                        params.put("user_id", fbuser.getUid());
                                        params.put("firstName",firstName.getText().toString());
                                        params.put("lastName",lastName.getText().toString());

                                        String child_id = childDB.push().getKey();

                                        Child child = new Child(fbuser.getUid(),child_id,"wala pa",firstName.getText().toString(),lastName.getText().toString(),"false");

                                        childDB.child(child_id).child("info").setValue(child);
                                        Intent intent = new Intent(Register.this,ChildProfileActivity.class);
                                        startActivity(intent);

                                    }else{

                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("child_id", "wala pa");
                                        params.put("user_id", fbuser.getUid());
                                        params.put("firstName",firstName.getText().toString());
                                        params.put("lastName",lastName.getText().toString());

                                        String key = childDB.push().getKey();

                                        parentDB.child(key).setValue(params);

                                        Intent intent = new Intent(Register.this,GuardianHomeActivity.class);
                                        intent.putExtra("id",key);

                                        startActivity(intent);

                                    }

                                } else {

                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Animal Wala doh", Toast.LENGTH_SHORT).show();


                                }

                                // ...
                            }
                        });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
