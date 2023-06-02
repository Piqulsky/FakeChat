package com.example.fakechat.settings;

import android.content.Context;
import android.net.Uri;
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
    private ImageButton imageButtonDelete;
    private EditText editTextActivity;
    private RecyclerView recyclerViewMessages;
    private SettingMessagesAdapter recyclerAdapter;
    private ImageButton imageButtonAdd;
    private Switch switchIsRead;
    private Context context;
    public SettingHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        editTextReceiver = itemView.findViewById(R.id.editTextSettingReceiver);
        imageViewAvatar = itemView.findViewById(R.id.imageViewSettingAvatar);
        imageButtonDelete = itemView.findViewById(R.id.imageButtonDeleteChat);
        editTextActivity = itemView.findViewById(R.id.editTextActivity);
        recyclerViewMessages = itemView.findViewById(R.id.recyclerViewSettingMessages);
        imageButtonAdd = itemView.findViewById(R.id.imageButtonAddMessage);
        switchIsRead = itemView.findViewById(R.id.switchIsRead);
    }
    public EditText getEditTextReceiver() {
        return editTextReceiver;
    }
    public void setReceiverName(String name){ editTextReceiver.setText(name); }
    public void setAvatarResource(String res){ imageViewAvatar.setImageURI(Uri.parse(res)); imageViewAvatar.setTag(res); }
    public void setSwitchOnline(String activity) {
        editTextActivity.setText(activity);
    }
    public EditText getEditTextActivity() {
        return editTextActivity;
    }
    public ImageButton getImageButtonDelete() {
        return imageButtonDelete;
    }
    public ImageButton getImageButtonAdd() {
        return imageButtonAdd;
    }
    public void setRecyclerViewMessages(ArrayList<ChatData> appData, int position) {
        recyclerAdapter = new SettingMessagesAdapter(appData, position, context);
        recyclerViewMessages.setAdapter(recyclerAdapter);
        recyclerViewMessages.setHasFixedSize(true);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(context));
    }
}
