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

public class AdapterUser  extends RecyclerView.Adapter<AdapterUser.UserViewHolder>{

    private Context context;
    private ArrayList<User> users;



    public AdapterUser(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycling_view_user, parent, false);
        return new AdapterUser.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User u = users.get(position);
        final String user_name = u.getUser_name();
        final String user_email = u.getEmail();
        final String user_profile = u.getProfile_image();
        final String user = u.getUser();
        final String user_user_name = u.getUser_nameUser();
        final String user_user_profile =u.getProfile_imageUser();



        holder.email.setText(user_email);
        holder.user_name.setText(user_name);
        Picasso.with(context).load(user_profile).into(holder.profile_picture);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment;
                fragment= new User_Class();
                Bundle b = new Bundle();
                b.putString("user_name", user_name);
                b.putString("user_email", user_email);
                b.putString("user_profile", user_profile);
                b.putString("id_email", user);
                b.putString("id_user_name", user_user_name);
                b.putString("id_profile_picture", user_user_profile);
                b.putString("uniqueID", "uniqueID");
                fragment.setArguments(b);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();




            }
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_picture;
        TextView user_name;
        TextView email;
        CardView cv;



        public UserViewHolder(View itemView) {
            super(itemView);
            profile_picture = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            email = itemView.findViewById(R.id.email);
            cv = itemView.findViewById(R.id.cv);


        }
    }
}
