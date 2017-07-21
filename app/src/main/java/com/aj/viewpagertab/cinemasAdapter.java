package com.aj.viewpagertab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class cinemasAdapter extends ArrayAdapter<cinemas> {
    TextView tvname;
    TextView tvprice;
    ImageView imageView;


    public cinemasAdapter(Context context, ArrayList<cinemas> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.custom, parent, false);
        }

        cinemas cinemas = getItem(position);
        //tvname = (TextView) view.findViewById(R.id.textView);
        tvprice = (TextView) view.findViewById(R.id.textView2);
        imageView=(ImageView)view.findViewById(R.id.imageView);


        tvprice.setText(cinemas.getName());
        Picasso.with(getContext()).load("https://delhiguide.000webhostapp.com/"+cinemas.getUrl()).into(imageView);
       // tvprice.setText(String.valueOf(cinemas.getPhone()));
        return view;

    }
}

