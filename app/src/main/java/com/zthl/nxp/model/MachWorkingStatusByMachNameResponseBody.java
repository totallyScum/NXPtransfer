package com.zthl.nxp.model;

import com.google.gson.annotations.SerializedName;

public class MachWorkingStatusByMachNameResponseBody {
    @SerializedName("CurrentProgram")
    private String currentProgram;
    @SerializedName("MessageDateTime")
    private String messageDateTime;
    @SerializedName("Mach_id")
    private String Machine_Number;
    @SerializedName("MachGroupId")
    private String matchGroupId;
    @SerializedName("MachGroupName")
    private String matchGroupName;
    @SerializedName("Operator")
    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCurrentProgram() {
        return currentProgram;
    }

    public void setCurrentProgram(String currentProgram) {
        this.currentProgram = currentProgram;
    }

    public String getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(String messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    public String getMachine_Number() {
        return Machine_Number;
    }

    public void setMachine_Number(String machine_Number) {
        Machine_Number = machine_Number;
    }

    public String getMatchGroupId() {
        return matchGroupId;
    }

    public void setMatchGroupId(String matchGroupId) {
        this.matchGroupId = matchGroupId;
    }

    public String getMatchGroupName() {
        return matchGroupName;
    }

    public void setMatchGroupName(String matchGroupName) {
        this.matchGroupName = matchGroupName;
    }

    @Override
    public String toString() {
        return "MachWorkingStatusByMachNameResponseBody{" +
                "currentProgram='" + currentProgram + '\'' +
                ", messageDateTime='" + messageDateTime + '\'' +
                ", Machine_Number='" + Machine_Number + '\'' +
                ", matchGroupId='" + matchGroupId + '\'' +
                ", matchGroupName='" + matchGroupName + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
