package com.example.fakechat.settings;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.ChatData;
import com.example.fakechat.messages.MessageData;

import java.util.ArrayList;

public class SettingMessagesAdapter extends RecyclerView.Adapter<SettingMessageHolder> {
    private ArrayList<MessageData> list;
    private Context context;
    public SettingMessagesAdapter(ArrayList<MessageData> appData, Context context){
        this.list = list; this.context = context;
    }
    @NonNull
    @Override
    public SettingMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingMessageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
