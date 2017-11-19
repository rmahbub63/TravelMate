package com.example.mahbub.travelmateui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.model.DivisionModel;

import java.util.ArrayList;

/**
 * Created by MAHBUB on 31-Oct-17.
 */

public class DivisionListAdapter extends RecyclerView.Adapter<DivisionListAdapter.DivisionViewHolder> {
    private Context context;
    private ArrayList<DivisionModel> divisions;

    public DivisionListAdapter(Context context, ArrayList<DivisionModel> divisions){
        this.context = context;
        this.divisions=divisions;
    }
    @Override
    public DivisionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.division_list_item,parent, false);
        return new DivisionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DivisionViewHolder holder, int position) {
        holder.imgIcon.setImageResource(divisions.get(position).getPicId());
        holder.textTitle.setText(divisions.get(position).getDivisionName());
    }

    @Override
    public int getItemCount() {
        return divisions.size();
    }

    public  class  DivisionViewHolder extends  RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView textTitle;

        public DivisionViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imageViewDhaka);
            textTitle=itemView.findViewById(R.id.textViewDhaka);
        }
    }
}