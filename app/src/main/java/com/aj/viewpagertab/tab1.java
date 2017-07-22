package com.aj.viewpagertab;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by AYUSH on 7/19/2017.
 */

public class tab1 extends Fragment {
    ArrayList<cinemas> cinemas;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1,container,false);

        listView = (ListView)view.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(),CinemaActivity.class);
                intent.putExtra("image",cinemas.get(position).getUrl());
                intent.putExtra("address",cinemas.get(position).getAddress1());
                intent.putExtra("phone",cinemas.get(position).getPhone());
                intent.putExtra("type",cinemas.get(position).getType());
                intent.putExtra("name",cinemas.get(position).getName());
                startActivity(intent);



            }
        });
        Download download = new Download();
        download.execute();

        return view;
    }

    public class Download extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            StringBuilder result = new StringBuilder();
            try {


                URL url = new URL("http://delhiguide.000webhostapp.com/getcinemas.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setReadTimeout(20000);
                connection.setConnectTimeout(20000);


                InputStream inputStream = connection.getInputStream();
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


            cinemas = new ArrayList<>();

            try {

                JSONObject data = new JSONObject(s);
                JSONArray contacts = data.getJSONArray("cinemas");
                 // JSONArray p2=data.getJSONArray("imageurls");

                for (int i = 0; i < contacts.length(); i++) {

                    cinemas cine = new cinemas();
                    JSONObject p = contacts.getJSONObject(i);

                    JSONArray p2=p.getJSONArray("imageurls");
                    for (int j = 0; j <p2.length() ; j++) {
                        JSONObject p3=p2.getJSONObject(j);
                        cine.setUrl(p3.getString("url"));
                        Log.i("myapp2",""+cine.getUrl());
                    }
                      cine.setName(p.getString("name"));
                    cine.setType(p.getString("type"));
                    cine.setPhone(p.getLong("phone"));
                    cine.setAddress1(p.getString("address1"));
                     cinemas.add(cine);

                }
                cinemasAdapter adapter = new cinemasAdapter(getContext(),cinemas);

                listView.setAdapter(adapter);


            } catch (Exception e) {
                Log.i("myapp2", "fault:" + e.getMessage());
            }


        }
    }
}
