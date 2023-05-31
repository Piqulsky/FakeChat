package com.example.fakechat.messages;

import static com.example.fakechat.MessageData.LAYOUT_MESSAGE_RECEIVED;
import static com.example.fakechat.MessageData.LAYOUT_MESSAGE_SENT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fakechat.MessageData;
import com.example.fakechat.R;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<MessageData> list;
    private String colorHex;
    private Context context;
    public MessagesAdapter(ArrayList<MessageData> list, Context context, String colorHex){
        this.list = list; this.context = context; this.colorHex = colorHex;
    }
    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getItemViewType()){
            case 0:
                return LAYOUT_MESSAGE_RECEIVED;
            case 1:
                return LAYOUT_MESSAGE_SENT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case LAYOUT_MESSAGE_RECEIVED:
                View layoutReceived = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_message_received, parent, false);
                return new ReceivedMessageHolder(layoutReceived);
            case LAYOUT_MESSAGE_SENT:
                View layoutSent = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_message_sent, parent, false);
                return new SentMessageHolder(layoutSent);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getItemViewType()){
            case LAYOUT_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).setMessage(list.get(position).getItemMessage());
                ((ReceivedMessageHolder) holder).setImage(list.get(position).getItemImageResource());
                break;
            case LAYOUT_MESSAGE_SENT:
                ((SentMessageHolder) holder).setMessage(list.get(position).getItemMessage());
                ((SentMessageHolder) holder).setColor(colorHex);
                if(list.get(position).getRead())
                    ((SentMessageHolder) holder).setImageViewRead(list.get(position).getItemImageResource());
                else
                    ((SentMessageHolder) holder).setImageViewRead(MessageData.MESSAGE_UNREAD_RESOURCE);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
