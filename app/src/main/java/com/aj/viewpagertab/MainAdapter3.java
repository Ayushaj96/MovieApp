package com.aj.viewpagertab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainAdapter3 extends RecyclerView.Adapter<MainAdapter3.ViewHolder> {


    public MainAdapter3() {

    }

    @Override
    public MainAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row2,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         public ViewHolder(View itemView) {
            super(itemView);
           }
    }
}


