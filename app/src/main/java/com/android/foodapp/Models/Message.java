package com.android.foodapp.Models;

public class Message {
    private int Status;
    private String Notification;
    private String Data;

    public Message(){}

    public Message(int status, String notification, String data) {
        this.Status = status;
        this.Notification = notification;
        this.Data = data;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        this.Status = status;
    }

    public String getNotification() {
        return Notification;
    }

    public void setNotification(String notification) {
        this.Notification = notification;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
