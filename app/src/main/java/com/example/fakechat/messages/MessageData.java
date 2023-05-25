package com.example.fakechat.messages;

import java.io.Serializable;

public class MessageData implements Serializable {
    public static final int LAYOUT_MESSAGE_RECEIVED = 0;
    public static final int LAYOUT_MESSAGE_SENT = 1;
    private int viewType;
    private String message;
    private int imageResource;
    MessageData(int viewType, String message){
        this.viewType = viewType; this.message = message;
    }
    MessageData(int viewType, String message, int imageResource){
        this.viewType = viewType; this.message = message; this.imageResource = imageResource;
    }

    public int getItemViewType(){
        return viewType;
    }
    public String getItemMessage(){
        return message;
    }
    public int getItemImageResource(){ return imageResource; }
}
