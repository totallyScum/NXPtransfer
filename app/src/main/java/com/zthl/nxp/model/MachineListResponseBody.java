package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class MachineListResponseBody {
    @SerializedName("PkId")
    String pkId;
    @SerializedName("Name")
    String name;
    @SerializedName("GroupName")
    String groupName;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
