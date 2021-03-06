package com.aj.viewpagertab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class tab2 extends Fragment
{ArrayList<movie> movies;

private RecyclerView mRecyclerView,mRecyclerView2,mRecyclerView3,mRecyclerView4;
private RecyclerView.Adapter mAdapter,mAdapter2,mAdapter3;
private RecyclerView.LayoutManager mLayoutManager;
private ArrayList<String> arr;
    private TextView txt3,textView,textView2,textView3;


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab3,container,false);

        mRecyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView2=(RecyclerView)view.findViewById(R.id.recycle);
        mRecyclerView3=(RecyclerView)view.findViewById(R.id.recycle2);
mRecyclerView4=(RecyclerView)view.findViewById(R.id.recycle3);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        //  mRecyclerView2.setLayoutManager(new GridLayoutManager(this,2));
    mRecyclerView4.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
    txt3=(TextView)view.findViewById(R.id.txt3);
    textView=(TextView)view.findViewById(R.id.txt);
    textView2=(TextView)view.findViewById(R.id.txt2);
    textView3=(TextView)view.findViewById(R.id.txt4);
    textView2.setText("Sitcom");
    txt3.setText("Other");
    textView.setText("Popular");
    textView3.setText("Top Rated");

    Download download = new Download();
        download.execute();
    Download2 download2 = new Download2();
    download2.execute();

    Download3 download3=new Download3();
    download3.execute();
    Download4 download4=new Download4();
    download4.execute();



        return view;
        }
    public class Download4 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            StringBuilder result = new StringBuilder();
            try {


                URL url = new URL("https://api.themoviedb.org/3/tv/top_rated?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=2");
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
            } catch(Exception e){
                Log.i("myapp", e.getMessage());
            }
            return result.toString();
        }


        @Override
        protected void onPostExecute(String s) {    //specify UI updation Logic/

            ArrayList<movie> mDataset;
            mDataset=new ArrayList<movie>();
            try {

                JSONObject data = new JSONObject(s);
                JSONArray contacts = data.getJSONArray("results");
                for (int i = 0; i < contacts.length(); i++) {

                    movie movies = new movie();
                    JSONObject p = contacts.getJSONObject(i);
                    movies.setTitle(p.getString("name"));
                    movies.setOverview(p.getString("overview"));
                    movies.setBackdrop_path(p.getString("backdrop_path"));
                    movies.setVote_average(p.getDouble("vote_average"));
                    movies.setPoster_path(p.getString("poster_path"));
                    Log.i("myapp","" +movies.getPoster_path());
                    mDataset.add(movies);
                }
                MainAdapter mAdapter = new MainAdapter(mDataset,getContext());
                mRecyclerView4.setAdapter(mAdapter);

            }

            catch (Exception e) {
                Log.i("myapp", "fault:"+e.getMessage());
            }
        }
    }

    public class Download3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            StringBuilder result = new StringBuilder();
            try {


                URL url = new URL("https://api.themoviedb.org/3/tv/popular?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=3");
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
            } catch(Exception e){
                Log.i("myapp", e.getMessage());
            }
            return result.toString();
        }


        @Override
        protected void onPostExecute(String s) {    //specify UI updation Logic/

            ArrayList<movie> mDataset;
            mDataset=new ArrayList<movie>();
            try {

                JSONObject data = new JSONObject(s);
                JSONArray contacts = data.getJSONArray("results");
                for (int i = 0; i < contacts.length(); i++) {

                    movie movies = new movie();
                    JSONObject p = contacts.getJSONObject(i);
                    movies.setTitle(p.getString("name"));
                    movies.setOverview(p.getString("overview"));
                    movies.setPoster_path(p.getString("poster_path"));
                    movies.setBackdrop_path(p.getString("backdrop_path"));
                    movies.setVote_average(p.getDouble("vote_average"));

                    Log.i("myapp","" +movies.getPoster_path());
                    mDataset.add(movies);
                }
                MainAdapter mAdapter = new MainAdapter(mDataset,getContext());
                mRecyclerView3.setAdapter(mAdapter);

            }

            catch (Exception e) {
                Log.i("myapp", "fault:"+e.getMessage());
            }
        }
    }

    public class Download2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            StringBuilder result = new StringBuilder();
            try {


                URL url = new URL("https://api.themoviedb.org/3/tv/popular?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=4");
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
            } catch(Exception e){
                Log.i("myapp", e.getMessage());
            }
            return result.toString();
        }


        @Override
        protected void onPostExecute(String s) {    //specify UI updation Logic/

            ArrayList<movie> mDataset;
            mDataset=new ArrayList<movie>();
            try {

                JSONObject data = new JSONObject(s);
                JSONArray contacts = data.getJSONArray("results");
                for (int i = 0; i < contacts.length(); i++) {

                    movie movies = new movie();
                    JSONObject p = contacts.getJSONObject(i);
                    movies.setTitle(p.getString("name"));
                    movies.setOverview(p.getString("overview"));
                    movies.setBackdrop_path(p.getString("backdrop_path"));
                    movies.setVote_average(p.getDouble("vote_average"));

                    movies.setPoster_path(p.getString("poster_path"));
                    Log.i("myapp","" +movies.getPoster_path());
                    mDataset.add(movies);
                }
                MainAdapter mAdapter = new MainAdapter(mDataset,getContext());
                mRecyclerView2.setAdapter(mAdapter);

            }

            catch (Exception e) {
                Log.i("myapp", "fault:"+e.getMessage());
            }
        }
    }

public class Download extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        StringBuilder result = new StringBuilder();
        try {


            URL url = new URL("https://api.themoviedb.org/3/tv/popular?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US");
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
        } catch(Exception e){
            Log.i("myapp", e.getMessage());
        }
        return result.toString();
    }


    @Override
    protected void onPostExecute(String s) {    //specify UI updation Logic/

        ArrayList<movie> mDataset;
        mDataset=new ArrayList<movie>();
        try {

            JSONObject data = new JSONObject(s);
            JSONArray contacts = data.getJSONArray("results");
            for (int i = 0; i < contacts.length(); i++) {

                movie movies = new movie();
                JSONObject p = contacts.getJSONObject(i);
                movies.setTitle(p.getString("name"));
                movies.setOverview(p.getString("overview"));
                movies.setBackdrop_path(p.getString("backdrop_path"));
                movies.setVote_average(p.getDouble("vote_average"));

                movies.setPoster_path(p.getString("poster_path"));
                Log.i("myapp","" +movies.getPoster_path());
                mDataset.add(movies);
            }
            MainAdapter mAdapter = new MainAdapter(mDataset,getContext());
            mRecyclerView.setAdapter(mAdapter);

        }

        catch (Exception e) {
            Log.i("myapp", "fault:"+e.getMessage());
        }
    }
}
}


