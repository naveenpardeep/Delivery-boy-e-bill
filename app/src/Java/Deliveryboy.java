package com.example.transporte_bill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;


public class Deliveryboy extends AppCompatActivity implements OnClickListener {
   TextInputEditText Deliveryboy_gmail,Deliveryboy_password ,Deliveryboy_name,Deliverybpy_mobile;
   Button Deliveryboy_register;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryboy);
        Deliveryboy_gmail = (TextInputEditText) findViewById(R.id.Deliveryboy_gmail);
        Deliveryboy_password = (TextInputEditText)findViewById(R.id.password);
        Deliveryboy_name =(TextInputEditText) findViewById(R.id.Deliveryboy_fullname);
        Deliverybpy_mobile = (TextInputEditText)findViewById(R.id.Deliveryboy_mobile);
        Deliveryboy_register =findViewById(R.id.deliveryboy_register);
        progressBar = findViewById(R.id.progressBar);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

         Deliveryboy_register.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
    switch (v.getId())
    {
        case R.id.deliveryboy_register :
            registeruser();
                    break;
    }
    }

    private void registeruser() {
        String email= Deliveryboy_gmail.getText().toString().trim();
        String phone= Deliverybpy_mobile.getText().toString().trim();
        String name= Deliveryboy_name.getText().toString().trim();
        String password = Deliveryboy_password.getText().toString().trim();

        if(name.isEmpty() ){
            Deliveryboy_name.setError("Name is required ");
           Deliveryboy_name.requestFocus();
           return;
        }
        if(phone.isEmpty() || phone.length()<10){
            Deliverybpy_mobile.setError("Enter Valid Mobile no");
            Deliverybpy_mobile.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            Deliveryboy_gmail.setError("Email is Required");
            Deliveryboy_gmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Deliveryboy_gmail.setError("enter valid  email id");
            Deliveryboy_gmail.requestFocus();
            return;
        }

        if(password.isEmpty() || password.length()<6){
            Deliveryboy_password.setError("password required minimum 7 char");
            Deliveryboy_password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                    User user=new User(name,email,phone);
                            FirebaseDatabase.getInstance().getReference("DeliveryBoy")
                                  // .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .child(phone) //to create child in database
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                  if(task.isSuccessful()){
                                      Toast.makeText(Deliveryboy.this,"User registered",Toast.LENGTH_LONG).show();
                                      progressBar.setVisibility(View.VISIBLE);
                                  }
                                  else
                                  {
                                      Toast.makeText(Deliveryboy.this,"Registration failed",Toast.LENGTH_LONG).show();
                                      progressBar.setVisibility(View.GONE);
                                  }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(Deliveryboy.this,"Registration failed",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}