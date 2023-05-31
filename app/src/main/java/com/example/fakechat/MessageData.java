package com.example.fakechat;

import java.io.Serializable;

public class MessageData implements Serializable {
    public static final int LAYOUT_MESSAGE_RECEIVED = 0;
    public static final int LAYOUT_MESSAGE_SENT = 1;
    public static final int MESSAGE_UNREAD_RESOURCE = R.drawable.sent_checkmark;
    private int viewType;
    private String message;
    private int imageResource;
    private Boolean isRead = false;
    public MessageData(int imageResource){
        viewType = 0; message = ""; this.imageResource = imageResource;
    }
    public MessageData(int viewType, String message, int imageResource){
        this.viewType = viewType; this.message = message; this.imageResource = imageResource;
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
    public int getItemImageResource(){ return imageResource; }
    public void setRead(Boolean read) {
        isRead = read;
    }
    public Boolean getRead() {
        return isRead;
    }
}
