package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class InvoicesPoint {
    @SerializedName("Machine_Number")
    private String machineNumber;
    @SerializedName("Invoices_Man")
    private String invoicesMan;
    @SerializedName("Grouping")
    private String grouping;
    @SerializedName("Operator")
    private String operator;
    @SerializedName("Current_Name")
    private String currentTime;
    @SerializedName("Invoices_Time")
    private String invoicesTime;
    @SerializedName("Invoices_TypeID")
    private String invoicesTypeID;

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getInvoicesMan() {
        return invoicesMan;
    }

    public void setInvoicesMan(String invoicesMan) {
        this.invoicesMan = invoicesMan;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getInvoicesTime() {
        return invoicesTime;
    }

    public void setInvoicesTime(String invoicesTime) {
        this.invoicesTime = invoicesTime;
    }

    public String getInvoicesTypeID() {
        return invoicesTypeID;
    }

    public void setInvoicesTypeID(String invoicesTypeID) {
        this.invoicesTypeID = invoicesTypeID;
    }

    @Override
    public String toString() {
        return "InvoicesPoint{" +
                "machineNumber='" + machineNumber + '\'' +
                ", invoicesMan='" + invoicesMan + '\'' +
                ", grouping='" + grouping + '\'' +
                ", operator='" + operator + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", invoicesTime='" + invoicesTime + '\'' +
                ", invoicesTypeID='" + invoicesTypeID + '\'' +
                '}';
    }
}
