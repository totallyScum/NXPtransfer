package com.zthl.nxp.model;

public class TransferCommitRequset {
   private String accountPkId;
private TurningPoint turningPoint;

    public String getAccountPkId() {
        return accountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        this.accountPkId = accountPkId;
    }

    public TurningPoint getTurningPoint() {
        return turningPoint;
    }

    public void setTurningPoint(TurningPoint turningPoint) {
        this.turningPoint = turningPoint;
    }
}
