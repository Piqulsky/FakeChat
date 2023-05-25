package com.example.fakechat.chats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatHolder> {
    private ArrayList<String> list;
    private Context context;
    public ChatsAdapter(ArrayList<String> list, Context context){
        this.list = list; this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
