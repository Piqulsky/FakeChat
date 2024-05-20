package com.example.fakechat.chats;

import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.R;

public class ChatHolder extends RecyclerView.ViewHolder {
    private final TextView textViewReceiver;
    private final ImageView imageViewAvatar;
    private final ImageButton imageViewButton;
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
    public void setTextViewReceiverColor(String color){textViewReceiver.setTextColor(Color.parseColor(color));}
    public void setAvatarResource(Uri res){imageViewAvatar.setImageURI(res);}
    public ImageButton getImageButton() {
        return imageViewButton;
    }
}
