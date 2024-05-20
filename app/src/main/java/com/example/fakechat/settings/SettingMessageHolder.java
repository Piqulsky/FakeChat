package com.example.fakechat.settings;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;
import com.example.fakechat.MessageData;
import com.example.fakechat.messages.MainActivity;

import java.util.ArrayList;

public class SettingMessageHolder extends RecyclerView.ViewHolder {
    private EditText editTextMessage;
    private ImageButton imageButtonDeleteMessage;
    private ImageView imageViewReceived;
    private ImageView imageViewSent;
    private Switch switchWhose;
    private Switch switchRead;
    private int sentColor;
    private int sentTextColor;
    private int receivedColor;
    private int receivedTextColor;

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
                editTextMessage.setBackgroundResource(R.drawable.message_bubble_sent);
                editTextMessage.getBackground().setColorFilter(sentColor, PorterDuff.Mode.MULTIPLY);
                imageViewSent.setVisibility(View.VISIBLE);
                imageViewReceived.setVisibility(View.INVISIBLE);
            }
            else {
                editTextMessage.setBackgroundResource(R.drawable.message_bubble_received);
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
            editTextMessage.setBackgroundResource(R.drawable.message_bubble_sent);
            editTextMessage.getBackground().setColorFilter(sentColor, PorterDuff.Mode.MULTIPLY);
            editTextMessage.setTextColor(sentTextColor);
            imageViewSent.setVisibility(View.VISIBLE);
            imageViewReceived.setVisibility(View.INVISIBLE);
        }
        else {
            editTextMessage.setBackgroundResource(R.drawable.message_bubble_received);;
            editTextMessage.getBackground().setColorFilter(receivedColor, PorterDuff.Mode.MULTIPLY);
            editTextMessage.setTextColor(receivedTextColor);
            imageViewSent.setVisibility(View.INVISIBLE);
            imageViewReceived.setVisibility(View.VISIBLE);
        }
    }

    public void setSwitchRead(Boolean read) {
        switchRead.setChecked(read);
    }
    public void setSentColor(String color){
        sentColor = Color.parseColor(color);
    }
    public void setSentTextColor(String color){
        sentTextColor = Color.parseColor(color);
    }
    public void setReceivedColor(String color){
        receivedColor = Color.parseColor(color);
    }
    public void setReceivedTextColor(String color){
        receivedTextColor = Color.parseColor(color);
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
