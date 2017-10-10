package com.devibar.champ.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.devibar.champ.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLoginGuardian;
    private Button mLoginChild;
    private TextView mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Firebase.setAndroidContext(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        getSupportActionBar().hide();

        mLoginGuardian = (Button) findViewById(R.id.btnLoginGuardian);
        mLoginChild = (Button) findViewById(R.id.btnLoginChild);
        mSignUp = (TextView) findViewById(R.id.txtSignUp);


        mLoginGuardian.setOnClickListener(this);
        mLoginChild.setOnClickListener(this);
        mSignUp.setOnClickListener(this);


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
            intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }

    }
}
