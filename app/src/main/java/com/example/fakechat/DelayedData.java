package com.example.fakechat;

import java.io.Serializable;

public class DelayedData implements Serializable {
    private String message;
    private float seconds;

    public DelayedData(){
        message = ""; seconds = 1.0f;
    }
    public DelayedData(String message, float seconds){
        this.message = message;
        this.seconds = seconds;
    }

    public String getMessage(){
        return message;
    }
    public float getSeconds(){
        return seconds;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setDelay(float delay){
        this.seconds = delay;
    }
}
