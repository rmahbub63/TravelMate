package com.example.mahbub.travelmateui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.model.MyTourList;

import java.util.ArrayList;

/**
 * Created by SHAJJAD on 04-Dec-17.
 */

public class MyTourListAdapter extends RecyclerView.Adapter<MyTourListAdapter.MyTourListViewHolder> {
    private Context context;
    private ArrayList<MyTourList> myTourLists;

    public MyTourListAdapter(Context context, ArrayList<MyTourList> myTourLists){
        this.context = context;
        this.myTourLists = myTourLists;
    }
    @Override
    public MyTourListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_tour_plan_support,parent, false);
        return new MyTourListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyTourListViewHolder holder, int position) {
        holder.imgIcon.setImageResource(myTourLists.get(position).getPicId());
        holder.textPlaceName.setText(myTourLists.get(position).getPlaceName());
        holder.textLocation.setText(myTourLists.get(position).getLocation());
        holder.textTourDate.setText(myTourLists.get(position).getTourDate());
    }

    @Override
    public int getItemCount() {
        return myTourLists.size();
    }

    public  class  MyTourListViewHolder extends  RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView textPlaceName,textLocation,textTourDate;


        public MyTourListViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.full_image);
            textPlaceName=itemView.findViewById(R.id.textViewPlaceName);
            textLocation=itemView.findViewById(R.id.textViewLocation);
            textTourDate=itemView.findViewById(R.id.textViewTourDate);
        }
    }
}
