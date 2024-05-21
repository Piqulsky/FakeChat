package com.example.fakechat.settings;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fakechat.ChatData;
import com.example.fakechat.R;
import com.example.fakechat.chats.ChatsActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private String chatsName;
    private String colorHex;
    private String secondaryThemeHex;
    private String bgColorHex;
    private String bgImageUri;
    private String primaryHex;
    private String secondaryHex;
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
        colorHex = extras.getString("ColorHex");
        secondaryThemeHex = extras.getString("SecondaryThemeHex");
        bgColorHex = extras.getString("BackgroundColorHex");
        bgImageUri = extras.getString("BackgroundImageUri");
        primaryHex = extras.getString("PrimaryHex");
        secondaryHex = extras.getString("SecondaryHex");

        View color = findViewById(R.id.colorView);
        color.setBackgroundColor(Color.parseColor(colorHex));

        color = findViewById(R.id.secondaryThemeView);
        color.setBackgroundColor(Color.parseColor(secondaryThemeHex));

        color = findViewById(R.id.primaryView);
        color.setBackgroundColor(Color.parseColor(primaryHex));

        color = findViewById(R.id.secondaryView);
        color.setBackgroundColor(Color.parseColor(secondaryHex));

        color = findViewById(R.id.backgroundView);
        if(bgColorHex.equals(""))
            color.setBackgroundColor(Color.WHITE);
        else
            color.setBackgroundColor(Color.parseColor(bgColorHex));

        LinearLayout linearLayoutHeader = findViewById(R.id.linearLayoutSettingsHeader);
        linearLayoutHeader.setBackgroundColor(Color.parseColor(colorHex));

        recyclerView = findViewById(R.id.recyclerViewSettings);
        recyclerAdapter = new SettingsAdapter(appData, this, colorHex, primaryHex, secondaryThemeHex, primaryHex);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EditText editTextColorTheme = findViewById(R.id.editTextColorTheme);
        editTextColorTheme.setText(colorHex);
        editTextColorTheme.setOnFocusChangeListener((view, b) -> {
            if(!b){
                View colorView = findViewById(R.id.colorView);
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                try{
                    colorView.setBackgroundColor(Color.parseColor(editTextColorTheme.getText().toString()));
                    dataCorrect = true;
                } catch (Exception e){
                    Toast.makeText(this, "Input correct hexadecimal RGB color code with #", Toast.LENGTH_LONG).show();
                    dataCorrect = false;
                }
            }
        });

        EditText editTextSecondaryTheme = findViewById(R.id.editTextSecondaryTheme);
        editTextSecondaryTheme.setText(secondaryThemeHex);
        editTextSecondaryTheme.setOnFocusChangeListener((view, b) -> {
            if(!b){
                View colorView = findViewById(R.id.secondaryThemeView);
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                try{
                    colorView.setBackgroundColor(Color.parseColor(editTextSecondaryTheme.getText().toString()));
                    dataCorrect = true;
                } catch (Exception e){
                    Toast.makeText(this, "Input correct hexadecimal RGB color code with #", Toast.LENGTH_LONG).show();
                    dataCorrect = false;
                }
            }
        });

        EditText editTextBackgroundColor = findViewById(R.id.editTextBackground);
        editTextBackgroundColor.setText(bgColorHex);
        editTextBackgroundColor.setOnFocusChangeListener((view, b) -> {
            if(!b){
                View bgView = findViewById(R.id.backgroundView);
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                try{
                    if(editTextBackgroundColor.getText().toString().equals("")) {
                        bgView.setBackgroundColor(Color.WHITE);
                    }
                    else
                        bgView.setBackgroundColor(Color.parseColor(editTextBackgroundColor.getText().toString()));
                    dataCorrect = true;
                } catch (Exception e){
                    Toast.makeText(this, "Input correct hexadecimal RGB color code with # or nothing", Toast.LENGTH_LONG).show();
                    dataCorrect = false;
                }
            }
        });

        ImageView imageViewBackground = findViewById(R.id.imageViewBackground);
        if(!bgImageUri.equals(""))
            imageViewBackground.setImageURI(Uri.parse(bgImageUri));

        Button selectBgButton = findViewById(R.id.bgButton);
        selectBgButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorHex)));
        selectBgButton.setTextColor(Color.parseColor(primaryHex));
        selectBgButton.setOnClickListener(view -> {
            try {
                getImage.launch("image/*");
            } catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        EditText editTextPrimaryColor = findViewById(R.id.editTextPrimaryColor);
        editTextPrimaryColor.setText(primaryHex);
        editTextPrimaryColor.setOnFocusChangeListener((view, b) -> {
            if(!b){
                View colorView = findViewById(R.id.primaryView);
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                try{
                    colorView.setBackgroundColor(Color.parseColor(editTextPrimaryColor.getText().toString()));
                    dataCorrect = true;
                } catch (Exception e){
                    Toast.makeText(this, "Input correct hexadecimal RGB color code with #", Toast.LENGTH_LONG).show();
                    dataCorrect = false;
                }
            }
        });

        EditText editTextSecondaryColor = findViewById(R.id.editTextSecondaryColor);
        editTextSecondaryColor.setText(secondaryHex);
        editTextSecondaryColor.setOnFocusChangeListener((view, b) -> {
            if(!b){
                View colorView = findViewById(R.id.secondaryView);
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                try{
                    colorView.setBackgroundColor(Color.parseColor(editTextSecondaryColor.getText().toString()));
                    dataCorrect = true;
                } catch (Exception e){
                    Toast.makeText(this, "Input correct hexadecimal RGB color code with #", Toast.LENGTH_LONG).show();
                    dataCorrect = false;
                }
            }
        });

        ImageButton saveButton = findViewById(R.id.imageViewSave);
        EditText editTextChatsName = findViewById(R.id.editTextChatsName);
        editTextChatsName.setText(chatsName);
        editTextChatsName.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        saveButton.setOnClickListener(view -> {
            if(dataCorrect){
                chatsName = editTextChatsName.getText().toString();
                colorHex = editTextColorTheme.getText().toString();
                secondaryThemeHex = editTextSecondaryTheme.getText().toString();
                bgColorHex = editTextBackgroundColor.getText().toString();
                primaryHex = editTextPrimaryColor.getText().toString();
                secondaryHex = editTextSecondaryColor.getText().toString();

                Intent chats =  new Intent(this, ChatsActivity.class);
                chats.putExtra("AppData", appData);
                chats.putExtra("ChatsName", chatsName);
                chats.putExtra("ColorHex", colorHex);
                chats.putExtra("SecondaryThemeHex", secondaryThemeHex);
                chats.putExtra("BackgroundColorHex", bgColorHex);
                chats.putExtra("BackgroundImageUri", bgImageUri);
                chats.putExtra("PrimaryHex", primaryHex);
                chats.putExtra("SecondaryHex", secondaryHex);
                startActivity(chats);
            }
        });

        ImageButton addButton = findViewById(R.id.imageButtonAddChat);
        addButton.setOnClickListener(view -> {
            appData.add(new ChatData());
            recyclerAdapter.notifyItemInserted(appData.size() - 1);
        });


    }

    ActivityResultLauncher<String> getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
        try {
            ImageView imageViewBackground = findViewById(R.id.imageViewBackground);
            imageViewBackground.setImageURI(uri);
            bgImageUri = uri.toString();
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    });
}