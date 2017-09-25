package com.devibar.champ.Activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.devibar.champ.R;
import com.devibar.champ.Utility.DialogUtility;

public class LoginPageActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;

    private String from;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        if (intent!=null){
            from = intent.getStringExtra("LAYOUT");
            if (from.equals("GUARDIAN")){
                setContentView(R.layout.activity_login_guardian);
            }else {
                setContentView(R.layout.activity_login_child);
            }
        }
        getSupportActionBar().hide();



        mUsername = (EditText) findViewById(R.id.etUsername);
        mPassword = (EditText) findViewById(R.id.etPassword);
        mLogin = (Button) findViewById(R.id.btnLogin);

        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if  (view.getId() == R.id.btnLogin){

            String username = mUsername.getText().toString();
            String password = mPassword.getText().toString();

            if (username.equals("") || password.equals("")){
                DialogUtility.messageDialog("Please don't leave any empty fields.",this);
            }else {
                Intent intent;
                if  (from.equals("GUARDIAN")){
                    intent = new Intent(LoginPageActivity.this,GuardianHomeActivity.class);
                    startActivity(intent);
                }else {

                }
            }



        }
    }
}
