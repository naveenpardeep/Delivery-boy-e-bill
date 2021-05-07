package com.example.transporte_bill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   private Button register ,login;
   private TextView forgotpassword;
   private TextInputEditText editmail ;
   private EditText editpassword;
   private FirebaseAuth auth;
   private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register= findViewById(R.id.register);
        register.setOnClickListener(this);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        editmail=findViewById(R.id.loginmail);
        editpassword=findViewById(R.id.password);
        forgotpassword=(TextView) findViewById(R.id.forgotpassowrd);

   // progressBar=findViewById(R.id.progressBar);
   auth=FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register :
                  startActivity(new Intent(this,Deliveryboy.class));
                  break;

            case R.id.login :
                userlogin();
                break;

        }

}

    private void userlogin() {
        String email= editmail.getText().toString().trim();
        String password= editpassword.getText().toString().trim();

        if(email.isEmpty()){
            editmail.setError("Not empty email id");
            editmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editmail.setError("please enter valid email id");
            editmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editpassword.setError("enter password");
            editpassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editpassword.setError("error password length");
            editpassword.requestFocus();
            return;
        }
     //  progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                //user bill activity
                    startActivity(new Intent(MainActivity.this,  Ebill.class));
              Toast.makeText(MainActivity.this,"login successful",Toast.LENGTH_LONG).show();
                }
                    else
                    {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"check your mail to verify",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"failed to login please try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}