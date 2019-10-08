package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class MachineRequest {
    @SerializedName("AccountPkId")
    private String AccountPkId;
    @SerializedName("Machine_Number")
    private String machineNumber;


    public String getAccountPkId() {
        return AccountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        AccountPkId = accountPkId;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    @Override
    public String toString() {
        return "MachineRequest{" +
                "AccountPkId='" + AccountPkId + '\'' +
                ", machineNumber='" + machineNumber + '\'' +
                '}';
    }
}
