package com.doodhbhandaar.dbadmin;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class CustomersFragment extends Fragment {

    RecyclerView customerRecyclerView;
    ArrayList<CustomerData> customerDataArrayList;
    CustomerAdapter customerAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference customersReference;


    public CustomersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output=inflater.inflate(R.layout.fragment_customers, container, false);
        FloatingActionButton fab = output.findViewById(R.id.fabCustomerAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addCustmoer();
            }
        });

        customerRecyclerView=output.findViewById(R.id.customer_recyclerview);
        customerDataArrayList=new ArrayList<>();

        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("loading");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        firebaseDatabase = FirebaseDatabaseReference.getDatabaseInstance();
        customersReference = firebaseDatabase.getReference("CUSTOMERS");
        customersReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CustomerData customerData = dataSnapshot.getValue(CustomerData.class);
                customerDataArrayList.add(customerData);
                customerAdapter.notifyDataSetChanged();
                pd.dismiss();
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
//        for(int i=0;i<5;i++){
//            CustomerData customerData=new CustomerData();
//            customerData.customerAddress="aass"+i;
//            customerData.customerName="name "+i;
//            customerData.customerPhonenumber="phone"+i;
//            customerDataArrayList.add(customerData);
//
//        }

        CustomersInterface customersInterface = new CustomersInterface() {
            @Override
            public void onViewClick(View view,int position) {
                //code to edit
                Intent intent = new Intent(getContext(),CustomerProfileActivity.class);
                intent.putExtra("name",customerDataArrayList.get(position).customerName);
                intent.putExtra("Address",customerDataArrayList.get(position).customerAddress);
                intent.putExtra("PhoneNo",customerDataArrayList.get(position).customerPhonenumber);
                intent.putExtra("DBN",customerDataArrayList.get(position).deliverBoyName);
                intent.putExtra("LATITIUDE",customerDataArrayList.get(position).latitude);
                intent.putExtra("LONGITUDE",customerDataArrayList.get(position).longitude);
                intent.putExtra("DELIVERYTYPE",customerDataArrayList.get(position).deliveryType);
                startActivity(intent);
            }
        };

        customerAdapter=new CustomerAdapter(getContext(),customerDataArrayList,customersInterface);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        customerRecyclerView.setAdapter(customerAdapter);
        customerAdapter.notifyDataSetChanged();

    return output;
    }

    private void addCustmoer() {
        CustomerData customerData = new CustomerData();
        customerData.customerName = "Shivam";
        customerData.customerPhonenumber = "8465465";
        customerData.latitude="8746";
        customerData.longitude = "564";
        customerData.deliveryType = true;
        customerData.deliverBoyName = "a;ldskf";
        customersReference.push().setValue(customerData);
    }

}
