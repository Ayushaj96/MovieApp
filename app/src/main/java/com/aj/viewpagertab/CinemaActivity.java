package com.aj.viewpagertab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CinemaActivity extends AppCompatActivity {
TextView textView,textView2,textView3;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        Intent intent=getIntent();
        String address=intent.getStringExtra("address");
        String image=intent.getStringExtra("image");
        String type=intent.getStringExtra("type");
        Long phone=intent.getLongExtra("phone",10);
        textView=(TextView)findViewById(R.id.textView4);
        img=(ImageView)findViewById(R.id.imgcinema);
        Picasso.with(getApplicationContext()).load("https://delhiguide.000webhostapp.com/"+image).into(img);

        textView2=(TextView)findViewById(R.id.textView6);
        textView3=(TextView)findViewById(R.id.textView12);
        textView3.setText(type);
        textView.setText(address);
        textView2.setText(String.valueOf(phone));

    }
}
