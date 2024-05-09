package com.example.fakechat.settings;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fakechat.ChatData;
import com.example.fakechat.ColorPalette;
import com.example.fakechat.R;
import com.example.fakechat.chats.ChatsActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private String chatsName;
    private String colorName;
    private ArrayList<ChatData> appData;
    private RecyclerView recyclerView;
    private SettingsAdapter recyclerAdapter;
    private Boolean dataCorrect = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Bundle extras = getIntent().getExtras();
        appData = (ArrayList<ChatData>) extras.get("AppData");
        chatsName = extras.getString("ChatsName");
        colorName = extras.getString("ColorName");

        View color = findViewById(R.id.colorView);
        color.setBackgroundColor(Color.parseColor(ColorPalette.colors.get(colorName)));

        LinearLayout linearLayoutHeader = findViewById(R.id.linearLayoutSettingsHeader);
        linearLayoutHeader.setBackgroundColor(Color.parseColor(ColorPalette.colors.get(colorName)));

        recyclerView = findViewById(R.id.recyclerViewSettings);
        recyclerAdapter = new SettingsAdapter(appData, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Spinner colorSpinner = findViewById(R.id.colorSpinner);
        String[] keys = ColorPalette.colors.keySet().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, keys);
        colorSpinner.setAdapter(adapter);

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                View colorView = findViewById(R.id.colorView);
                String color = ColorPalette.colors.get(adapterView.getItemAtPosition(pos).toString());
                colorView.setBackgroundColor(Color.parseColor(color));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ImageButton saveButton = findViewById(R.id.imageViewSave);
        EditText editTextChatsName = findViewById(R.id.editTextChatsName);
        editTextChatsName.setText(chatsName);
        saveButton.setOnClickListener(view -> {
            if(dataCorrect){
                chatsName = editTextChatsName.getText().toString();

                Spinner spinner = findViewById(R.id.colorSpinner);
                colorName = spinner.getSelectedItem().toString();

                Intent chats =  new Intent(this, ChatsActivity.class);
                chats.putExtra("AppData", appData);
                chats.putExtra("ChatsName", chatsName);
                chats.putExtra("ColorName", colorName);
                startActivity(chats);
            }
        });

        ImageButton addButton = findViewById(R.id.imageButtonAddChat);
        addButton.setOnClickListener(view -> {
            appData.add(new ChatData());
            recyclerAdapter.notifyItemInserted(appData.size() - 1);
        });


    }


}