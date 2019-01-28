package com.doodhbhandaar.dbadmin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    DatabaseReference areasReference;


    public CustomersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output=inflater.inflate(R.layout.fragment_customers, container, false);


        customerRecyclerView=output.findViewById(R.id.customer_recyclerview);
        customerDataArrayList=new ArrayList<>();


        for(int i=0;i<5;i++){
            CustomerData customerData=new CustomerData();
            customerData.customerAddress="aass"+i;
            customerData.customerName="name "+i;
            customerData.customerPhonenumber="phone"+i;
            customerDataArrayList.add(customerData);

        }

        CustomersInterface customersInterface = new CustomersInterface() {
            @Override
            public void onViewClick(View view,int position) {
                //code to edit
                Intent intent = new Intent(getContext(),CustomerProfileActivity.class);
                intent.putExtra("name",customerDataArrayList.get(position).customerName);
                intent.putExtra("Address",customerDataArrayList.get(position).customerAddress);
                intent.putExtra("PhoneNo",customerDataArrayList.get(position).customerPhonenumber);

                startActivity(intent);
            }
        };

        customerAdapter=new CustomerAdapter(getContext(),customerDataArrayList,customersInterface);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        customerRecyclerView.setAdapter(customerAdapter);
        customerAdapter.notifyDataSetChanged();

    return output;
    }

}
