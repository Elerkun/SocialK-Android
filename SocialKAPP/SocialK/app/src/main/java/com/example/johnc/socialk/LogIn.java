package com.example.johnc.socialk;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {

    RequestQueue requestQueue;
    EditText emailET;
    EditText passET;
    Button logInButton;
    Button registerButton;
    boolean FragmentTransaction=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailET = findViewById(R.id.email);
        passET = findViewById(R.id.pass);
        logInButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
            }
        });
        requestQueue = Volley.newRequestQueue(LogIn.this);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogIn();
            }
        });

        }

    private void LogIn() {
        final String url = "http://192.168.1.130:8080/User/findOne/" + emailET.getText().toString();

        Log.i("hola", url);
            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    for (int i = 0; i < response.length(); i++) {


                        String email = response.getString("email");
                        String pass = response.getString("pass");
                        String user_name = response.getString("user_name");
                        String profile_picture = response.getString("profile_image");
                        if(email.equals(emailET.getText().toString()) && pass.equals(passET.getText().toString())){
                            Toast.makeText(LogIn.this, "Log In Succesful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogIn.this, MainActivity.class);
                            intent.putExtra("id_email", email);
                            intent.putExtra("pass", pass);
                            intent.putExtra("user_name", user_name);
                            intent.putExtra("profile_picture", profile_picture);
                            Fragment f = new Fragment_Tweets();
                            Bundle b = new Bundle();
                            b.putString("id_email", email);
                            f.setArguments(b);
                            FragmentTransaction = true;
                            Log.i("hola", profile_picture);
                            startActivity(intent);

                        }else{
                            Toast.makeText(LogIn.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    } catch (JSONException e) {
                           e.printStackTrace();

                    }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LogIn.this, "Log In Failed", Toast.LENGTH_SHORT).show();

            }

        });
        requestQueue.add(request);


    }

}




