package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponseBody {
Account Account;
Boolean AlertMessage;
int State;

    public Account getAccount() {
        return Account;
    }

    public void setAccount(Account account) {
        Account = account;
    }

    public Boolean getAlertMessage() {
        return AlertMessage;
    }

    public void setAlertMessage(Boolean alertMessage) {
        AlertMessage = alertMessage;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }



    @Override
    public String toString() {
        return "LoginResponseBody{" +
                "Account=" + Account +
                ", AlertMessage=" + AlertMessage +
                ", State=" + State +
                '}';
    }
}
