package com.example.fakechat.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fakechat.ChatData;
import com.example.fakechat.MessageData;
import com.example.fakechat.R;
import com.example.fakechat.chats.ChatsActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private String chatsName;
    private String colorHex;
    private ArrayList<ChatData> appData;
    private RecyclerView recyclerView;
    private SettingsAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Bundle extras = getIntent().getExtras();
        appData = extras.getParcelableArrayList("AppData", ChatData.class);
        chatsName = extras.getString("ChatsName");
        colorHex = extras.getString("ColorHex");

        LinearLayout linearLayoutHeader = findViewById(R.id.linearLayoutSettingsHeader);
        linearLayoutHeader.setBackgroundColor(Color.parseColor(colorHex));

        recyclerView = findViewById(R.id.recyclerViewSettings);
        recyclerAdapter = new SettingsAdapter(appData, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EditText editTextColorTheme = findViewById(R.id.editTextColorTheme);
        editTextColorTheme.setText(colorHex);
        editTextColorTheme.setOnFocusChangeListener((view, b) -> {
            if(!b){
                View colorView = findViewById(R.id.colorView);
                colorView.setBackgroundColor(Color.parseColor(editTextColorTheme.getText().toString()));
            }
        });

        ImageButton saveButton = findViewById(R.id.imageViewSave);
        EditText editTextChatsName = findViewById(R.id.editTextChatsName);
        editTextChatsName.setText(chatsName);
        saveButton.setOnClickListener(view -> {
            chatsName = editTextChatsName.getText().toString();
            colorHex = editTextColorTheme.getText().toString();

            Intent chats =  new Intent(this, ChatsActivity.class);
            chats.putExtra("AppData", appData);
            chats.putExtra("ChatsName", chatsName);
            chats.putExtra("ColorHex", colorHex);
            startActivity(chats);
        });

        ImageButton addButton = findViewById(R.id.imageButtonAddChat);
        addButton.setOnClickListener(view -> {
            appData.add(new ChatData());
            recyclerAdapter.notifyItemInserted(appData.size() - 1);
        });
    }
}