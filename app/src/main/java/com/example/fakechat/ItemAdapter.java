package com.example.fakechat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    public ItemAdapter(List<MessagesData> list){
        dataStore = list;
    }
    private List<MessagesData> dataStore;

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_message_received, parent, false);
        if(true)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_message_sent, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        //holder.getTextViewName().setText(dataStore.get(position).name);
    }

    @Override
    public int getItemCount() {
        return dataStore.size();
    }
}
