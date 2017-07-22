package com.aj.viewpagertab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
ImageView img,imageView;
    TextView txt,textView,textView10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        String imageurl=intent.getStringExtra("url");
        String title=intent.getStringExtra("title");
        String overview=intent.getStringExtra("overview");
        String backdrop=intent.getStringExtra("backdrop_path");
        Double vote=intent.getDoubleExtra("vote_average",3);
        textView10=(TextView)findViewById(R.id.textView10);
        imageView=(ImageView)findViewById(R.id.image1);
        txt=(TextView)findViewById(R.id.textView);
        textView=(TextView)findViewById(R.id.textView7);
         img=(ImageView)findViewById(R.id.image);
        txt.setText(title);
        textView10.setText(String.valueOf(vote));
        textView.setText(overview);
        Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w780"+backdrop).into(imageView);

        Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w780"+imageurl).into(img);
    }
}
