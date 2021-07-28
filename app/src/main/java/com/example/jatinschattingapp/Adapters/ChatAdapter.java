package com.example.jatinschattingapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jatinschattingapp.R;
import com.example.jatinschattingapp.model.MessageModel;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {
ArrayList<MessageModel> messageModels;
Context context;
    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }


    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        if(viewType == SENDER_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_send,parent,false);
            return new SenderViewVolder(view);


        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_receive,parent,false);
            return new ReceiverViewVolder(view);
        }




    }

    @Override
    public int getItemViewType(int position) {
        if (messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid()))
           {
            return SENDER_VIEW_TYPE;
           }
        else
            {
            return RECEIVER_VIEW_TYPE;
            }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
     MessageModel messageModel = messageModels.get(position);

     if (holder.getClass() == SenderViewVolder.class)
 {
     ((SenderViewVolder)holder).SenderMsg.setText(messageModel.getMessage());

 Log.d("Welcome",messageModel.getMessage());
 }

     else
    {
        ((ReceiverViewVolder)holder).receiversMsg.setText(messageModel.getMessage());
        Log.d("Welcome",messageModel.getMessage());
    }


    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class ReceiverViewVolder extends RecyclerView.ViewHolder {

    TextView receiversMsg,time;
        public ReceiverViewVolder(@NonNull @NotNull View itemView) {
            super(itemView);
            receiversMsg = itemView.findViewById(R.id.receiver_text);
            time =itemView.findViewById(R.id.receiver_timestamp);
        }
    }
public class SenderViewVolder extends RecyclerView.ViewHolder {
    TextView SenderMsg,time;
        public SenderViewVolder(@NonNull @NotNull View itemView) {

        super(itemView);
        SenderMsg=itemView.findViewById(R.id.sender_text);
        time = itemView.findViewById(R.id.sender_timestamp);
    }
}

}
