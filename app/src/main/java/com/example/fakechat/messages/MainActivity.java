package com.example.fakechat.messages;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fakechat.CallActivity;
import com.example.fakechat.ChatData;
import com.example.fakechat.DelayedData;
import com.example.fakechat.MessageData;
import com.example.fakechat.chats.ChatsActivity;
import com.example.fakechat.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int receiver;
    private String chatsName;
    private String colorHex;
    private String secondaryThemeHex;
    private String bgColorHex;
    private String bgImageUri;
    private String primaryHex;
    private String secondaryHex;
    private ArrayList<ChatData> appData;
    private RecyclerView recyclerView;
    private MessagesAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        try{
            appData = (ArrayList<ChatData>) extras.get("AppData");
            receiver = extras.getInt("ReceiverIndex");
            chatsName = extras.getString("ChatsName");
            colorHex = extras.getString("ColorHex");
            secondaryThemeHex = extras.getString("SecondaryThemeHex");
            bgColorHex = extras.getString("BackgroundColorHex");
            bgImageUri = extras.getString("BackgroundImageUri");
            primaryHex = extras.getString("PrimaryHex");
            secondaryHex = extras.getString("SecondaryHex");
        }catch (Exception e){
            appData = new ArrayList<>();
            appData.add(new ChatData("Sergiusz", getUriToDrawable(this, R.drawable.avatar12).toString(), new ArrayList<>()));
            appData.add(new ChatData("Joasia", getUriToDrawable(this, R.drawable.avatar1).toString(), createDataArray(2)));
            appData.add(new ChatData("Tomek", getUriToDrawable(this, R.drawable.avatar3).toString(), createDataArray(1)));
            appData.get(2).setActivity("15m ago");
            appData.add(new ChatData("Adrian", getUriToDrawable(this, R.drawable.avatar2).toString(), createDataArray(3)));
            appData.get(3).setActivity("20m ago");
            appData.get(3).setRead(false);
            appData.add(new ChatData("Anna", getUriToDrawable(this, R.drawable.avatar5).toString(), createDataArray(4)));
            appData.get(4).setRead(false);
            appData.add(new ChatData("Igor", getUriToDrawable(this, R.drawable.avatar11).toString(), createDataArray(0)));
            appData.get(5).setRead(false);
            appData.add(new ChatData("Kaś", getUriToDrawable(this, R.drawable.avatar9).toString(), new ArrayList<>()));
            appData.add(new ChatData("Bernard", getUriToDrawable(this, R.drawable.avatar10).toString(), new ArrayList<>()));
            appData.add(new ChatData());

            receiver = 0;
            chatsName = "Chats";
            colorHex = "#168AFF";
            secondaryThemeHex = "#434343";
            bgColorHex = "";
            bgImageUri = "";
            primaryHex = "#FFFFFF";
            secondaryHex = "#000000";
        }

        if(bgColorHex.equals("") && !bgImageUri.equals("")){
            ImageView backgroundImage = findViewById(R.id.mainBackgroundImage);
            backgroundImage.setImageURI(Uri.parse(bgImageUri));
        }
        else if(!bgColorHex.equals("")){
            ConstraintLayout parent = findViewById(R.id.mainParent);
            parent.setBackgroundColor(Color.parseColor(bgColorHex));
        }

        TextView textViewReceiverName = findViewById(R.id.textViewReceiver);
        textViewReceiverName.setText(appData.get(receiver).getReceiverName());
        textViewReceiverName.setTextColor(Color.parseColor(primaryHex));

        TextView textViewActivity = findViewById(R.id.textViewActivity);
        textViewActivity.setText(appData.get(receiver).getActivity());
        View activityDot = findViewById(R.id.activityDot);
        if(appData.get(receiver).getActivity().equals("online")){
            activityDot.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            textViewActivity.setTextColor(Color.GREEN);
        }
        else{
            activityDot.getBackground().setColorFilter(Color.parseColor(primaryHex), PorterDuff.Mode.MULTIPLY);
            textViewActivity.setTextColor(Color.parseColor(primaryHex));
        }
        appData.get(receiver).setRead(true);

        ImageView imageViewAvatar = findViewById(R.id.imageViewReceiver);
        imageViewAvatar.setImageURI(Uri.parse(appData.get(receiver).getAvatar()));

        LinearLayout linearLayoutHeader = findViewById(R.id.linearLayoutMainHeader);
        linearLayoutHeader.setBackgroundColor(Color.parseColor(colorHex));
        LinearLayout linearLayoutFooter = findViewById(R.id.linearLayoutMainFooter);
        linearLayoutFooter.setBackgroundColor(Color.parseColor(colorHex));

        recyclerView = findViewById(R.id.recyclerViewMessages);
        recyclerAdapter = new MessagesAdapter(appData, this, colorHex, primaryHex, secondaryThemeHex, primaryHex, receiver);
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.scrollToPosition(recyclerAdapter.getItemCount() - 1);

        appData.get(receiver).getDelayedData().forEach(delayedData -> {
            recyclerView.postDelayed(() -> {
                appData.get(receiver).readAllMessages();
                addToRecyclerData(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, delayedData.getMessage()));
            }, Math.round(delayedData.getSeconds()*1000+1000));
        });
        appData.get(receiver).getDelayedData().clear();

        ImageButton sendButton = findViewById(R.id.imageViewSend);
        sendButton.setColorFilter(Color.parseColor(primaryHex), PorterDuff.Mode.MULTIPLY);
        sendButton.setOnClickListener(view -> {
            EditText editTextMessage = findViewById(R.id.editTextMessage);
            if(!editTextMessage.getText().toString().equals(""))
                MainActivity.this.addToRecyclerData(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, editTextMessage.getText().toString()));
            editTextMessage.setText("");
        });

        ImageButton returnButton = findViewById(R.id.imageViewReturn);
        returnButton.setColorFilter(Color.parseColor(primaryHex), PorterDuff.Mode.MULTIPLY);
        returnButton.setOnClickListener(view -> {
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
        });

        ImageButton callButton = findViewById(R.id.imageViewCall);
        callButton.setColorFilter(Color.parseColor(primaryHex), PorterDuff.Mode.MULTIPLY);
        callButton.setOnClickListener(view -> {
            Intent call =  new Intent(this, CallActivity.class);
            call.putExtra("AppData", appData);
            call.putExtra("ChatsName", chatsName);
            call.putExtra("ColorHex", colorHex);
            call.putExtra("SecondaryThemeHex", secondaryThemeHex);
            call.putExtra("BackgroundColorHex", bgColorHex);
            call.putExtra("BackgroundImageUri", bgImageUri);
            call.putExtra("ReceiverIndex", receiver);
            call.putExtra("PrimaryHex", primaryHex);
            call.putExtra("SecondaryHex", secondaryHex);
            startActivity(call);
        });

        imageViewAvatar.setOnClickListener(view -> {
            try {
                getImage.launch("image/*");
            } catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        EditText editTextMessage = findViewById(R.id.editTextMessage);
        /**
         * From stack user vida - issue: https://stackoverflow.com/questions/4165414/how-to-hide-soft-keyboard-on-android-after-clicking-outside-edittext
         * hide keyboard when something other is touched
         */
        editTextMessage.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }

    private ArrayList<MessageData> createDataArray(int par){
        ArrayList<MessageData> list = new ArrayList<>();

        switch (par){
            case 0:
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "This is message received"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "This is message sent"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "This is message received that is definitely much more longer and should be split"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "This is message sent"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "This is message sent that is definitely much more longer and should be split"));
                list.get(0).setRead(true);
                list.get(1).setRead(true);
                list.get(2).setRead(true);
                list.get(3).setRead(true);
                break;
            case 1:
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Kto ma przewagę? Pan nad światem czy świat nad Panem?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Hm?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "W sensie wiesz"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Nie wiem Tomek"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Em. Jakby mając wszystko co mamy"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Szefów, ich firmy, szefów tych szefów, a także ustroje, którymi rządzą szefowie wszystkich szefów"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Czy oni rzeczywiście mają nad tym konrolę? Czy bardziej świat i jego warunki określają obecny stan rzeczy?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Ale cię wzięło na rozmyślenia"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Każdego nachodzą. Ja nie jestem wyjątkiem"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Coż. Ja myślę, że"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Zresztą ty wiesz, co ja uważam"));
                list.forEach(messageData -> messageData.setRead(true));
                list.get(9).setRead(false);
                list.get(10).setRead(false);
                break;
            case 2:
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Tomek"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Tomek!"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Tomek!!!"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Tak?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Już jestem. Słucham cię"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Dobrze. Czekasz na kogoś?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Tak"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Na kogo?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Na kogoś"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Wiesz, że Sammy nie będzie zadowolony taką odpowiedzią"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Sammy już i tak nie jest zadowolony"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Ale to nieważne"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Czekam na Andy'ego"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "To znowu ten?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "I co z nim"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Musimy porozmawiać sobie o biznesie"));
                list.forEach(messageData -> messageData.setRead(true));
                break;
            case 3:
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Tomek!"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Myślałem, że nie wrócisz"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Tak samo myślałem o tobie, bracie"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Jak?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Myślę"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Że po prostu miałem szczęście"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Wiesz, naprawdę dobrze od ciebie usłyszeć"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "A więc biznes"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Tsa"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Ta gorsza część"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Ale"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Zacznijmy od małej dygresji"));
                list.forEach(messageData -> messageData.setRead(true));
                break;
            case 4:
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Nic się nie stało?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Znowu jakiś koszmar?"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Ta"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Mam nadzieję, że tylko koszmar"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Wiesz że jestem obok w razie w"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Muszę się zbierać"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Do pracy"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_SENT, "Dzięki"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Weź. Przestań dziękować"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Na szybko jeszcze"));
                list.add(new MessageData(MessageData.LAYOUT_MESSAGE_RECEIVED, "Co tam na spotkaniu?"));
                list.forEach(messageData -> messageData.setRead(true));
                break;
        }

        return list;
    }

    public void addToRecyclerData(MessageData messageData){
        appData.get(receiver).addMessageData(messageData);
        recyclerView.scrollToPosition(recyclerAdapter.getItemCount() - 1);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
    ActivityResultLauncher<String> getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
        try {
            ((ImageView) findViewById(R.id.imageViewReceiver)).setImageURI(uri);
            appData.get(receiver).setAvatar(uri.toString());
            recyclerAdapter.notifyDataSetChanged();
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    });
    /**
     * From stack user ceph3us - issue: https://stackoverflow.com/questions/6602417/get-the-uri-of-an-image-stored-in-drawable
     * get uri to drawable or any other resource type if u wish
     * @param context - context
     * @param drawableId - drawable res id
     * @return - uri
     */
    public static Uri getUriToDrawable(@NonNull Context context, @AnyRes int drawableId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );
    }
}

/*
Film Plan:
- Message (Znowu)
- Call
- Editor
- Add messages (>Hej, jak leci <Okej, a u ciebie?)
- Add delayed messages(>Nawet nawet <A praca? >Spoko)
- Show the changed chat
- Back to editor
- Change chats name (Czaty)
- Change colors to
* #1f2326
* #2d3035
* #fcffff
* #fcffff
* #373a41

 */