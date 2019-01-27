package com.doodhbhandaar.dbadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterDeliveryManActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference deliveryBoyReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_delivery_man);
//
//        FirebaseApp.initializeApp(this);
//        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = FirebaseDatabaseReference.getDatabaseInstance();
        deliveryBoyReference = firebaseDatabase.getReference("DELIVERYBOY");

        Button registerButton = findViewById(R.id.register);
        final EditText nameEditText = findViewById(R.id.editText);
        final EditText phoneEditText = findViewById(R.id.editText2);
        final EditText addressEditText = findViewById(R.id.editText3);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String address = addressEditText.getText().toString();
                DeliveryBoyReference deliveryBoyReferenceObject = new DeliveryBoyReference();
                deliveryBoyReferenceObject.address = address;
                deliveryBoyReferenceObject.name = name;
                deliveryBoyReferenceObject.contactNo = phone;
                deliveryBoyReference.push().setValue(deliveryBoyReferenceObject);
            }
        });

    }
}
