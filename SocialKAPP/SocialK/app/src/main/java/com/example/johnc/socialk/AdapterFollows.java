package com.example.johnc.socialk;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterFollows extends RecyclerView.Adapter<AdapterFollows.FollowsViewHolder>{
    private Context context;
    private ArrayList<Follow> follows;
    boolean FragmentTransaction= false;

    public AdapterFollows(Context context, ArrayList<Follow> follows) {
        this.context = context;
        this.follows = follows;
    }

    @NonNull
    @Override
    public FollowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycling_view_follows, parent, false);
        return new AdapterFollows.FollowsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowsViewHolder holder, int position) {
        final Follow f = follows.get(position);
        final String user_name = f.getUser().getUser_name();
        final String user_email = f.getUser().getEmail();
        final String profile_picture = f.getUser().getProfile_image();
        final String email = f.getUser().getUser();
        final String user_user_name = f.getUser().getUser_nameUser();
        final String user_user_profile =f.getUser().getProfile_imageUser();



        holder.email.setText(user_email);
        holder.user_name.setText(user_name);
        Picasso.with(context).load(profile_picture).into(holder.profile_picture);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment;
                fragment= new User_Class();
                Bundle b = new Bundle();
                b.putString("user_name", user_name);
                b.putString("user_email", user_email);
                b.putString("user_profile", profile_picture);
                b.putString("id_email", email);
                b.putString("id_user_name", user_user_name);
                b.putString("id_profile_picture", user_user_profile);
                fragment.setArguments(b);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();







            }
        });

    }

    @Override
    public int getItemCount() {
        return follows.size();
    }

    public class FollowsViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_picture;
        TextView user_name;
        TextView email;
        CardView cv;


        public FollowsViewHolder(View view){
            super(view);
            profile_picture = view.findViewById(R.id.user_image);
            user_name = view.findViewById(R.id.user_name);
            email = view.findViewById(R.id.email);
            cv = itemView.findViewById(R.id.cv);

        }
    }
}
