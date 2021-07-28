package com.example.jatinschattingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jatinschattingapp.ChatActivity;
import com.example.jatinschattingapp.R;
import com.example.jatinschattingapp.model.user;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class usersAdapter extends RecyclerView.Adapter<usersAdapter.Viewholder> {


    ArrayList<user> list;
    Context context;

    public usersAdapter(ArrayList<user> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_users,parent,false);


        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull usersAdapter.Viewholder holder, int position) {
user usr = list.get(position);
        Picasso.get().load(usr.getProfilepic()).placeholder(R.drawable.dp).into(holder.profile_image);
        holder.username.setText(usr.getUsername());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("UserId",usr.getUserid());
            intent.putExtra("profile_image",usr.getProfilepic());
            intent.putExtra("nameusr",usr.getUsername());


            context.startActivity(intent);
        }
    });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView profile_image;
        TextView username ,last_msg;

        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            profile_image =itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.username);
            last_msg= itemView.findViewById(R.id.last_msg);

        }
    }
}
