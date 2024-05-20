package com.example.fakechat.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fakechat.ChatData;
import com.example.fakechat.R;
import com.example.fakechat.settings.SettingsActivity;

import java.util.ArrayList;

public class ChatsActivity extends AppCompatActivity {
    private String chatsName;
    private String colorHex;
    private String secondaryThemeHex;
    private String bgColorHex;
    private String bgImageUri;
    private String primaryHex;
    private String secondaryHex;
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
        secondaryThemeHex = extras.getString("SecondaryThemeHex");
        bgColorHex = extras.getString("BackgroundColorHex");
        bgImageUri = extras.getString("BackgroundImageUri");
        primaryHex = extras.getString("PrimaryHex");
        secondaryHex = extras.getString("SecondaryHex");

        if(bgColorHex.equals("") && !bgImageUri.equals("")){
            ImageView backgroundImage = findViewById(R.id.chatsBackgroundImage);
            backgroundImage.setImageURI(Uri.parse(bgImageUri));
        }
        else if(!bgColorHex.equals("")){
            ConstraintLayout parent = findViewById(R.id.chatsParent);
            parent.setBackgroundColor(Color.parseColor(bgColorHex));
        }

        LinearLayout linearLayoutHeader = findViewById(R.id.linearLayoutChatsHeader);
        linearLayoutHeader.setBackgroundColor(Color.parseColor(colorHex));

        TextView textViewChats = findViewById(R.id.textViewChats);
        textViewChats.setText(chatsName);
        textViewChats.setTextColor(Color.parseColor(primaryHex));

        recyclerView = findViewById(R.id.recyclerViewChats);
        recyclerAdapter = new ChatsAdapter(appData, this, chatsName, colorHex, secondaryThemeHex, bgColorHex, bgImageUri, primaryHex, secondaryHex);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton moreButton = findViewById(R.id.imageViewMore);
        moreButton.setOnClickListener(view -> {
            Intent settings =  new Intent(this, SettingsActivity.class);
            settings.putExtra("AppData", appData);
            settings.putExtra("ChatsName", chatsName);
            settings.putExtra("ColorHex", colorHex);
            settings.putExtra("SecondaryThemeHex", secondaryThemeHex);
            settings.putExtra("BackgroundColorHex", bgColorHex);
            settings.putExtra("BackgroundImageUri", bgImageUri);
            settings.putExtra("PrimaryHex", primaryHex);
            settings.putExtra("SecondaryHex", secondaryHex);
            startActivity(settings);
        });

    }
}