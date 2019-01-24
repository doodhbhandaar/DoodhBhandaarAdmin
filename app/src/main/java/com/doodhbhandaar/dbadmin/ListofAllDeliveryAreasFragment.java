package com.doodhbhandaar.dbadmin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListofAllDeliveryAreasFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<String> deliveryAreasItems;
    ListOfAllDeliveryAreasAdapter adapter;

    public ListofAllDeliveryAreasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output= inflater.inflate(R.layout.fragment_listof_all_delivery_areas, container, false);

        recyclerView = output.findViewById(R.id.list_of_all_delivery_area_recyclerview);
        deliveryAreasItems = new ArrayList<>();

        for(int i=0;i<1000;i++){
            deliveryAreasItems.add("Shivam "+i+1);
        }
        adapter = new ListOfAllDeliveryAreasAdapter(getContext(),deliveryAreasItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return output;
    }

}
