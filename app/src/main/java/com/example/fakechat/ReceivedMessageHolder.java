package com.example.fakechat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    private final TextView textViewMessage;
    private final ImageView imageViewSender;

    public ReceivedMessageHolder(@NonNull View itemView) {
        super(itemView);
        textViewMessage = itemView.findViewById(R.id.textViewMessageReceived);
        imageViewSender = itemView.findViewById(R.id.imageViewSender);
    }

    public void setMessage(String message) {
        textViewMessage.setText(message);
    }
    public void setImage(int imageResource) {
        imageViewSender.setImageResource(imageResource);
    }
}
