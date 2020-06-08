package com.example.androidprojecthubx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etUserName, etUserEmail, etUserMobileNumber, etnumberofPeople, etUserAddress;
    Button btnSendData,btnSignUp;
    Spinner spinnerEventCategory;
    DatabaseReference databaseReference;
    DataModel model;
    Button btnFetchData;
    List<String> eventList = new ArrayList<>();
    boolean validation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("Event Form Details");

        etUserName = findViewById(R.id.et_userName);
        etUserEmail = findViewById(R.id.et_userEmail);
        etUserMobileNumber = findViewById(R.id.et_userMobileNumber);
        etnumberofPeople = findViewById(R.id.et_numberofPeople);
        etUserAddress = findViewById(R.id.et_userAddress);
        btnSendData = findViewById(R.id.btn_sendData);
        btnFetchData = findViewById(R.id.btn_fetchAllData);
        btnSignUp = findViewById(R.id.btn_signUp);
        spinnerEventCategory = findViewById(R.id.spinnerEventCategory);

        btnFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FetchDataActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        spinnerEventCategory.setOnItemSelectedListener(this);

        eventList.add("Marriage");
        eventList.add("Birthday party");
        eventList.add("Corprate");
        eventList.add("Business");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,eventList );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEventCategory.setAdapter(arrayAdapter);

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()){
                    addData();
                }else {
                    Toast.makeText(MainActivity.this, "Please submit Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkValidation() {
        if (etUserName.getText().toString().length() == 0){
            etUserName.setError("Name is required");
            return false;

        }else if (etUserEmail.getText().toString().isEmpty()){
            etUserEmail.setError("Email is required");
            return false;

        } else if (etUserMobileNumber.getText().toString().length() == 0) {
            etUserMobileNumber.setError("Mobile number is required");
            return false;

        } else if (etUserMobileNumber.getText().toString().length() > 10) {
            etUserMobileNumber.setError("Mobile Number is more than 10");
            return false;

        } else if (etUserMobileNumber.getText().toString().length() < 10) {
            etUserMobileNumber.setError("Mobile Number is less than 10");
            return false;

        }else if (etnumberofPeople.getText().toString().length() == 0){
            etnumberofPeople.setError("Please add no.of people");
            return false;
        }

        else if (etUserAddress.getText().toString().trim().length() == 0){
            etUserAddress.setError("Address is required");
            return false;
        }
        else {
            Toast.makeText(MainActivity.this, "Thanks for submit", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void addData(){
        String mName = etUserName.getText().toString();
        String mEmail = etUserEmail.getText().toString();
        String mMobileNumber = etUserMobileNumber.getText().toString();
        String mNumberOfPeople = etnumberofPeople.getText().toString();
        String mAddress = etUserAddress.getText().toString();
        String mEventCategory = spinnerEventCategory.getSelectedItem().toString();

        String id = databaseReference.push().getKey();
        model = new DataModel(id,mName,mEmail,mMobileNumber,mNumberOfPeople,mAddress,mEventCategory);
        databaseReference.child((id)).setValue(model);

        etUserName.setText("");
        etUserEmail.setText("");
        etUserMobileNumber.setText("");
        etnumberofPeople.setText("");
        etUserAddress.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

