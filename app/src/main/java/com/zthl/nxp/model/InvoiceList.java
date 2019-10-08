package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class InvoiceList {
    @SerializedName("PkId")
    private String pkId;
    @SerializedName("Invoices_State")
    private String invoicesState;
    @SerializedName("Current_Name")
    private String currentName;

    @SerializedName("Operator")
    private String operator;

    @SerializedName("InvoicesStateName")
    private String invoicesStateName;



    @SerializedName("Machine_Number")
    private String machineNumber;

    @SerializedName("InvoicesTimeStart")
    private String invoicesTimeStart;


    @SerializedName("FounderRealName")
    private String founderRealName;


    @SerializedName("Grouping")
    private String grouping;

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getFounderRealName() {
        return founderRealName;
    }

    public void setFounderRealName(String founderRealName) {
        this.founderRealName = founderRealName;
    }

    public String getInvoicesTimeStart() {
        return invoicesTimeStart;
    }

    public void setInvoicesTimeStart(String invoicesTimeStart) {
        this.invoicesTimeStart = invoicesTimeStart;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getInvoicesStateName() {
        return invoicesStateName;
    }

    public void setInvoicesStateName(String invoicesStateName) {
        this.invoicesStateName = invoicesStateName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getInvoicesState() {
        return invoicesState;
    }

    public void setInvoicesState(String invoicesState) {
        this.invoicesState = invoicesState;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    @Override
    public String toString() {
        return "InvoiceList{" +
                "pkId='" + pkId + '\'' +
                ", invoicesState='" + invoicesState + '\'' +
                ", currentName='" + currentName + '\'' +
                '}';
    }
}
