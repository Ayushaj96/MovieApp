package com.aj.viewpagertab;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
EditText editText;
    ImageView imageView,imageView2,imageView3,imageView4;
    movie m;
    TextView textView,txt,textView2,txt2;
    boolean flag=true;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText=(EditText)findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                    return  true;
                }
                return false;
            }
        });
        editText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        textView=(TextView)findViewById(R.id.textView8);
        txt=(TextView)findViewById(R.id.about);
        textView2=(TextView)findViewById(R.id.textView9);
       txt2=(TextView)findViewById(R.id.plot);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView3=(ImageView)findViewById(R.id.image1);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView4=(ImageView)findViewById(R.id.imageView4);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=editText.getText().toString();
                Log.i("myapp1",""+name);
                 txt2.setText("PLOT-");
                imageView4.setImageResource(R.drawable.star);
                new Download().execute("https://api.themoviedb.org/3/search/movie?query="+name+"&api_key=55957fcf3ba81b137f8fc01ac5a31fb5" );

            }
        });

    }

void sendMessage()
{editText.clearFocus();

    InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    name=editText.getText().toString();
    Log.i("myapp1",""+name);
    txt2.setText("PLOT-");
    imageView4.setImageResource(R.drawable.star);
    new Download().execute("https://api.themoviedb.org/3/search/movie?query="+name+"&api_key=55957fcf3ba81b137f8fc01ac5a31fb5" );

}
    public class Download extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            StringBuilder result = new StringBuilder();

            try {
                URL url=new URL(params[0]);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.connect();
                InputStream inputStream = con.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF8"));
                BufferedReader br = new BufferedReader(reader);

                String line = null;

                line = br.readLine();

                while (line != null) {
                    result.append(line);
                    line = br.readLine();
                }
            } catch (Exception e) {
                Log.i("myapp", e.getMessage());
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {    //specify UI updation Logic/
          final  ArrayList<movie> arr=new ArrayList<>();
            try {

                JSONObject data = new JSONObject(s);
                JSONArray contacts = data.getJSONArray("results");
                for (int i = 0; i < contacts.length(); i++) {

                    m = new movie();
                    JSONObject p = contacts.getJSONObject(0);
                    m.setTitle(p.getString("title"));
                    m.setOverview(p.getString("overview"));
                    m.setVote_average(p.getDouble("vote_average"));
                    m.setBackdrop_path(p.getString("backdrop_path"));
                    // m.setRelease_date(p.getString("release_date"));
                    m.setPoster_path(p.getString("poster_path"));



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                txt.setText(m.getOverview());
                                textView2.setText(String.valueOf(m.getVote_average()));
                                //tv1.setText(m.getRelease_date());
                                textView.setText(m.getTitle());
                                Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w780/" + m.getBackdrop_path()).into(imageView3);
                                Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w780/" + m.getPoster_path()).into(imageView);

                        }
                    });
                }

            } catch (Exception e) {
                Log.i("myapp", "fault:" + e.getMessage());
            }

        }}}

