package com.aj.viewpagertab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CinemaActivity extends AppCompatActivity {
TextView textView,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        Intent intent=getIntent();
        String address=intent.getStringExtra("address");
        Long phone=intent.getLongExtra("phone",10);
        textView=(TextView)findViewById(R.id.textView4);
        textView2=(TextView)findViewById(R.id.textView6);
        textView.setText(address);
        textView2.setText(String.valueOf(phone));

    }
}
