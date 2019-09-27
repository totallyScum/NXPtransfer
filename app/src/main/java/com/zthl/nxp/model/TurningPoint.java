package com.zthl.nxp.model;

public class TurningPoint {
   private String machineNumber;
   private String turnaroundMan;
   private String grouping;
   private String operator;
   private String currentName;
   private String targetProgram;
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
}
