package com.example.fakechat.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fakechat.ChatData;
import com.example.fakechat.MessageData;
import com.example.fakechat.chats.ChatsActivity;
import com.example.fakechat.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int receiver;
    private String chatsName;
    private String colorHex;
    private ArrayList<ChatData> appData;
    private RecyclerView recyclerView;
    private MessagesAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        try{
            appData = extras.getParcelableArrayList("AppData", ChatData.class);
            receiver = extras.getInt("ReceiverIndex");
            chatsName = extras.getString("ChatsName");
            colorHex = extras.getString("ColorHex");
        }catch (Exception e){
            appData = new ArrayList<>();
            appData.add(new ChatData("Receiver1", R.drawable.avatar, createDataArray()));
            appData.add(new ChatData("Receiver2", R.drawable.avatar, new ArrayList<>()));
            appData.get(1).setActivity("15m ago");
            appData.add(new ChatData());
            appData.get(2).setRead(false);
            appData.add(new ChatData());
            appData.add(new ChatData());

            receiver = 0;
            chatsName = "Chats";
            colorHex = "#FF9800";
        }
        TextView textViewReceiverName = findViewById(R.id.textViewReceiver);
        textViewReceiverName.setText(appData.get(receiver).getReceiverName());

        TextView textViewActivity = findViewById(R.id.textViewActivity);
        textViewActivity.setText(appData.get(receiver).getActivity());
        if(appData.get(receiver).getActivity().equals("online"))
            textViewActivity.setTextColor(Color.GREEN);
        appData.get(receiver).setRead(true);

        ImageView imageViewAvatar = findViewById(R.id.imageViewReceiver);
        imageViewAvatar.setImageResource(appData.get(receiver).getAvatar());

        LinearLayout linearLayoutHeader = findViewById(R.id.linearLayoutMainHeader);
        linearLayoutHeader.setBackgroundColor(Color.parseColor(colorHex));
        LinearLayout linearLayoutFooter = findViewById(R.id.linearLayoutMainFooter);
        linearLayoutFooter.setBackgroundColor(Color.parseColor(colorHex));

        recyclerView = findViewById(R.id.recyclerViewMessages);
        recyclerAdapter = new MessagesAdapter(appData.get(receiver).getMessagesData(), this, colorHex);
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        ImageButton sendButton = findViewById(R.id.imageViewSend);
        sendButton.setOnClickListener(view -> {
            EditText editTextMessage = findViewById(R.id.editTextMessage);
            if(!editTextMessage.getText().toString().equals(""))
                MainActivity.this.addToRecyclerData(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, editTextMessage.getText().toString(), appData.get(receiver).getAvatar()));
            editTextMessage.setText("");
        });

        ImageButton returnButton = findViewById(R.id.imageViewReturn);
        returnButton.setOnClickListener(view -> {
            Intent chats =  new Intent(this, ChatsActivity.class);
            chats.putExtra("AppData", appData);
            chats.putExtra("ChatsName", chatsName);
            chats.putExtra("ColorHex", colorHex);
            startActivity(chats);
        });
    }

    private ArrayList<MessageData> createDataArray(){
        ArrayList<MessageData> list = new ArrayList<>();

        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "This is message received", R.drawable.avatar));
        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "This is message sent", R.drawable.avatar));
        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "This is message received that is definitely much more longer and should be split", R.drawable.avatar));
        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "This is message sent", R.drawable.avatar));
        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "This is message sent that is definitely much more longer and should be split", R.drawable.avatar));
        list.get(list.size() - 1).setRead(false);

        return list;
    }

    public void addToRecyclerData(MessageData messageData){
        appData.get(receiver).addMessageData(messageData);
        recyclerView.scrollToPosition(recyclerAdapter.getItemCount() - 1);
        recyclerAdapter.notifyDataSetChanged();
    }
}