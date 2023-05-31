package com.example.fakechat.chats;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;

public class ChatHolder extends RecyclerView.ViewHolder {
    private TextView textViewReceiver;
    private ImageView imageViewAvatar;
    private ImageButton imageViewButton;
    public ChatHolder(@NonNull View itemView) {
        super(itemView);
        textViewReceiver = itemView.findViewById(R.id.textViewChatReceiver);
        imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
        imageViewButton = itemView.findViewById(R.id.imageViewButton);
    }

    public void setReceiverName(String name){textViewReceiver.setText(name);}
    public TextView getTextViewReceiver() {
        return textViewReceiver;
    }
    public void setAvatarResource(int res){imageViewAvatar.setImageResource(res);}
    public ImageButton getImageButton() {
        return imageViewButton;
    }
}
