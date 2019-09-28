package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TurringList {
    @SerializedName("PkId")
    private String pKId;
    @SerializedName("CreatedBy")
    private String createBy;
    @SerializedName("CreatedDateTime")
    private String CreatedDateTime;
    @SerializedName("Current_Name")
    private String currentName;
    @SerializedName("Grouping")
    private String grouping;
    @SerializedName("Machine_Number")
    private String machineNumber;
    @SerializedName("Operator")
    private String operator;
       @SerializedName("Target_Program")
    private String targetProgram;
    @SerializedName("Turnaround_Man")
    private String turnaroundMan;
    @SerializedName("TurningState")
    private String turningState;

    @SerializedName("TurningRemark")
    private String turningRemark;
    @SerializedName("Founder")
    private String founder;
    @SerializedName("Transit_Situation")
    private String transitSituation;
    @SerializedName("Billing_Time")
    private String billingTime;
    @SerializedName("Conversion_Time")
    private String conversionTime;
    @SerializedName("TurningStateName")
    private String turningStateName;
    @SerializedName("MessageType")
    private String messageType;

    public String getBillingTime() {
        return billingTime;
    }

    public void setBillingTime(String billingTime) {
        this.billingTime = billingTime;
    }

    public String getpKId() {
        return pKId;
    }

    public void setpKId(String pKId) {
        this.pKId = pKId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreatedDateTime() {
        return CreatedDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        CreatedDateTime = createdDateTime;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTargetProgram() {
        return targetProgram;
    }

    public void setTargetProgram(String targetProgram) {
        this.targetProgram = targetProgram;
    }

    public String getTurnaroundMan() {
        return turnaroundMan;
    }

    public void setTurnaroundMan(String turnaroundMan) {
        this.turnaroundMan = turnaroundMan;
    }

    public String getTurningState() {
        return turningState;
    }

    public void setTurningState(String turningState) {
        this.turningState = turningState;
    }

    public String getTurningRemark() {
        return turningRemark;
    }

    public void setTurningRemark(String turningRemark) {
        this.turningRemark = turningRemark;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getTransitSituation() {
        return transitSituation;
    }

    public void setTransitSituation(String transitSituation) {
        this.transitSituation = transitSituation;
    }

    public String getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(String conversionTime) {
        this.conversionTime = conversionTime;
    }

    public String getTurningStateName() {
        return turningStateName;
    }

    public void setTurningStateName(String turningStateName) {
        this.turningStateName = turningStateName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
