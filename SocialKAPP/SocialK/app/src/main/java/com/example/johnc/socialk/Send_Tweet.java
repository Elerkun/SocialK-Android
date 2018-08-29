package com.example.johnc.socialk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Send_Tweet extends AppCompatActivity {

    String user_name;
    String user_email;
    Uri uri;
    EditText text;
    ImageView image;
    Button send;
    RequestQueue requestQueue;
    private static final int RESULT_LOAD_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__tweet);
        Intent i = getIntent();
        user_name= i.getStringExtra("user_name");
        user_email= i.getStringExtra("id_email");
        requestQueue = Volley.newRequestQueue(Send_Tweet.this);
        image= findViewById(R.id.image);
        text = findViewById(R.id.Text);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_message();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
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
            Picasso.with(Send_Tweet.this).load(uri).into(image);

        }
    }
    private void send_message(){
        String url = "http://192.168.1.130:8080/Message";
        final JSONObject jsonObject = new JSONObject();
        try {

            final JSONObject user = new JSONObject();
            user.put("user_name", user_name);
            user.put("email", user_email);



            jsonObject.put("message", text.getText().toString());
            if(uri==null){
                uri= Uri.parse("No Photo");
                jsonObject.put("image",uri.toString() );
            }
            jsonObject.put("image", uri.toString());
            jsonObject.put("user", user);


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
