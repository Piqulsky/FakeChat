package com.example.fakechat.messages;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fakechat.CallActivity;
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
            appData.add(new ChatData("Stefan", getUriToDrawable(this, R.drawable.avatar).toString(), createDataArray()));
            appData.add(new ChatData("Felix", getUriToDrawable(this, R.drawable.niles).toString(), new ArrayList<>()));
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
        imageViewAvatar.setImageURI(Uri.parse(appData.get(receiver).getAvatar()));

        LinearLayout linearLayoutHeader = findViewById(R.id.linearLayoutMainHeader);
        linearLayoutHeader.setBackgroundColor(Color.parseColor(colorHex));
        LinearLayout linearLayoutFooter = findViewById(R.id.linearLayoutMainFooter);
        linearLayoutFooter.setBackgroundColor(Color.parseColor(colorHex));

        recyclerView = findViewById(R.id.recyclerViewMessages);
        recyclerAdapter = new MessagesAdapter(appData, this, colorHex, receiver);
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        ImageButton sendButton = findViewById(R.id.imageViewSend);
        sendButton.setOnClickListener(view -> {
            EditText editTextMessage = findViewById(R.id.editTextMessage);
            if(!editTextMessage.getText().toString().equals(""))
                MainActivity.this.addToRecyclerData(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, editTextMessage.getText().toString()));
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

        ImageButton callButton = findViewById(R.id.imageViewCall);
        callButton.setOnClickListener(view -> {
            Intent call =  new Intent(this, CallActivity.class);
            call.putExtra("AppData", appData);
            call.putExtra("ChatsName", chatsName);
            call.putExtra("ColorHex", colorHex);
            call.putExtra("ReceiverIndex", receiver);
            startActivity(call);
        });

        imageViewAvatar.setOnClickListener(view -> {
            getImage.launch("image/*");
        });
    }

    private ArrayList<MessageData> createDataArray(){
        ArrayList<MessageData> list = new ArrayList<>();

        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "This is message received"));
        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "This is message sent"));
        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "This is message received that is definitely much more longer and should be split"));
        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "This is message sent"));
        list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "This is message sent that is definitely much more longer and should be split"));
        list.get(0).setRead(true);
        list.get(1).setRead(true);
        list.get(2).setRead(true);
        list.get(3).setRead(true);

        return list;
    }

    public void addToRecyclerData(MessageData messageData){
        appData.get(receiver).addMessageData(messageData);
        recyclerView.scrollToPosition(recyclerAdapter.getItemCount() - 1);
    }
    ActivityResultLauncher<String> getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
        ((ImageView) findViewById(R.id.imageViewReceiver)).setImageURI(uri);
        appData.get(receiver).setAvatar(uri.toString());
        recyclerAdapter.notifyDataSetChanged();
    });
    /**
     * From stack user ceph3us - issue: https://stackoverflow.com/questions/6602417/get-the-uri-of-an-image-stored-in-drawable
     * get uri to drawable or any other resource type if u wish
     * @param context - context
     * @param drawableId - drawable res id
     * @return - uri
     */
    public static final Uri getUriToDrawable(@NonNull Context context, @AnyRes int drawableId) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );
        return imageUri;
    }
}