package com.example.johnc.socialk;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


public class User_Favoritos extends Fragment {
    private RecyclerView recyclerView;
    private AdapterFavorito adapterFavorito;
    private ArrayList<Favorito> favoritoArrayList;
    private RequestQueue requestQueue;
    String user_nameT;
    String id_email;
    String uniqueID;
    String uniqueID2;
    String user_name_profile;
    String user_name_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_user__favoritos, container, false);
        recyclerView =view.findViewById(R.id.user_favorites);
        recyclerView.setHasFixedSize(true);
        user_nameT = getArguments().getString("email");
        id_email = getArguments().getString("id_email");
        user_name_profile= getArguments().getString("id_profile_picture");
        user_name_user = getArguments().getString("id_user_name");
        uniqueID = getArguments().getString("uniqueID");
        uniqueID2 = getArguments().getString("uniqueID2");
        Log.i("User", user_nameT+"");


        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        favoritoArrayList = new ArrayList<>();
        adapterFavorito = new AdapterFavorito(getContext(), favoritoArrayList);
        requestQueue = Volley.newRequestQueue(getContext());
        if(uniqueID2!=null){
            getFavoritobyUser_name(id_email);
        }else{
            getFavoritobyUser_name(user_nameT);
        }

        recyclerView.setAdapter(adapterFavorito);

        return view;

    }
    private void getFavoritobyUser_name(final String user_nameget){
        final String url = "http://192.168.1.130:8080/Favorito/findAll/" + user_nameget ;
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject data = response.getJSONObject(i);
                        String message = data.getJSONObject("message").getString("message");
                        String image = data.getJSONObject("message").getString("image");
                        String user_name = data.getJSONObject("message").getJSONObject("user").getString("user_name");
                        String email = data.getJSONObject("message").getJSONObject("user").getString("email");
                        User u = new User(user_name,null,email,null,id_email,user_name_user,user_name_profile);
                        Message m = new Message(message, image, u);
                        Favorito f = new Favorito(user_nameget, m);
                        favoritoArrayList.add(f);





                    }
                    adapterFavorito = new AdapterFavorito(getContext(), favoritoArrayList);
                    recyclerView.setAdapter(adapterFavorito);

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
