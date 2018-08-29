package com.example.johnc.socialk;




import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Followers extends Fragment {
    private RecyclerView recyclerView;
    private AdapterFollowers adapterFollowers;
    private ArrayList<Follower> followerArrayList;
    private RequestQueue requestQueue;
    String id_email;
    String user_name_profile;
    String user_name_user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment__followers, container, false);

        recyclerView =view.findViewById(R.id.recycling_followers);
        recyclerView.setHasFixedSize(true);
        id_email= getArguments().getString("id_email");
        user_name_profile= getArguments().getString("id_profile_picture");
        user_name_user = getArguments().getString("id_user_name");



        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        followerArrayList = new ArrayList<>();
        adapterFollowers = new AdapterFollowers(getActivity(), followerArrayList);
        requestQueue = Volley.newRequestQueue(getActivity());

        getFollowers();
        recyclerView.setAdapter(adapterFollowers);

        return  view;


    }
    private void getFollowers() {
        final String url = "http://192.168.1.130:8080/Follower/findAll/" + id_email;
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject data = response.getJSONObject(i);
                        String email = data.getString("user_name");
                        String user_name = data.getJSONObject("user").getString("user_name");
                        String user_email = data.getJSONObject("user").getString("email");
                        String user_profile = data.getJSONObject("user").getString("profile_image");
                        User u = new User(user_name, null, user_email, user_profile,id_email,user_name_user,user_name_profile);
                        if (id_email.equals(email)) {
                            Follower f = new Follower(email, u);
                            followerArrayList.add(f);
                        }


                    }
                    adapterFollowers = new AdapterFollowers(getActivity(), followerArrayList);
                    recyclerView.setAdapter(adapterFollowers);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }

        });
        requestQueue.add(request);

    }

}
