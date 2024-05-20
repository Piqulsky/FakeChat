package com.example.fakechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fakechat.chats.ChatsActivity;
import com.example.fakechat.messages.MainActivity;

import java.util.ArrayList;

public class CallActivity extends AppCompatActivity {
    private int receiver;
    private String chatsName;
    private String colorHex;
    private String secondaryThemeHex;
    private String bgColorHex;
    private String bgImageUri;
    private String primaryHex;
    private String secondaryHex;
    private ArrayList<ChatData> appData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Bundle extras = getIntent().getExtras();
        appData = (ArrayList<ChatData>) extras.get("AppData");
        receiver = extras.getInt("ReceiverIndex");
        chatsName = extras.getString("ChatsName");
        colorHex = extras.getString("ColorHex");
        secondaryThemeHex = extras.getString("SecondaryThemeHex");
        bgColorHex = extras.getString("BackgroundColorHex");
        bgImageUri = extras.getString("BackgroundImageUri");
        primaryHex = extras.getString("PrimaryHex");
        secondaryHex = extras.getString("SecondaryHex");

        TextView textViewReceiverName = findViewById(R.id.textViewCallName);
        textViewReceiverName.setText(appData.get(receiver).getReceiverName());

        ImageView imageViewAvatar = findViewById(R.id.imageViewCallAvatar);
        imageViewAvatar.setImageURI(Uri.parse(appData.get(receiver).getAvatar()));

        ImageButton endButton = findViewById(R.id.imageButtonEnd);
        endButton.setOnClickListener(view -> {
            Intent chat =  new Intent(this, MainActivity.class);
            chat.putExtra("AppData", appData);
            chat.putExtra("ChatsName", chatsName);
            chat.putExtra("ColorHex", colorHex);
            chat.putExtra("SecondaryThemeHex", secondaryThemeHex);
            chat.putExtra("BackgroundColorHex", bgColorHex);
            chat.putExtra("BackgroundImageUri", bgImageUri);
            chat.putExtra("ReceiverIndex", receiver);
            chat.putExtra("PrimaryHex", primaryHex);
            chat.putExtra("SecondaryHex", secondaryHex);
            startActivity(chat);
        });
    }
}