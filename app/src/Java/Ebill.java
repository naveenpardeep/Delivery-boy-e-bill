package com.example.transporte_bill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ebill extends AppCompatActivity {
    private TextView item1,item2,item3,price1,price2,price3,quantity1,quantity2,quantity3,totalamount,contact1,deliveredto1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference("Delivery");;
    private Button deliverd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebill);
        deliverd=findViewById(R.id.Delivered);
        deliverd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliverd();
            }
        });
        contact1=findViewById(R.id.contact);
        deliveredto1=findViewById(R.id.deliveredto);
        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);
        item3=findViewById(R.id.item3);
        price1=findViewById(R.id.price1);
        price2=findViewById(R.id.price2);
        price3=findViewById(R.id.price3);

        quantity1=findViewById(R.id.quantity1);
        quantity2=findViewById(R.id.quantity2);
        quantity3=findViewById(R.id.quantity3);
        totalamount=findViewById(R.id.totalamount);

    }

    private void deliverd() {
        String contact=contact1.getText().toString();
        String deliveredto=deliveredto1.getText().toString();

        Delivery delivery=new Delivery(contact,deliveredto);
        FirebaseDatabase.getInstance().getReference("Delivery")

                .child(contact) //to create child in database
                .setValue(deliveredto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Ebill.this,"Item Delivered",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Ebill.this,MainActivity.class));
                }
                else
                {
                    Toast.makeText(Ebill.this,"something went wrong",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}