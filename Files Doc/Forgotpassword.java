package com.example.transporte_bill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpassword extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText emailreset;
    private Button resetpassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        emailreset=findViewById(R.id.forgot_email);
        resetpassword=findViewById(R.id.resetpassword);

        auth=FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        resetpassword();
    }

    private void resetpassword() {
      String  email=emailreset.getText().toString().trim();
      if(email.isEmpty()){
          emailreset.setError("please enter email id");
          emailreset.requestFocus();
          return;
      }
      if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
          emailreset.setError("enter valid email id" );
          emailreset.requestFocus();
          return;
      }
      auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
          @Override
          public void onComplete(@NonNull Task<Void> task) {
              if(task.isSuccessful()){
                  Toast.makeText(Forgotpassword.this,"Check your email id ",Toast.LENGTH_LONG).show();
                  startActivity(new Intent(Forgotpassword.this,MainActivity.class));

              }
              else
              {
                  Toast.makeText(Forgotpassword.this,"please try again",Toast.LENGTH_LONG).show();
              }
          }
      });
    }
}