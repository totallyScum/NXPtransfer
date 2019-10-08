package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("PkId")
        private String pkID;

    public String getPkID() {
        return pkID;
    }

    public void setPkID(String pkID) {
        this.pkID = pkID;
    }

    @Override
    public String toString() {
        return "Account{" +
                "pkID='" + pkID + '\'' +
                '}';
    }
}
