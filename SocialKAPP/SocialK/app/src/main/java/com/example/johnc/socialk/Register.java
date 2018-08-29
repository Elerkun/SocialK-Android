package com.example.johnc.socialk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    String emailGet;
    RequestQueue requestQueue;
    EditText email;
    EditText user_name;
    Button register;
    EditText pass;
    Button log_in;
    ImageView profile_picture;
    Uri uri;

    private static final int RESULT_LOAD_IMAGE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        requestQueue = Volley.newRequestQueue(Register.this);
        email = findViewById(R.id.email);
        user_name = findViewById(R.id.user_name);
        register = findViewById(R.id.register);
        pass = findViewById(R.id.pass);
        log_in = findViewById(R.id.login);
        profile_picture = findViewById(R.id.profile_image);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    userInsert();
                    Intent intent = new Intent(Register.this, LogIn.class);
                    startActivity(intent);




            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LogIn.class);
                startActivity(intent);
            }
        });

        profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromAlbum();
            }
        });



    }

    private void getImageFromAlbum() {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK && reqCode == RESULT_LOAD_IMAGE) {
            uri = data.getData();
            Picasso.with(Register.this).load(uri).into(profile_picture);
        }
    }

    final JSONObject jsonObject = new JSONObject();

    private void userInsert() {

        String url = "http://192.168.1.130:8080/User";

        try {

            jsonObject.put("email", email.getText().toString());
            jsonObject.put("user_name", user_name.getText().toString());
            jsonObject.put("pass", pass.getText().toString());
            jsonObject.put("profile_image", uri.toString());


        } catch (JSONException e) {
            e.printStackTrace();

        }catch (NullPointerException n){
            Toast.makeText(Register.this,"Please fill up the form", Toast.LENGTH_SHORT).show();
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








