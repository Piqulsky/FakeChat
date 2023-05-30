package com.example.fakechat.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;
import com.example.fakechat.MessageData;

import java.util.ArrayList;

public class SettingMessagesAdapter extends RecyclerView.Adapter<SettingMessageHolder> {
    private ArrayList<MessageData> list;
    private Context context;
    public SettingMessagesAdapter(ArrayList<MessageData> list, Context context){
        this.list = list; this.context = context;
    }
    @NonNull
    @Override
    public SettingMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_setting_message, parent, false);
        return new SettingMessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingMessageHolder holder, int position) {
        holder.setEditTextMessage(list.get(position).getItemMessage());
        if(list.get(position).getItemViewType() == MessageData.LAYOUT_MESSAGE_SENT)
            holder.setSwitchWhose(true);
        else if(list.get(position).getItemViewType() == MessageData.LAYOUT_MESSAGE_RECEIVED)
            holder.setSwitchWhose(false);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
