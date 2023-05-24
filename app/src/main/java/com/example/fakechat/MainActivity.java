package com.example.fakechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MessagesData> recyclerData;
    private RecyclerView recyclerView;
    private MessagesAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerData = createDataArray();
        recyclerAdapter = new MessagesAdapter(recyclerData, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
    }

    private ArrayList<MessagesData> createDataArray(){
        ArrayList<MessagesData> list = new ArrayList<>();

        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_RECEIVED, "This is message received", R.drawable.ic_launcher_foreground));
        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_SENT, "This is message sent"));
        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_RECEIVED, "This is message received that is definetely much more longer and should be splitted", R.drawable.ic_launcher_foreground));
        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_SENT, "This is message sent"));
        list.add(new MessagesData(MessagesData.LAYOUT_MESSAGE_SENT, "This is message sent that is definetely much more longer and should be splitted"));

        return list;
    }
}