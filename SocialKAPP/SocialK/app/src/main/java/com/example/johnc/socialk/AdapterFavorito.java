package com.example.johnc.socialk;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

public class AdapterFavorito extends RecyclerView.Adapter<AdapterFavorito.FavoritoViewHolder> {
    private Context context;
    private ArrayList<Favorito> favoritos;
    private RequestQueue requestQueue;



    public AdapterFavorito(Context context, ArrayList<Favorito> favoritos) {
        this.context = context;
        this.favoritos = favoritos;
    }

    @NonNull
    @Override
    public FavoritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycling_view_message, parent, false);
        return new AdapterFavorito.FavoritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritoViewHolder holder, int position) {
        final Favorito f = favoritos.get(position);
        final String user_name = f.getMessage().getUser().getUser_name();
        final String message = f.getMessage().getMessage();
        final String image = f.getMessage().getImage();
        final String user = f.getEmail();

        holder.user_name.setText(user_name);
        holder.message.setText(message);
        Picasso.with(context).load(image).fit().centerInside().into(holder.message_image);





    }




    @Override
    public int getItemCount() {
        return favoritos.size();
    }

    public class FavoritoViewHolder extends  RecyclerView.ViewHolder{
        TextView user_name;
        TextView message;
        ImageView message_image;
        LikeButton toogle;

        public FavoritoViewHolder(View itemView) {
            super(itemView);

            user_name = itemView.findViewById(R.id.user_name);
            message = itemView.findViewById(R.id.message);
            message_image=itemView.findViewById(R.id.user_image);
            toogle = itemView.findViewById(R.id.toogle);


        }
    }
}
