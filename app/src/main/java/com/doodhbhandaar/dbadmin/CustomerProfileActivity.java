package com.doodhbhandaar.dbadmin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerProfileActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference deliveryBoyReference;
    DatabaseReference customersReference;
    DatabaseReference areasReference;
    ArrayList<DeliveryBoyReference> deliveryBoyItems;
    String pno;
    TextView deliveryBoyNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        Bundle bundle=getIntent().getExtras();
        int x = 1;
        String name=bundle.getString("name");
        String address=bundle.getString("Address");
        pno=bundle.getString("PhoneNo");
        final String deliveryBoyName = bundle.getString("DBN");
        boolean deliveryTypeIsMorning = bundle.getBoolean("DELIVERYTYPE");
        TextView NameText=(TextView)findViewById(R.id.textView);
        NameText.setText(name);
        TextView AddressText=(TextView)findViewById(R.id.Addresstext);
        AddressText.setText(address);
        TextView Pno=(TextView)findViewById(R.id.pnotext);
        Pno.setText(pno);
        deliveryBoyNameTextView = findViewById(R.id.DBtext);
        deliveryBoyNameTextView.setText("DeliveryBoy: "+deliveryBoyName);
        TextView deliveryType = findViewById(R.id.DeliveryTypeText);
        fetchDeliveryBoy();
        if(deliveryTypeIsMorning)
            deliveryType.setText("Delivery Type: Morning");
        else
            deliveryType.setText("Delivery Type: Evening");
        deliveryBoyNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDeliveryBoy(deliveryBoyName);
            }
        });

    }

    private void changeDeliveryBoy(String deliveryBoyName) {
        final CharSequence[] items = new CharSequence[deliveryBoyItems.size()];
        for(int i=0;i<deliveryBoyItems.size();i++){
            items[i] = deliveryBoyItems.get(i).name;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(CustomerProfileActivity.this);
        builder.setTitle("Make your selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                changeDB(item);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void changeDB(final int item) {
        String contactNo = deliveryBoyItems.get(item).contactNo;
        Query deleteDB = deliveryBoyReference.orderByChild("contactNo").equalTo(contactNo);
        deleteDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    DeliveryBoyReference deliveryBoyReference = d.getValue(DeliveryBoyReference.class);
                    int a = Integer.parseInt(pno);
                    if(deliveryBoyReference.cidList==null)
                        deliveryBoyReference.cidList = new ArrayList<>();
                    deliveryBoyReference.cidList.add(a);
                    d.getRef().setValue(deliveryBoyReference);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Query deleteDBa = customersReference.orderByChild("customerPhonenumber").equalTo(pno);
        deleteDBa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    CustomerData customerData = d.getValue(CustomerData.class);
                    customerData.deliverBoyName = deliveryBoyItems.get(item).name;
                    d.getRef().setValue(customerData);
                    deliveryBoyNameTextView.setText("Delivery Boy: "+deliveryBoyItems.get(item).name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void fetchDeliveryBoy() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        deliveryBoyItems = new ArrayList<>();
        firebaseDatabase = FirebaseDatabaseReference.getDatabaseInstance();
        deliveryBoyReference = firebaseDatabase.getReference("DELIVERYBOY");
        customersReference = firebaseDatabase.getReference("CUSTOMERS");
        deliveryBoyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    DeliveryBoyReference deliveryBoyReference = d.getValue(DeliveryBoyReference.class);
                    deliveryBoyItems.add(deliveryBoyReference);
                }
                pd.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
