package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class GetServerDateTimeResponseBody {
    @SerializedName("AlertMessage")
    private String AlertMessage;
    @SerializedName("State")
    private int State;
    @SerializedName("DateTime")
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    public String getAlertMessage() {
        return AlertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        AlertMessage = alertMessage;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
