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
import com.example.fakechat.R;
import com.example.fakechat.MessageData;

import java.util.ArrayList;

public class SettingMessagesAdapter extends RecyclerView.Adapter<SettingMessageHolder> {
    private ArrayList<ChatData> appData;
    private int position;
    private Context context;
    private String colorHex;
    public SettingMessagesAdapter(ArrayList<ChatData> appData, int position, Context context, String colorHex){
        this.appData = appData; this.position = position; this.context = context; this.colorHex = colorHex;
    }
    @NonNull
    @Override
    public SettingMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_setting_message, parent, false);
        return new SettingMessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingMessageHolder holder, int position) {
        ArrayList<MessageData> list = appData.get(this.position).getMessagesData();
        holder.setEditTextMessage(list.get(position).getItemMessage());
        holder.setSwitchRead(list.get(position).getRead());
        holder.setSentColor(colorHex);
        if(list.get(position).getItemViewType() == MessageData.LAYOUT_MESSAGE_SENT)
            holder.setSwitchWhose(true);
        else if(list.get(position).getItemViewType() == MessageData.LAYOUT_MESSAGE_RECEIVED)
            holder.setSwitchWhose(false);

        holder.getEditTextMessage().addTextChangedListener(new MessageTextChangedListener(holder, this.position));
        holder.getEditTextMessage().setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        holder.getSwitchRead().setOnCheckedChangeListener(new ReadOnCheckedChangeListener(holder, this.position));
        holder.getSwitchWhose().setOnCheckedChangeListener(new WhoseOnCheckedChangeListener(holder, this.position));
        holder.getImageButtonDeleteMessage().setOnClickListener(view -> {
            appData.get(this.position).getMessagesData().remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });
    }
    private class MessageTextChangedListener implements TextWatcher{
        @NonNull SettingMessageHolder holder;
        int chatPosition;
        public MessageTextChangedListener(@NonNull SettingMessageHolder holder, int chatPosition){
            this.holder = holder; this.chatPosition = chatPosition;
        }
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            appData.get(chatPosition).getMessagesData().get(holder.getAdapterPosition()).setItemMessage(editable.toString());
        }
    }
    private class ReadOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener{
        @NonNull SettingMessageHolder holder;
        int chatPosition;
        public ReadOnCheckedChangeListener(@NonNull SettingMessageHolder holder, int chatPosition){
            this.holder = holder; this.chatPosition = chatPosition;
        }
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            appData.get(chatPosition).getMessagesData().get(holder.getAdapterPosition()).setRead(b);
        }
    }
    private class WhoseOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener{
        @NonNull SettingMessageHolder holder;
        int chatPosition;
        public WhoseOnCheckedChangeListener(@NonNull SettingMessageHolder holder, int chatPosition){
            this.holder = holder; this.chatPosition = chatPosition;
        }
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            holder.setSwitchWhose(b);
            appData.get(chatPosition).getMessagesData().get(holder.getAdapterPosition()).setItemViewType(b ? MessageData.LAYOUT_MESSAGE_SENT : MessageData.LAYOUT_MESSAGE_RECEIVED);
        }
    }
    @Override
    public int getItemCount() {
        return appData.get(this.position).getMessagesData().size();
    }

}
