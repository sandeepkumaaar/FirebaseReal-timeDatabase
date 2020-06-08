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

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbarLogin;
    EditText etLoginEmail, etLoginPassword;
    Button btnLogin, btnlsignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        toolbarLogin = findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbarLogin);
        getSupportActionBar().setTitle("Login Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etLoginEmail = findViewById(R.id.et_loginEmail);
        etLoginPassword = findViewById(R.id.et_loginPassword);
        btnLogin = findViewById(R.id.btnlogin);
        btnlsignUp = findViewById(R.id.btn_lsignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupFirebase();
            }
        });

        btnlsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
    }

    public void setupFirebase() {
        String mLoginEmail = etLoginEmail.getText().toString().trim();
        String mLoginPass = etLoginPassword.getText().toString().trim();

        if (TextUtils.isEmpty(mLoginEmail)){
            Toast.makeText(LoginActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mLoginPass)){
            Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mLoginPass.length() < 6){
            Toast.makeText(LoginActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
        }

        mAuth.signInWithEmailAndPassword(mLoginEmail, mLoginPass)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LoginActivity.this, "Login Failed, User not available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
