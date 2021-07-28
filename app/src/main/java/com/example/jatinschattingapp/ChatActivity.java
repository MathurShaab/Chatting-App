package com.example.jatinschattingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jatinschattingapp.Adapters.ChatAdapter;
import com.example.jatinschattingapp.databinding.ActivityChatBinding;
import com.example.jatinschattingapp.model.MessageModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {
ActivityChatBinding binding;
FirebaseDatabase database;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        //getSupportActionBar().hide();
        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        final String Senderid= auth.getUid();
        String receiverId = getIntent().getStringExtra("UserId");
        String username = getIntent().getStringExtra("nameusr");
        String profilepic= getIntent().getStringExtra("profile_image");
        binding.nameusr.setText(username);
        Picasso.get().load(profilepic).placeholder(R.drawable.dp).into(binding.profileImage);
        binding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        final ArrayList<MessageModel> messageModels = new ArrayList<>();
        final ChatAdapter chatAdapter = new ChatAdapter(messageModels,this);
        binding.chatrecyclerview.setAdapter(chatAdapter);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        binding.chatrecyclerview.setLayoutManager(linearLayoutManager);


        final String senderRoom = Senderid + receiverId;
        final String receiversRoom = receiverId + Senderid;

database.getReference().child("chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

        messageModels.clear();

        for (DataSnapshot snapshot1 : snapshot.getChildren())
        {
               Log.d("TAG",senderRoom);
               Log.d("TAG",receiversRoom);
            MessageModel model = snapshot1.getValue(MessageModel.class);
            messageModels.add(model);


        }
             chatAdapter.notifyDataSetChanged();
    }
    @Override
    public void onCancelled(@NonNull @NotNull DatabaseError error) {
        Log.d("TAG", "Failed to read value.", error.toException());
        Toast.makeText(ChatActivity.this, (CharSequence) error.toException(),Toast.LENGTH_SHORT).show();
    }
});
        //final String message=binding.etMessage.getText().toString();
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=binding.etMessage.getText().toString();

                final MessageModel model = new MessageModel(Senderid,message);
                model.setTimestamp(new Date().getTime());
                binding.etMessage.setText("");
                database.getReference().child("chats").child(senderRoom).push().setValue(model)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                       database.getReference().child("chats").child(receiversRoom)
                       .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void unused) {

                           }
                       });
                    }
                });
            }
        });








    }
}