package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class TurnaroundManList {
    @SerializedName("PkId")
    String pkId;
    @SerializedName("RealName")
    String realName;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "TurnaroundManList{" +
                "pkId='" + pkId + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
