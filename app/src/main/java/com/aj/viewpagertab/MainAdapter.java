package com.aj.viewpagertab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<movie> mDataset;
    private Context context;


    public MainAdapter(ArrayList<movie> mDataset,Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        ViewHolder vh=new ViewHolder(view);

        return vh;

    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, final int i) {
        // holder.mTitle.setText(mDataset.get(position));
        Log.i("myapp",""+mDataset.get(i).getPoster_path());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Main2Activity.class);
                intent.putExtra("url",mDataset.get(i).getPoster_path());
                intent.putExtra("title",mDataset.get(i).getTitle());
                intent.putExtra("overview",mDataset.get(i).getOverview());
                intent.putExtra("backdrop_path",mDataset.get(i).getBackdrop_path());
                intent.putExtra("vote_average",mDataset.get(i).getVote_average());
                context.startActivity(intent);
                // Your image click code

            }
        });

        Picasso.with(context).load("https://image.tmdb.org/t/p/w780"+mDataset.get(i).getPoster_path()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.imageview);
        }
    }
}

