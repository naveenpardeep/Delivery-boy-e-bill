package com.example.transporte_bill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ebill extends AppCompatActivity {
    private TextView item1,item2,item3,price1,price2,price3,quantity1,quantity2,quantity3,totalamount;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebill);
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
        DatabaseReference ref=database.getReference("order");
    }
}