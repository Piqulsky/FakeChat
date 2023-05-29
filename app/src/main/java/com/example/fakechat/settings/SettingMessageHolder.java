package com.example.fakechat.settings;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;
import com.example.fakechat.messages.MessageData;

import java.util.ArrayList;

public class SettingMessageHolder extends RecyclerView.ViewHolder {
    private EditText editTextReceiver;
    private ImageView imageViewAvatar;
    private ImageButton imageViewButton;
    private Switch switchOnline;
    private RecyclerView recyclerViewMessages;

    private ArrayList<MessageData> recyclerArrayList;
    public SettingMessageHolder(@NonNull View itemView) {
        super(itemView);
        editTextReceiver = itemView.findViewById(R.id.editTextSettingReceiver);
        imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
        imageViewButton = itemView.findViewById(R.id.imageViewButton);
        switchOnline = itemView.findViewById(R.id.switchOnline);
        recyclerViewMessages = itemView.findViewById(R.id.recyclerViewSettingMessages);
    }

    public void setReceiverName(String name){editTextReceiver.setText(name);}
    public void setAvatarResource(int res){imageViewAvatar.setImageResource(res);}

    public void setSwitchOnline(Boolean isOnline) {
        switchOnline.setChecked(isOnline);
    }

    public void setRecyclerViewMessages(ArrayList<MessageData> arrayList) {

    }

    public ImageButton getImageButton() {
        return imageViewButton;
    }
}
