package com.example.johnc.socialk;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

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


public class Search_User extends Fragment {

    private RecyclerView recyclerView;
    private AdapterUser adapterUser;
    private ArrayList<User> usersArrayList;
    private RequestQueue requestQueue;
    String id_email;
    String user_name_profile;
    String user_name_user;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_search__user, container, false);
        recyclerView =view.findViewById(R.id.recycling_user);
        recyclerView.setHasFixedSize(true);
        id_email= getArguments().getString("id_email");
        user_name_profile= getArguments().getString("id_profile_picture");
        user_name_user = getArguments().getString("id_user_name");





        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        usersArrayList = new ArrayList<>();
        adapterUser = new AdapterUser(getActivity(),usersArrayList);

        requestQueue = Volley.newRequestQueue(getActivity());


        recyclerView.setAdapter(adapterUser);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                final EditText input = new EditText(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setTitle("Search....");
                alertDialog.setView(input);
                alertDialog.setPositiveButton("Search",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                getUser(input.getText().toString());


                            }
                        });
                alertDialog.show();

            }


        });

        return view;
    }
    private void getUser(String searchUser_name) {
        final String url = "http://192.168.1.130:8080/User/findAll/" + searchUser_name;
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject data = response.getJSONObject(i);
                        String user_name = data.getString("user_name");
                        String user_email = data.getString("email");
                        String user_profile = data.getString("profile_image");
                        User u = new User(user_name, null, user_email, user_profile, id_email,user_name_user,user_name_profile);
                        usersArrayList.add(0,u);



                    }
                    adapterUser = new AdapterUser(getActivity(), usersArrayList);
                    recyclerView.setAdapter(adapterUser);

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
