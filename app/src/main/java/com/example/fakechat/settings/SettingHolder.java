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
import com.example.fakechat.DelayedData;
import com.example.fakechat.MessageData;
import com.example.fakechat.R;

import java.util.ArrayList;

public class SettingHolder extends RecyclerView.ViewHolder {
    private final EditText editTextReceiver;
    private final ImageView imageViewAvatar;
    private final ImageButton imageButtonDelete;
    private final EditText editTextActivity;
    private final RecyclerView recyclerViewMessages;
    private final ImageButton imageButtonAdd;
    private final Switch switchIsRead;
    private final RecyclerView recyclerViewDelayed;
    private final ImageButton imageButtonAddDelayed;
    private final Context context;
    private String sentHex;
    private String sentTextHex;
    private String receivedHex;
    private String receivedTextHex;
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
        recyclerViewDelayed = itemView.findViewById(R.id.recyclerViewSettingDelayed);
        imageButtonAddDelayed = itemView.findViewById(R.id.imageButtonAddDelayed);

    }
    public EditText getEditTextReceiver() {
        return editTextReceiver;
    }
    public void setReceiverName(String name){ editTextReceiver.setText(name); }
    public void setAvatarResource(String res){ imageViewAvatar.setImageURI(Uri.parse(res)); imageViewAvatar.setTag(res); }
    public void setEditTextActivity(String activity) {
        editTextActivity.setText(activity);
    }
    public void setSentHex(String sentHex){
        this.sentHex = sentHex;
    }
    public void setSentTextHex(String sentTextHex){
        this.sentTextHex = sentTextHex;
    }
    public void setReceivedHex(String receivedHex){
        this.receivedHex = receivedHex;
    }
    public void setReceivedTextHex(String receivedTextHex){
        this.receivedTextHex = receivedTextHex;
    }
    public EditText getEditTextActivity() {
        return editTextActivity;
    }
    public Switch getSwitchIsRead() {
        return switchIsRead;
    }
    public ImageButton getImageButtonDelete() {
        return imageButtonDelete;
    }
    public ImageButton getImageButtonAdd() {
        return imageButtonAdd;
    }
    public void setRecyclerViewMessages(ArrayList<MessageData> messages) {
        SettingMessagesAdapter recyclerAdapter = new SettingMessagesAdapter(messages, context, sentHex, sentTextHex, receivedHex, receivedTextHex);
        recyclerViewMessages.setAdapter(recyclerAdapter);
        recyclerViewMessages.setHasFixedSize(true);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(context));
    }
    public ImageButton getImageButtonAddDelayed(){
        return imageButtonAddDelayed;
    }
    public void setRecyclerViewDelayed(ArrayList<DelayedData> messages) {
        SettingDelayedAdapter recyclerAdapter = new SettingDelayedAdapter(messages, context, receivedHex, receivedTextHex);
        recyclerViewDelayed.setAdapter(recyclerAdapter);
        recyclerViewDelayed.setHasFixedSize(true);
        recyclerViewDelayed.setLayoutManager(new LinearLayoutManager(context));
    }
}
