package com.example.johnc.socialk;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class Fragment_Tweets extends Fragment {

    private RecyclerView recyclerView;
    private AdapterMessage adapterMessage;
    private ArrayList<Message> messageArrayList;
    private RequestQueue requestQueue;
    String id_email;
    String user_name_profile;
    String user_name_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment__tweets, container, false);
        recyclerView =view.findViewById(R.id.recycling_message);
        recyclerView.setHasFixedSize(true);
        id_email = getArguments().getString("id_email");
        user_name_profile= getArguments().getString("id_profile_picture");
        user_name_user = getArguments().getString("id_user_name");




        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        messageArrayList = new ArrayList<>();
        adapterMessage = new AdapterMessage(getActivity(), messageArrayList);
        requestQueue = Volley.newRequestQueue(getActivity());

        getMessages();
        recyclerView.setAdapter(adapterMessage);

        return view;
    }

    private void getMessages(){
        final String url = "http://192.168.1.130:8080/Message/findAll/" ;
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject data = response.getJSONObject(i);
                        String message = data.getString("message");
                        String image = data.getString("image");
                        String user_name = data.getJSONObject("user").getString("user_name");
                        String email = data.getJSONObject("user").getString("email");
                        User u = new User(user_name,null,email,null,id_email,user_name_user,user_name_profile);
                        Message m = new Message(message, image, u);
                        messageArrayList.add(m);



                    }
                    adapterMessage = new AdapterMessage(getActivity(), messageArrayList);
                    recyclerView.setAdapter(adapterMessage);

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
