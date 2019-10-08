package com.zthl.nxp.model.request;

import com.google.gson.annotations.SerializedName;

public class PersonalListRequest {
    @SerializedName("AccountPkId")
    String accountPkId;
    @SerializedName("Machine_Number")
    String machineNumber;
    @SerializedName("Founder")
    String founder;
    @SerializedName("Target_Program")
    String targetProgram;
    @SerializedName("Seek")
    String seek;
    @SerializedName("QuestionDateTimeStart")
    String billingTimeStart;
    @SerializedName("QuestionDateTimeEnd")
    String billingTimeEnd;

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

    public String getTargetProgram() {
        return targetProgram;
    }

    public void setTargetProgram(String targetProgram) {
        this.targetProgram = targetProgram;
    }

    public String getSeek() {
        return seek;
    }

    public void setSeek(String seek) {
        this.seek = seek;
    }

    public String getBillingTimeStart() {
        return billingTimeStart;
    }

    public void setBillingTimeStart(String billingTimeStart) {
        this.billingTimeStart = billingTimeStart;
    }

    public String getBillingTimeEnd() {
        return billingTimeEnd;
    }

    public void setBillingTimeEnd(String billingTimeEnd) {
        this.billingTimeEnd = billingTimeEnd;
    }
}
