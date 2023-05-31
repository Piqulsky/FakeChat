package com.example.fakechat.settings;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.ChatData;
import com.example.fakechat.R;
import com.example.fakechat.MessageData;

import java.util.ArrayList;

public class SettingHolder extends RecyclerView.ViewHolder {
    private EditText editTextReceiver;
    private ImageView imageViewAvatar;
    private ImageButton imageViewMore;
    private Switch switchOnline;
    private RecyclerView recyclerViewMessages;
    private SettingMessagesAdapter recyclerAdapter;
    private Context context;
    public SettingHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        editTextReceiver = itemView.findViewById(R.id.editTextSettingReceiver);
        imageViewAvatar = itemView.findViewById(R.id.imageViewSettingAvatar);
        imageViewMore = itemView.findViewById(R.id.imageViewMore);
        switchOnline = itemView.findViewById(R.id.switchOnline);
        recyclerViewMessages = itemView.findViewById(R.id.recyclerViewSettingMessages);
    }

    public void setReceiverName(String name){editTextReceiver.setText(name);}
    public void setAvatarResource(int res){imageViewAvatar.setImageResource(res); imageViewAvatar.setTag(res);}

    public void setSwitchOnline(Boolean isOnline) {
        switchOnline.setChecked(isOnline);
    }

    public void setRecyclerViewMessages(ArrayList<MessageData> arrayList) {
        recyclerAdapter = new SettingMessagesAdapter(arrayList, context, (Integer)imageViewAvatar.getTag());
        recyclerViewMessages.setAdapter(recyclerAdapter);
        recyclerViewMessages.setHasFixedSize(true);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(context));
    }

    public ImageButton getImageViewMore() {
        return imageViewMore;
    }
}
