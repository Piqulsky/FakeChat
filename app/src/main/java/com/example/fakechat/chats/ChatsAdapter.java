package com.example.fakechat.chats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.ChatData;
import com.example.fakechat.R;
import com.example.fakechat.messages.MainActivity;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatHolder> {
    private ArrayList<String> list;
    private ArrayList<ChatData> appData;
    private Context context;
    public ChatsAdapter(ArrayList<String> list, Context context, ArrayList<ChatData> appData){
        this.list = list; this.context = context; this.appData = appData;
    }
    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_chat, parent, false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        holder.setReceiverName(list.get(position));
        holder.getImageViewButton().setOnClickListener(view -> {
            Intent chat =  new Intent(context, MainActivity.class);
            chat.putExtra("AppData", appData);
            chat.putExtra("ReceiverIndex", position);
            context.startActivity(chat);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
