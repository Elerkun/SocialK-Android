package com.example.johnc.socialk;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AdapterUserMessage extends RecyclerView.Adapter<AdapterUserMessage.UserMessageViewHolder> {
    private Context context;
    private ArrayList<Message> messages;

    public AdapterUserMessage(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public AdapterUserMessage.UserMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycling_view_message, parent, false);
        return new AdapterUserMessage.UserMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterUserMessage.UserMessageViewHolder holder, int position) {
        final Message m =messages.get(position);
        final String user_name = m.getUser().getUser_name();
        final String message = m.getMessage();
        final String image = m.getImage();
        final String user= m.getUser().getEmail();
        holder.user_name.setText(user_name);
        holder.message.setText(message);
        Picasso.with(context).load(image).fit().centerInside().into(holder.message_image);




    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class UserMessageViewHolder extends RecyclerView.ViewHolder{
        TextView user_name;
        TextView message;
        ImageView message_image;
        LikeButton toogle;



        public UserMessageViewHolder(View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            message = itemView.findViewById(R.id.message);
            message_image=itemView.findViewById(R.id.user_image);
            toogle = itemView.findViewById(R.id.toogle);





        }
    }
}
