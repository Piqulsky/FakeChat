package com.example.fakechat.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fakechat.R;
import com.example.fakechat.messages.MessagesData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatsActivity extends AppCompatActivity {
    private Map<String, ArrayList<MessagesData>> appData;
    private RecyclerView recyclerView;
    private ChatsAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        Bundle extras = getIntent().getExtras();
        Object[] appDataKeys = (Object[]) extras.get("AppDataKeys");
        Object[] appDataValues = (Object[]) extras.get("AppDataValues");

        appData = new HashMap<>();
        for (int i = 0; i < appDataValues.length; i++) {
            appData.put(String.valueOf(appDataKeys[i]), (ArrayList<MessagesData>) appDataValues[i]);
        }

        recyclerView = findViewById(R.id.recyclerViewChats);
        ArrayList dataKeys = new ArrayList<>();
        dataKeys.addAll(appData.keySet());
        recyclerAdapter = new ChatsAdapter(dataKeys, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}