package com.example.fakechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChatsActivity extends AppCompatActivity {
    private Map<String, ArrayList<MessagesData>> appData;
    private RecyclerView recyclerView;
    private MessagesAdapter recyclerAdapter;
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
    }
}