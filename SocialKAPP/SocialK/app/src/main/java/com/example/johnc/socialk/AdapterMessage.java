package com.example.johnc.socialk;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class AdapterMessage  extends RecyclerView.Adapter<AdapterMessage.MessageViewHolder>{
    private Context context;
    private ArrayList<Message> messages;



    public AdapterMessage(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycling_view_message, viewGroup, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, final int i) {
         final Message m =messages.get(i);
         final String user_name = m.getUser().getUser_name();
         final String message = m.getMessage();
         final String image = m.getImage();
         final String email = m.getUser().getEmail();
         final String email_user = m.getUser().getUser();

         messageViewHolder.user_name.setText(user_name);
         messageViewHolder.message.setText(message);
         Picasso.with(context).load(image).fit().centerInside().into(messageViewHolder.message_image);
         messageViewHolder.toogle.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Toast.makeText(context, "You like" + " " + message, Toast.LENGTH_SHORT).show();

                final JSONObject jsonObject = new JSONObject();
                final JSONObject user = new JSONObject();
                final JSONObject messages = new JSONObject();
                try {


                    user.put("email", email);
                    user.put("user_name", user_name);

                    messages.put("message", message);
                    messages.put("image",image);
                    messages.put("user", user);


                    jsonObject.put("message", messages);
                    jsonObject.put("email", email_user);
                    Log.i("user", email_user+"");



                } catch (JSONException e) {
                    e.printStackTrace();

                }

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                final String url = "http://192.168.1.130:8080/Favorito";
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
                SharedPreferences.Editor editor = context.getSharedPreferences("com.example.johnc.socialk", MODE_PRIVATE).edit();
                editor.putBoolean("followOnOff" + message , true);
                editor.apply();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Toast.makeText(context, "You unliked" + " "+ message, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = context.getSharedPreferences("com.example.johnc.socialk", MODE_PRIVATE).edit();
                editor.putBoolean("followOnOff" + message , true);
                editor.apply();

                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    final String url = "http://192.168.1.130:8080/Favorito/unliked/" + email +"/"+ message;


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



        });
        SharedPreferences sharedPrefs = context.getSharedPreferences("com.example.johnc.socialk", MODE_PRIVATE);
        messageViewHolder.toogle.setLiked(sharedPrefs.getBoolean("followOnOff" +message , false));



    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView user_name;
        TextView message;
        ImageView message_image;
        LikeButton toogle;

          public MessageViewHolder (View item){
              super(item);
              user_name = item.findViewById(R.id.user_name);
              message = item.findViewById(R.id.message);
              message_image=item.findViewById(R.id.user_image);
              toogle = item.findViewById(R.id.toogle);


          }


      }



}
