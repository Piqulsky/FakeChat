package com.example.fakechat.messages;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;

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

    public void setColor(String color) {
        textViewMessage.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.MULTIPLY);
    }
    public void setTextColor(String color) {
        textViewMessage.setTextColor(Color.parseColor(color));
    }
    public void setImage(String imageResource) {
        imageViewSender.setImageURI(Uri.parse(imageResource));
    }
}
