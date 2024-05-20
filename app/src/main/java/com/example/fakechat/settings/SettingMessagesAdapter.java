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
    private ArrayList<MessageData> list;
    private Context context;
    private String sentHex;
    private String sentTextHex;
    private String receivedHex;
    private String receivedTextHex;
    public SettingMessagesAdapter(ArrayList<MessageData> list, Context context, String sentHex, String sentTextHex, String receivedHex, String receivedTextHex){
        this.list = list; this.context = context; this.sentHex = sentHex; this.sentTextHex = sentTextHex; this.receivedHex = receivedHex; this.receivedTextHex = receivedTextHex;
    }
    @NonNull
    @Override
    public SettingMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_setting_message, parent, false);
        return new SettingMessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingMessageHolder holder, int position) {
        holder.setEditTextMessage(list.get(position).getItemMessage());
        holder.setSwitchRead(list.get(position).getRead());
        holder.setSentColor(sentHex);
        holder.setSentTextColor(sentTextHex);
        holder.setReceivedColor(receivedHex);
        holder.setReceivedTextColor(receivedTextHex);
        if(list.get(position).getItemViewType() == MessageData.LAYOUT_MESSAGE_SENT)
            holder.setSwitchWhose(true);
        else if(list.get(position).getItemViewType() == MessageData.LAYOUT_MESSAGE_RECEIVED)
            holder.setSwitchWhose(false);

        holder.getEditTextMessage().addTextChangedListener(new MessageTextChangedListener(holder));
        holder.getEditTextMessage().setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        holder.getSwitchRead().setOnCheckedChangeListener(new ReadOnCheckedChangeListener(holder));
        holder.getSwitchWhose().setOnCheckedChangeListener(new WhoseOnCheckedChangeListener(holder));
        holder.getImageButtonDeleteMessage().setOnClickListener(view -> {
            list.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });
    }
    private class MessageTextChangedListener implements TextWatcher{
        @NonNull SettingMessageHolder holder;
        public MessageTextChangedListener(@NonNull SettingMessageHolder holder){
            this.holder = holder;
        }
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void afterTextChanged(Editable editable) {
            list.get(holder.getAdapterPosition()).setItemMessage(editable.toString());
        }
    }
    private class ReadOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener{
        @NonNull SettingMessageHolder holder;
        public ReadOnCheckedChangeListener(@NonNull SettingMessageHolder holder){
            this.holder = holder;
        }
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            list.get(holder.getAdapterPosition()).setRead(b);
        }
    }
    private class WhoseOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener{
        @NonNull SettingMessageHolder holder;
        public WhoseOnCheckedChangeListener(@NonNull SettingMessageHolder holder){
            this.holder = holder;
        }
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            holder.setSwitchWhose(b);
            list.get(holder.getAdapterPosition()).setItemViewType(b ? MessageData.LAYOUT_MESSAGE_SENT : MessageData.LAYOUT_MESSAGE_RECEIVED);
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
