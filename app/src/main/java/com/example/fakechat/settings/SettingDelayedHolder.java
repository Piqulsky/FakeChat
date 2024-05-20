package com.example.fakechat.settings;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;

public class SettingDelayedHolder extends RecyclerView.ViewHolder {
    private final EditText editTextDelayedMessage;
    private final EditText editTextDelay;
    private final ImageButton imageButtonDeleteDelayed;

    private int receivedColor;
    private int receivedTextColor;
    public SettingDelayedHolder(@NonNull View itemView) {
        super(itemView);
        editTextDelayedMessage = itemView.findViewById(R.id.editTextSettingDelayedMessage);
        editTextDelay = itemView.findViewById(R.id.editTextDelay);
        imageButtonDeleteDelayed = itemView.findViewById(R.id.imageButtonDeleteDelayed);

        editTextDelayedMessage.setTextColor(receivedTextColor);
        editTextDelayedMessage.setBackgroundResource(R.drawable.message_bubble_received);
        editTextDelayedMessage.getBackground().setColorFilter(receivedColor, PorterDuff.Mode.MULTIPLY);
    }

    public void setEditTextDelayedMessage(String message) {
        editTextDelayedMessage.setText(message);
    }
    public EditText getEditTextDelayedMessage() {
        return editTextDelayedMessage;
    }
    public void setEditTextDelay(String message) {
        editTextDelay.setText(message);
    }
    public EditText getEditTextDelay() {
        return editTextDelay;
    }
    public ImageButton getImageButtonDeleteDelayed(){
        return imageButtonDeleteDelayed;
    }
    public void setReceivedColor(String color){
        receivedColor = Color.parseColor(color);
        editTextDelayedMessage.setBackgroundResource(R.drawable.message_bubble_received);
        editTextDelayedMessage.getBackground().setColorFilter(receivedColor, PorterDuff.Mode.MULTIPLY);
    }
    public void setReceivedTextColor(String color){
        receivedTextColor = Color.parseColor(color);
        editTextDelayedMessage.setTextColor(receivedTextColor);
    }
}
