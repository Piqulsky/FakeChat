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
    private EditText editTextActivity;
    private RecyclerView recyclerViewMessages;
    private SettingMessagesAdapter recyclerAdapter;
    private Context context;
    public SettingHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        editTextReceiver = itemView.findViewById(R.id.editTextSettingReceiver);
        imageViewAvatar = itemView.findViewById(R.id.imageViewSettingAvatar);
        editTextActivity = itemView.findViewById(R.id.editTextActivity);
        recyclerViewMessages = itemView.findViewById(R.id.recyclerViewSettingMessages);
    }
    public EditText getEditTextReceiver() {
        return editTextReceiver;
    }
    public void setReceiverName(String name){editTextReceiver.setText(name);}
    public void setAvatarResource(int res){imageViewAvatar.setImageResource(res); imageViewAvatar.setTag(res);}
    public void setSwitchOnline(String activity) {
        editTextActivity.setText(activity);
    }
    public EditText getEditTextActivity() {
        return editTextActivity;
    }
    public void setRecyclerViewMessages(ArrayList<ChatData> appData, int position) {
        recyclerAdapter = new SettingMessagesAdapter(appData, position, context, (Integer)imageViewAvatar.getTag());
        recyclerViewMessages.setAdapter(recyclerAdapter);
        recyclerViewMessages.setHasFixedSize(true);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(context));
    }
}
