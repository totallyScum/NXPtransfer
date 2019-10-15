package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class InvoicesType {
    @SerializedName("PkId")
    String pkId;
    @SerializedName("Name")
    String name;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
