package com.doodhbhandaar.dbadmin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AreasAdapter extends RecyclerView.Adapter<ViewHolderListOfAllAreas> {

    ArrayList<String> areasList;
    LayoutInflater inflater;

    public AreasAdapter(Context context, ArrayList<String> areas) {
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        areasList = areas;
    }

    @NonNull
    @Override
    public ViewHolderListOfAllAreas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View output = inflater.inflate(R.layout.list_of_all_areas_view_holder,parent,false);
        return new ViewHolderListOfAllAreas(output);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListOfAllAreas holder, int position) {
        String s = areasList.get(position);
        holder.textView.setText(s);
    }

    @Override
    public int getItemCount() {
        return areasList.size();
    }
}
