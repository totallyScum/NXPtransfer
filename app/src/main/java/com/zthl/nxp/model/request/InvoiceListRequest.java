package com.zthl.nxp.model.request;

import com.google.gson.annotations.SerializedName;

public class InvoiceListRequest {
    @SerializedName("AccountPkId")
    private String accountPkId;
    @SerializedName("Machine_Number")
    private String machineNumber;
    @SerializedName("Founder")
    private String founder;
    @SerializedName("Invoices_TypeID")
    private String incoicesTypeID;

    public String getAccountPkId() {
        return accountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        this.accountPkId = accountPkId;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getIncoicesTypeID() {
        return incoicesTypeID;
    }

    public void setIncoicesTypeID(String incoicesTypeID) {
        this.incoicesTypeID = incoicesTypeID;
    }
}
