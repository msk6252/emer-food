package com.example.msk6252.emer_food;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by msk6252 on 16/06/21.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private ArrayList<Food> mDataList;
    private TextView text1;
    private TextView text2;
    private TextView text3;


    public CardAdapter(Context context, ArrayList<Food> dataList){
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = mLayoutInflater.inflate(R.layout.cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    public int getItemCount(){
        return mDataList.size();
    }

    public void onBindViewHolder(ViewHolder holder,int position){
        Food data = mDataList.get(position);
        if(data != null){
            holder.text1.setText(data.getName());
            holder.text2.setText(data.getId());
            holder.text3.setText(data.getDay());
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;
        TextView text3;

        public ViewHolder(View v) {
            super(v);
            text1 = (TextView) v.findViewById(R.id.text1);
            text2 = (TextView) v.findViewById(R.id.text2);
            text3 = (TextView) v.findViewById(R.id.text3);
        }
    }

}
