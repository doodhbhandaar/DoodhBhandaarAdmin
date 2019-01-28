package com.doodhbhandaar.dbadmin;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListofAllDeliveryAreasFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<DeliveryBoyReference> deliveryBoyItems;
    ListOfAllDeliveryAreasAdapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference deliveryBoyReference;

    public ListofAllDeliveryAreasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output= inflater.inflate(R.layout.fragment_listof_all_delivery_areas, container, false);

        recyclerView = output.findViewById(R.id.list_of_all_delivery_area_recyclerview);
        deliveryBoyItems = new ArrayList<>();
        firebaseDatabase = FirebaseDatabaseReference.getDatabaseInstance();
        deliveryBoyReference = firebaseDatabase.getReference("DELIVERYBOY");
        deliveryBoyReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DeliveryBoyReference deliveryBoyReference = dataSnapshot.getValue(DeliveryBoyReference.class);
                deliveryBoyItems.add(deliveryBoyReference);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        for(int i=0;i<10;i++){
//            DeliveryBoyReference deliveryBoyReference = new DeliveryBoyReference();
//            deliveryBoyReference.address="address";
//            deliveryBoyReference.name="name";
//            deliveryBoyReference.contactNo="phonenumber11";
//            deliveryBoyItems.add(deliveryBoyReference);
//        }
        adapter = new ListOfAllDeliveryAreasAdapter(getContext(),deliveryBoyItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        FloatingActionButton fab = output.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addDeliveryboy();
            }
        });
       /*

        */
        return output;
    }

    private void addDeliveryboy() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.activity_register_delivery_man,null);
        builder.setView(dialogView);

        final EditText nameEditText = dialogView.findViewById(R.id.register_deliveryboy_name);
        final EditText phoneEditText = dialogView.findViewById(R.id.register_deliveryboy_phonenumber);
        final EditText addressEditText=dialogView.findViewById(R.id.register_deliveryboy_address);

        builder.setTitle("Add Delivery Boy");
        builder.setPositiveButton("register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String address = addressEditText.getText().toString();
                DeliveryBoyReference deliveryBoyReferenceObject = new DeliveryBoyReference();
                deliveryBoyReferenceObject.address = address;
                deliveryBoyReferenceObject.name = name;
                deliveryBoyReferenceObject.contactNo = phone;
                deliveryBoyReference.push().setValue(deliveryBoyReferenceObject);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }


}
