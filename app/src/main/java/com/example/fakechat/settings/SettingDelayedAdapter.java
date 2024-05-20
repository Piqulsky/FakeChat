package com.example.fakechat.settings;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.DelayedData;
import com.example.fakechat.MessageData;
import com.example.fakechat.R;

import java.util.ArrayList;

public class SettingDelayedAdapter extends RecyclerView.Adapter<SettingDelayedHolder> {
    private ArrayList<DelayedData> list;
    private Context context;
    private String receivedHex;
    private String receivedTextHex;
    public SettingDelayedAdapter(ArrayList<DelayedData> list, Context context, String receivedHex, String receivedTextHex){
        this.list = list; this.context = context; this.receivedHex = receivedHex; this.receivedTextHex = receivedTextHex;
    }
    @NonNull
    @Override
    public SettingDelayedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_setting_delayed, parent, false);
        return new SettingDelayedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingDelayedHolder holder, int position) {
        holder.setEditTextDelayedMessage(list.get(position).getMessage());
        holder.setEditTextDelay(Float.toString(list.get(position).getSeconds()));
        holder.setReceivedColor(receivedHex);
        holder.setReceivedTextColor(receivedTextHex);

        holder.getEditTextDelayedMessage().addTextChangedListener(new SettingDelayedAdapter.MessageTextChangedListener(holder));
        holder.getEditTextDelayedMessage().setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        holder.getEditTextDelay().addTextChangedListener(new SettingDelayedAdapter.DelayTextChangedListener(holder));
        holder.getEditTextDelay().setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        holder.getImageButtonDeleteDelayed().setOnClickListener(view -> {
            list.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });
    }

    private class MessageTextChangedListener implements TextWatcher {
        @NonNull SettingDelayedHolder holder;
        public MessageTextChangedListener(@NonNull SettingDelayedHolder holder){
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
            list.get(holder.getAdapterPosition()).setMessage(editable.toString());
        }
    }
    private class DelayTextChangedListener implements TextWatcher {
        @NonNull SettingDelayedHolder holder;
        public DelayTextChangedListener(@NonNull SettingDelayedHolder holder){
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
            list.get(holder.getAdapterPosition()).setDelay(Float.parseFloat(editable.toString()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
