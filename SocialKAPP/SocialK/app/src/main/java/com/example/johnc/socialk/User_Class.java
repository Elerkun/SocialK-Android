package com.example.johnc.socialk;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class User_Class extends Fragment {



    private ViewPager viewPager;
    private TabLayout tabLayout;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String user_name;
    String user_name_profile;
    String user_name_user;
    String user_email;
    String user_profile;
    String id_email;
    String uniqueID;
    String uniqueID2;
    ImageView profile_picture;
    TextView user_nameT;
    TextView user_emailT;
    private RequestQueue requestQueue;
    LikeButton toogle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_user_, container, false);
        View contenedor = (View) container.getParent();

        tabLayout = view.findViewById(R.id.tabs);

        profile_picture= view.findViewById(R.id.user_profile);
        user_emailT = view.findViewById(R.id.user_email);
        user_nameT = view.findViewById(R.id.user_name);
        toogle= view.findViewById(R.id.follow);

        requestQueue = Volley.newRequestQueue(getContext());


        user_name= getArguments().getString("user_name");
        user_email= getArguments().getString("user_email");
        user_profile= getArguments().getString("user_profile");
        uniqueID = getArguments().getString("uniqueID");
        uniqueID2 = getArguments().getString("uniqueID2");
        id_email= getArguments().getString("id_email");
        user_name_profile= getArguments().getString("id_profile_picture");
        user_name_user = getArguments().getString("id_user_name");

        Log.i("key", user_name+"");
        Log.i("user", id_email+"");

        user_nameT.setText(user_name);
        if(uniqueID2!=null){
            user_emailT.setText(id_email);
            user_nameT.setText(user_name);
        }else{
            user_emailT.setText(user_email);

        }
        Picasso.with(getContext()).load(user_profile).into(profile_picture);



        toogle.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                follow();
                follower();
                Toast.makeText(getContext(), "You follow " + user_name, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getContext().getSharedPreferences("com.example.johnc.socialk", MODE_PRIVATE).edit();
                editor.putBoolean("followOnOff"+ user_email , true);
                editor.apply();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                unfollow();
                Toast.makeText(getContext(), "You unfollow " + user_name, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getContext().getSharedPreferences("com.example.johnc.socialk", MODE_PRIVATE).edit();
                editor.putBoolean("followOnOff" + user_email,false);
                editor.apply();

            }
        });
        SharedPreferences sharedPrefs = getContext().getSharedPreferences("com.example.johnc.socialk", MODE_PRIVATE);
        toogle.setLiked(sharedPrefs.getBoolean("followOnOff" + user_email, false));

        viewPager= view.findViewById(R.id.view);
        User_Class.ViewPagerAdapter viewPagerAdapter = new User_Class.ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.parseColor("#3f51b5"), Color.parseColor("#3f51b5"));
        return  view;

        }





    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }
        String[] tabs = {"Messages", "Favorites"};


        public Fragment getItem(int position){
            Bundle b = new Bundle();
            Fragment fragment;
            switch (position){

                case 0:
                    fragment = new User_Tweets();
                    b.putString("email",user_email);
                    b.putString("id_email",id_email);
                    b.putString("uniqueID", uniqueID);
                    b.putString("uniqueID2", uniqueID2);
                    fragment.setArguments(b);
                    return fragment;

                case 1:
                    fragment = new User_Favoritos();
                    b.putString("email",user_email);
                    b.putString("id_email",id_email);
                    b.putString("uniqueID", uniqueID);
                    b.putString("uniqueID2", uniqueID2);

                    fragment.setArguments(b);
                    return fragment;


            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
        public CharSequence getPageTitle(int position){
            return tabs[position];


        }

    }

    private void follow(){
        final String url = "http://192.168.1.130:8080/Follow/";
        final JSONObject jsonObject = new JSONObject();
        try {
            JSONObject user = new JSONObject();
            user.put("user_name", user_name);
            user.put("email", user_email);
            user.put("profile_image", user_profile);
            jsonObject.put("user_name", id_email);
            jsonObject.put("user", user);
            Log.i("holas", user_email+"");
            Log.i("holas", user_name+"");
            Log.i("holas", user_profile+"");


        } catch (JSONException e) {
            e.printStackTrace();

        }
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {




            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }


        };
        requestQueue.add(request);
    }
    private void follower(){
        final String url = "http://192.168.1.130:8080/Follower/";
        final JSONObject jsonObject = new JSONObject();
        try {
            JSONObject user = new JSONObject();
            user.put("user_name", user_name_user);
            user.put("email", id_email);
            user.put("profile_image", user_name_profile);
            jsonObject.put("user_name", user_email);
            jsonObject.put("user", user);
            Log.i("holas", user_name_user+"");
            Log.i("holas", user_name+"");
            Log.i("holas", user_name_profile+"");




        } catch (JSONException e) {
            e.printStackTrace();

        }
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {




            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }


        };
        requestQueue.add(request);
    }

    private void unfollow(){
        final String url = "http://192.168.1.130:8080/Follow/unfollow/" + user_email;



        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {




            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("X-HTTP-Method-Override", "DELETE");
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");

                return headers;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        requestQueue.add(request);

    }



}




