package com.example.jatinschattingapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jatinschattingapp.Adapters.usersAdapter;
import com.example.jatinschattingapp.ChatActivity;
import com.example.jatinschattingapp.R;
import com.example.jatinschattingapp.databinding.FragmentChatBinding;
import com.example.jatinschattingapp.model.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ChatFragment extends Fragment {


//FragmentChatBinding binding;
    public ChatFragment() {
        // Required empty public constructor
    }

ArrayList<user> list = new ArrayList<>();
    FragmentChatBinding binding;
FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentChatBinding.inflate(inflater, container, false);
database=FirebaseDatabase.getInstance();

        usersAdapter adapter =new usersAdapter(list, getContext());
   binding.chatRv.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.chatRv.setLayoutManager(linearLayoutManager);

        database.getReference().child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

               list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    user usr = dataSnapshot.getValue(user.class);
                    usr.setUserid(dataSnapshot.getKey());
                    list.add(usr);


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



        return binding.getRoot();
    }
}