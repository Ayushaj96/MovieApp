package com.aj.viewpagertab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AYUSH on 7/19/2017.
 */

public class MainAdapter2 extends RecyclerView.Adapter<MainAdapter2.ViewHolder> {
    private ArrayList<String> mDataset;


    public MainAdapter2(ArrayList<String> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public MainAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row2,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(MainAdapter2.ViewHolder holder, int position) {
        holder.mTitle.setText(mDataset.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle=(TextView)itemView.findViewById(R.id.title);
        }
    }
}

