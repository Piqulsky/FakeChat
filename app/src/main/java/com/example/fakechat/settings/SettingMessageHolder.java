package com.example.fakechat.settings;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;
import com.example.fakechat.MessageData;

import java.util.ArrayList;

public class SettingMessageHolder extends RecyclerView.ViewHolder {
    private EditText editTextMessage;
    private ImageButton imageButtonDeleteMessage;
    private ImageView imageViewReceived;
    private ImageView imageViewSent;
    private Switch switchWhose;
    private Switch switchRead;

    public SettingMessageHolder(@NonNull View itemView) {
        super(itemView);
        editTextMessage = itemView.findViewById(R.id.editTextSettingMessage);
        imageButtonDeleteMessage = itemView.findViewById(R.id.imageButtonDeleteMessage);
        imageViewReceived = itemView.findViewById(R.id.imageViewReceived);
        imageViewSent = itemView.findViewById(R.id.imageViewSent);
        switchRead = itemView.findViewById(R.id.switchRead);
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

    public void setSwitchRead(Boolean read) {
        switchRead.setChecked(read);
    }

    public EditText getEditTextMessage() {
        return editTextMessage;
    }

    public ImageButton getImageButtonDeleteMessage() {
        return imageButtonDeleteMessage;
    }

    public Switch getSwitchRead() {
        return switchRead;
    }

    public Switch getSwitchWhose() {
        return switchWhose;
    }
}
