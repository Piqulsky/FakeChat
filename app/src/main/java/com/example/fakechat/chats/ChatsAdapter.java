package com.example.fakechat.chats;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.ChatData;
import com.example.fakechat.R;
import com.example.fakechat.messages.MainActivity;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatHolder> {
    private ArrayList<ChatData> appData;
    private String chatsName;
    private String colorHex;
    private String secondaryThemeHex;
    private String bgColorHex;
    private String bgImageUri;
    private String primaryHex;
    private String secondaryHex;
    private Context context;
    public ChatsAdapter(ArrayList<ChatData> appData,Context context, String chatsName, String colorHex, String secondaryThemeHex, String bgColorHex, String bgImageUri, String primaryHex, String secondaryHex){
        this.appData = appData; this.context = context; this.chatsName = chatsName; this.colorHex = colorHex; this.secondaryThemeHex = secondaryThemeHex; this.bgColorHex = bgColorHex; this.bgImageUri = bgImageUri; this.primaryHex = primaryHex; this.secondaryHex = secondaryHex;
    }
    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_chat, parent, false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        holder.setReceiverName(appData.get(position).getReceiverName());
        holder.setAvatarResource(Uri.parse(appData.get(position).getAvatar()));
        holder.setTextViewReceiverColor(secondaryHex);
        if(!appData.get(position).getRead())
            holder.getTextViewReceiver().setTypeface(null, Typeface.BOLD);
        holder.getImageButton().setOnClickListener(view -> {
            Intent chat =  new Intent(context, MainActivity.class);
            chat.putExtra("AppData", appData);
            chat.putExtra("ReceiverIndex", position);
            chat.putExtra("ChatsName", chatsName);
            chat.putExtra("ColorHex", colorHex);
            chat.putExtra("SecondaryThemeHex", secondaryThemeHex);
            chat.putExtra("BackgroundColorHex", bgColorHex);
            chat.putExtra("BackgroundImageUri", bgImageUri);
            chat.putExtra("PrimaryHex", primaryHex);
            chat.putExtra("SecondaryHex", secondaryHex);
            context.startActivity(chat);
        });
    }

    @Override
    public int getItemCount() {
        return appData.size();
    }
}
