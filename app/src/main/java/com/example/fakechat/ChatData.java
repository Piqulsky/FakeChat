package com.example.fakechat;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatData implements Serializable {
    private String receiverName;
    private int avatar;
    private Boolean isRead = true;
    private String activity = "online";
    private ArrayList<MessageData> messagesData;

    public ChatData(){
        receiverName = "EmptyName";
        avatar = R.drawable.avatar;
        messagesData = new ArrayList<>();
    }
    public ChatData(String receiverName, int avatar, ArrayList<MessageData> messagesData){
        this.receiverName = receiverName;
        this.avatar = avatar;
        this.messagesData = messagesData;
    }
    public ChatData(String receiverName, int avatar, ArrayList<MessageData> messagesData, Boolean isRead){
        this.receiverName = receiverName;
        this.avatar = avatar;
        this.messagesData = messagesData;
        this.isRead = isRead;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public int getAvatar() {
        return avatar;
    }
    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
    public ArrayList<MessageData> getMessagesData() {
        return messagesData;
    }
    public void addMessageData(MessageData messageData){
        messagesData.add(messageData);
    }
    public Boolean getRead() {
        return isRead;
    }
    public void setRead(Boolean read) {
        isRead = read;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    public String getActivity() {
        return activity;
    }
}
