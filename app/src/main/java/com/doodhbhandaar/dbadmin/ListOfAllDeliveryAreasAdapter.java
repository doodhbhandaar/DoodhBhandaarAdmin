package com.doodhbhandaar.dbadmin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListOfAllDeliveryAreasAdapter extends RecyclerView.Adapter<ViewHolderListOfAllDeliveryAreas> {

    ArrayList<DeliveryBoyReference> deliveryBoyItems;
    LayoutInflater inflater;


    public ListOfAllDeliveryAreasAdapter(Context context,ArrayList<DeliveryBoyReference> deliveryAreasItems){
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.deliveryBoyItems=deliveryAreasItems;

    }

    @NonNull
    @Override
    public ViewHolderListOfAllDeliveryAreas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View output=inflater.inflate(R.layout.view_holder_list_of_all_delivery_areas,parent,false);
        return new ViewHolderListOfAllDeliveryAreas(output);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListOfAllDeliveryAreas holder, int position) {
        DeliveryBoyReference deliveryBoyReference = deliveryBoyItems.get(position);
        holder.deliveryboyName.setText(deliveryBoyReference.name);
        holder.deliveryboyPhonenumber.setText(deliveryBoyReference.contactNo);
        holder.deliveryboyAddress.setText(deliveryBoyReference.address);

    }

    @Override
    public int getItemCount() {
        return deliveryBoyItems.size();
    }
}