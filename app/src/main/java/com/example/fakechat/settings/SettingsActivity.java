package com.example.fakechat.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.fakechat.ChatData;
import com.example.fakechat.R;
import com.example.fakechat.chats.ChatsActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private ArrayList<ChatData> appData;
    private RecyclerView recyclerView;
    private SettingsAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Bundle extras = getIntent().getExtras();
        appData = extras.getParcelableArrayList("AppData", ChatData.class);

        recyclerView = findViewById(R.id.recyclerViewSettings);
        recyclerAdapter = new SettingsAdapter(appData, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton saveButton = findViewById(R.id.imageViewSave);
        saveButton.setOnClickListener(view -> {
            Intent settings =  new Intent(this, ChatsActivity.class);
            settings.putExtra("AppData", appData);
            startActivity(settings);
        });

        ImageButton cancelButton = findViewById(R.id.imageViewCancel);
        cancelButton.setOnClickListener(view -> {
            Intent settings =  new Intent(this, ChatsActivity.class);
            settings.putExtra("AppData", appData);
            startActivity(settings);
        });
    }
}