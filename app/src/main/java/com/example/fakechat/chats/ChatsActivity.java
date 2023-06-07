package com.example.fakechat.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fakechat.ChatData;
import com.example.fakechat.R;
import com.example.fakechat.settings.SettingsActivity;

import java.util.ArrayList;

public class ChatsActivity extends AppCompatActivity {
    private String chatsName;
    private String colorHex;
    private ArrayList<ChatData> appData;
    private RecyclerView recyclerView;
    private ChatsAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        Bundle extras = getIntent().getExtras();
        appData = (ArrayList<ChatData>) extras.get("AppData");
        chatsName = extras.getString("ChatsName");
        colorHex = extras.getString("ColorHex");

        LinearLayout linearLayoutHeader = findViewById(R.id.linearLayoutChatsHeader);
        linearLayoutHeader.setBackgroundColor(Color.parseColor(colorHex));

        TextView textViewChats = findViewById(R.id.textViewChats);
        textViewChats.setText(chatsName);

        recyclerView = findViewById(R.id.recyclerViewChats);
        recyclerAdapter = new ChatsAdapter(appData, this, chatsName, colorHex);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton moreButton = findViewById(R.id.imageViewMore);
        moreButton.setOnClickListener(view -> {
            Intent settings =  new Intent(this, SettingsActivity.class);
            settings.putExtra("AppData", appData);
            settings.putExtra("ChatsName", chatsName);
            settings.putExtra("ColorHex", colorHex);
            startActivity(settings);
        });
    }
}