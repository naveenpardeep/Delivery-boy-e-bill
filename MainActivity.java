package com.example.transporte_bill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   private Button register ,login;
   private TextView forgotpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register= findViewById(R.id.register);
        login = findViewById(R.id.login);
        forgotpassword=(TextView) findViewById(R.id.forgotpassowrd);
        register.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register :
                  startActivity(new Intent(this,Deliveryboy.class));
                  break;
        }
    }
}