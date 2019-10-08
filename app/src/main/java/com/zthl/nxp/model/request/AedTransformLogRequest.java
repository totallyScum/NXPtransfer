package com.zthl.nxp.model.request;

import com.google.gson.annotations.SerializedName;

public class AedTransformLogRequest {
    @SerializedName("AccountPkId")
   private String AccountPkId;
    @SerializedName("LogId")
 private    String logId;
    @SerializedName("TurningRemark")
 private    String turningRemark;
    @SerializedName("NextState")
  private   String nextState;


    public String getAccountPkId() {
        return AccountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        AccountPkId = accountPkId;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getTurningRemark() {
        return turningRemark;
    }

    public void setTurningRemark(String turningRemark) {
        this.turningRemark = turningRemark;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }
}
