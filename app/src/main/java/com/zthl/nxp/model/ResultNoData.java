package com.zthl.nxp.model;

public class ResultNoData {
    private String State;
    private String AlertMessage;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = state;
    }

    public String getAlertMessage() {
        return AlertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.AlertMessage = alertMessage;
    }

    @Override
    public String toString() {
        return "ResultNoData{" +
                "state=" + State +
                ", alertMessage='" + AlertMessage + '\'' +
                '}';
    }
}
