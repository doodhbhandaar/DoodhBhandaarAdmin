package com.doodhbhandaar.dbadmin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListOfAllDeliveryAreasAdapter extends RecyclerView.Adapter<ViewHolderListOfAllDeliveryAreas> {

    ArrayList<String> deliveryList;
    LayoutInflater inflater;

    public ListOfAllDeliveryAreasAdapter(Context context,ArrayList<String> deliveryList) {
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.deliveryList = deliveryList;
    }

    @NonNull
    @Override
    public ViewHolderListOfAllDeliveryAreas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View output = inflater.inflate(R.layout.view_holder_list_of_all_delivery_areas,parent,false);
        return new ViewHolderListOfAllDeliveryAreas(output);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListOfAllDeliveryAreas holder, int position) {
        String s = deliveryList.get(position);
        holder.textView.setText(s);
    }

    @Override
    public int getItemCount() {
        return deliveryList.size();
    }
}
