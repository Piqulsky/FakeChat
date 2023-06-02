package com.example.fakechat;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatData implements Serializable {
    private String receiverName;
    private String avatar;
    private Boolean isRead = true;
    private String activity = "online";
    private ArrayList<MessageData> messagesData;

    public ChatData(){
        receiverName = "EmptyName";
        avatar = "android.resource://com.example.fakechat/drawable/avatar";
        messagesData = new ArrayList<>();
    }
    public ChatData(String receiverName, String avatar, ArrayList<MessageData> messagesData){
        this.receiverName = receiverName;
        this.avatar = avatar;
        this.messagesData = messagesData;
    }
    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
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
