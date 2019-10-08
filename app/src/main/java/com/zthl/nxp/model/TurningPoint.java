package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class TurningPoint {
    @SerializedName("Machine_Number")
   private String machineNumber;
    @SerializedName("Turnaround_Man")
   private String turnaroundMan;
    @SerializedName("Grouping")
   private String grouping;
    @SerializedName("Operator")
   private String operator;
    @SerializedName("Current_Name")
   private String currentName;
    @SerializedName("Target_Program")
   private String targetProgram;
    @SerializedName("Billing_Time")
   private String billingtime;

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getTurnaroundMan() {
        return turnaroundMan;
    }

    public void setTurnaroundMan(String turnaroundMan) {
        this.turnaroundMan = turnaroundMan;
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

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public String getTargetProgram() {
        return targetProgram;
    }

    public void setTargetProgram(String targetProgram) {
        this.targetProgram = targetProgram;
    }

    public String getBillingtime() {
        return billingtime;
    }

    public void setBillingtime(String billingtime) {
        this.billingtime = billingtime;
    }

    @Override
    public String toString() {
        return "TurningPoint{" +
                "machineNumber='" + machineNumber + '\'' +
                ", turnaroundMan='" + turnaroundMan + '\'' +
                ", grouping='" + grouping + '\'' +
                ", operator='" + operator + '\'' +
                ", currentName='" + currentName + '\'' +
                ", targetProgram='" + targetProgram + '\'' +
                ", billingtime='" + billingtime + '\'' +
                '}';
    }
}
