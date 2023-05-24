package com.example.fakechat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SentMessageHolder extends RecyclerView.ViewHolder {
    private final TextView textViewMessage;

    public SentMessageHolder(@NonNull View itemView) {
        super(itemView);
        textViewMessage = itemView.findViewById(R.id.textViewMessageSent);
    }

    public void setMessage(String message) {
        textViewMessage.setText(message);
    }
}
