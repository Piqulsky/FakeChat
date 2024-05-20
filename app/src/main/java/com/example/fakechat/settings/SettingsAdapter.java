package com.example.fakechat.settings;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.ChatData;
import com.example.fakechat.DelayedData;
import com.example.fakechat.MessageData;
import com.example.fakechat.R;

import java.util.ArrayList;

public class SettingsAdapter extends RecyclerView.Adapter<SettingHolder>{
    private ArrayList<ChatData> appData;
    private Context context;
    private ArrayList<SettingHolder> settingHolders;
    private String sentHex;
    private String sentTextHex;
    private String receivedHex;
    private String receivedTextHex;
    public SettingsAdapter(ArrayList<ChatData> appData, Context context, String sentHex, String sentTextHex, String receivedHex, String receivedTextHex){
        this.appData = appData; this.context = context; settingHolders = new ArrayList<>(); this.sentHex = sentHex; this.sentTextHex = sentTextHex; this.receivedHex = receivedHex; this.receivedTextHex = receivedTextHex;
    }
    @NonNull
    @Override
    public SettingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_setting, parent, false);
        SettingHolder holder = new SettingHolder(view);
        settingHolders.add(holder);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull SettingHolder holder, int position) {
        ChatData chatData = appData.get(position);
        holder.setReceiverName(chatData.getReceiverName());
        holder.setAvatarResource(chatData.getAvatar());
        holder.setEditTextActivity(chatData.getActivity());
        holder.getSwitchIsRead().setChecked(chatData.getRead());
        holder.setSentHex(sentHex);
        holder.setSentTextHex(sentTextHex);
        holder.setReceivedHex(receivedHex);
        holder.setReceivedTextHex(receivedTextHex);
        holder.setRecyclerViewMessages(chatData.getMessagesData());
        holder.setRecyclerViewDelayed(chatData.getDelayedData());
        holder.getSwitchIsRead().setOnCheckedChangeListener((compoundButton, b) -> {
            appData.get(holder.getAdapterPosition()).setRead(b);
        });
        holder.getEditTextReceiver().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                appData.get(holder.getAdapterPosition()).setReceiverName(editable.toString());
            }
        });
        holder.getEditTextReceiver().setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        holder.getEditTextActivity().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                appData.get(holder.getAdapterPosition()).setActivity(editable.toString());
            }
        });
        holder.getEditTextActivity().setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        holder.getImageButtonDelete().setOnClickListener(view -> {
            appData.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });
        holder.getImageButtonAdd().setOnClickListener(view -> {
            appData.get(holder.getAdapterPosition()).getMessagesData().add(new MessageData());
            notifyItemChanged(holder.getAdapterPosition());
        });
        holder.getImageButtonAddDelayed().setOnClickListener(view -> {
            ArrayList<DelayedData> tmp = appData.get(holder.getAdapterPosition()).getDelayedData();
            appData.get(holder.getAdapterPosition()).getDelayedData().add(new DelayedData());
            notifyItemChanged(holder.getAdapterPosition());
        });
    }
    @Override
    public int getItemCount() {
        return appData.size();
    }
}
