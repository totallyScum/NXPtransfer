package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class ProgramList {
    @SerializedName("PkId")
    private String pkId;
    @SerializedName("Name")
    private String name;

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
