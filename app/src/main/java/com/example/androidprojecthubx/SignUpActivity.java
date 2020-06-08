package com.example.androidprojecthubx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    Toolbar toolbarSignUp;
    EditText et_userEmail, et_password, et_confirmPassword;
    Button btnSignUp, btnLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        toolbarSignUp = findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbarSignUp);
        getSupportActionBar().setTitle("SignUp Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        et_userEmail = findViewById(R.id.et_userEmail);
        et_password = findViewById(R.id.et_userPassword);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);
        btnSignUp = findViewById(R.id.btnsignUp);
        btnLogin = findViewById(R.id.btn_login);

        firebaseAuth = firebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpFirebaseAuth();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }

    private void setUpFirebaseAuth() {
        String mUserEmail = et_userEmail.getText().toString().trim();
        String mUserPass = et_password.getText().toString().trim();
        String mUserConfirmPass = et_confirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(mUserEmail)){
            Toast.makeText(SignUpActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mUserPass)){
            Toast.makeText(SignUpActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mUserConfirmPass)){
            Toast.makeText(SignUpActivity.this, "Please enter Confirm password ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mUserPass.length() < 6){
            Toast.makeText(SignUpActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
        }

        if (mUserPass.equals(mUserConfirmPass)){

            firebaseAuth.createUserWithEmailAndPassword(mUserEmail, mUserPass)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(SignUpActivity.this, DashboardActivity.class));
                                Toast.makeText(SignUpActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(SignUpActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            Toast.makeText(SignUpActivity.this, "Password Matched", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(SignUpActivity.this, "Password Not match", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
