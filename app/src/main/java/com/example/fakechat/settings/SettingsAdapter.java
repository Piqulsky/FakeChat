package com.example.fakechat.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.ChatData;
import com.example.fakechat.R;

import java.util.ArrayList;

public class SettingsAdapter extends RecyclerView.Adapter<SettingHolder>{
    private ArrayList<ChatData> appData;
    private Context context;
    private ArrayList<SettingHolder> settingHolders;
    public SettingsAdapter(ArrayList<ChatData> appData, Context context){
        this.appData = appData; this.context = context; settingHolders = new ArrayList<>();
    }
    @NonNull
    @Override
    public SettingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_setting, parent, false);
        SettingHolder holder = new SettingHolder(view);
        settingHolders.add(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingHolder holder, int position) {
        holder.setReceiverName(appData.get(position).getReceiverName());
        holder.setAvatarResource(appData.get(position).getAvatar());
        holder.setSwitchOnline(true); //DEBUG
        holder.setRecyclerViewMessages(appData.get(position).getMessagesData()); //DEBUG
//        holder.getImageViewMore().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(holder.getRecyclerArrayList() != appData.get(holder.getAdapterPosition()).getMessagesData())
//                    holder.setRecyclerViewMessages(appData.get(holder.getAdapterPosition()).getMessagesData());
//                else
//                    holder.setRecyclerViewMessages(new ArrayList<>());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return appData.size();
    }

    public ArrayList<SettingHolder> getSettingHolders() {
        return settingHolders;
    }
}
