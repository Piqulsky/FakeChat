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

public class SentMessageHolder extends RecyclerView.ViewHolder {
    private final TextView textViewMessage;
    private final ImageView imageViewRead;

    public SentMessageHolder(@NonNull View itemView) {
        super(itemView);
        textViewMessage = itemView.findViewById(R.id.textViewMessageSent);
        imageViewRead = itemView.findViewById(R.id.imageViewRead);
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
    public void setImageViewRead(String res) {imageViewRead.setImageURI(Uri.parse(res));}
}
