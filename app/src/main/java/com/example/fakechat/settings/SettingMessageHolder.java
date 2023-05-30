package com.example.fakechat.settings;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;
import com.example.fakechat.MessageData;

import java.util.ArrayList;

public class SettingMessageHolder extends RecyclerView.ViewHolder {
    private EditText editTextMessage;
    private ImageView imageViewReceived;
    private ImageView imageViewSent;
    private Switch switchWhose;

    private ArrayList<MessageData> recyclerArrayList;
    public SettingMessageHolder(@NonNull View itemView) {
        super(itemView);
        editTextMessage = itemView.findViewById(R.id.editTextSettingMessage);
        imageViewReceived = itemView.findViewById(R.id.imageViewReceived);
        imageViewSent = itemView.findViewById(R.id.imageViewSent);
        switchWhose = itemView.findViewById(R.id.switchWhoseMessage);
        switchWhose.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                imageViewSent.setVisibility(View.VISIBLE);
                imageViewReceived.setVisibility(View.INVISIBLE);
            }
            else {
                imageViewSent.setVisibility(View.INVISIBLE);
                imageViewReceived.setVisibility(View.VISIBLE);
            }
        });
    }

    public void setEditTextMessage(String message) {
        editTextMessage.setText(message);
    }

    public void setSwitchWhose(Boolean whose) {
        switchWhose.setChecked(whose);
        if(whose){
            imageViewSent.setVisibility(View.VISIBLE);
            imageViewReceived.setVisibility(View.INVISIBLE);
        }
        else {
            imageViewSent.setVisibility(View.INVISIBLE);
            imageViewReceived.setVisibility(View.VISIBLE);
        }
    }

    public MessageData getMessageData(){
        if(switchWhose.isChecked())
            return new MessageData(MessageData.LAYOUT_MESSAGE_SENT, editTextMessage.getText().toString());
        else
            return new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, editTextMessage.getText().toString());
    }
}
