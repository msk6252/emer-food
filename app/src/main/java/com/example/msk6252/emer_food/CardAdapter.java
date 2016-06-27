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
    private ArrayList<String> mDataList;
    private TextView text;

    public CardAdapter(Context context, ArrayList<String> dataList){
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
        String data = (String) mDataList.get(position);
        holder.text.setText(data);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.text);
        }
    }

}
