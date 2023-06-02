package com.example.fakechat;

import com.example.fakechat.messages.MainActivity;

import java.io.Serializable;

public class MessageData implements Serializable {
    public static final int LAYOUT_MESSAGE_RECEIVED = 0;
    public static final int LAYOUT_MESSAGE_SENT = 1;
    private int viewType;
    private String message;
    private Boolean isRead = false;
    public MessageData(){
        viewType = 0; message = "";
    }
    public MessageData(int viewType, String message){
        this.viewType = viewType; this.message = message;
    }

    public int getItemViewType(){
        return viewType;
    }
    public void setItemViewType(int viewType) {
        this.viewType = viewType;
    }
    public String getItemMessage(){
        return message;
    }
    public void setItemMessage(String message) {
        this.message = message;
    }
    public void setRead(Boolean read) {
        isRead = read;
    }
    public Boolean getRead() {
        return isRead;
    }
}
