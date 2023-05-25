package com.example.fakechat.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.fakechat.ChatData;
import com.example.fakechat.R;
import com.example.fakechat.messages.MainActivity;
import com.example.fakechat.messages.MessageData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatsActivity extends AppCompatActivity {
    private ArrayList<ChatData> appData;
    private RecyclerView recyclerView;
    private ChatsAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        Bundle extras = getIntent().getExtras();
        appData = extras.getParcelableArrayList("AppData", ChatData.class);

        recyclerView = findViewById(R.id.recyclerViewChats);
        ArrayList<String> chatNames = new ArrayList<>();
        ArrayList<Integer> chatAvatars = new ArrayList<>();
        appData.forEach((ChatData chat) -> {
            chatNames.add(chat.getReceiverName());
            chatAvatars.add(chat.getAvatar());
        });
        recyclerAdapter = new ChatsAdapter(chatNames, this, appData);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void startChat(int receiverIndex){

    }
}