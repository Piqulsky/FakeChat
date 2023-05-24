package com.example.fakechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String receiver;
    private Map<String, ArrayList<MessagesData>> appData;
    private RecyclerView recyclerView;
    private MessagesAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver = "Receiver";

        appData = new HashMap<>();
        appData.put("Receiver", createDataArray());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new MessagesAdapter(appData.get(receiver), this);
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        ImageView sendButton = findViewById(R.id.imageViewSend);
        sendButton.setOnClickListener(view -> {
            EditText editTextMessage = findViewById(R.id.editTextMessage);
            if(!editTextMessage.getText().toString().equals(""))
                MainActivity.this.addToRecyclerData(new MessagesData(MessagesData.LAYOUT_MESSAGE_SENT, editTextMessage.getText().toString()));
            editTextMessage.setText("");
        });
    }

    private ArrayList<MessagesData> createDataArray(){
        ArrayList<MessagesData> list = new ArrayList<>();

        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_RECEIVED, "This is message received", R.drawable.ic_launcher_foreground));
        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_SENT, "This is message sent"));
        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_RECEIVED, "This is message received that is definitely much more longer and should be split", R.drawable.ic_launcher_foreground));
        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_SENT, "This is message sent"));
        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_SENT, "This is message sent that is definitely much more longer and should be split"));

        return list;
    }

    public void addToRecyclerData(MessagesData messagesData){
        appData.get(receiver).add(messagesData);
        recyclerView.scrollToPosition(recyclerAdapter.getItemCount() - 1);
        recyclerAdapter.notifyDataSetChanged();
    }
}