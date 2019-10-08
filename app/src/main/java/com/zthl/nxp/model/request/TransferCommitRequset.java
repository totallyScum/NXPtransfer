package com.zthl.nxp.model.request;

import com.google.gson.annotations.SerializedName;
import com.zthl.nxp.model.TurningPoint;

public class TransferCommitRequset {
   private String accountPkId;
    @SerializedName("Turning_Point")
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

    @Override
    public String toString() {
        return "TransferCommitRequset{" +
                "accountPkId='" + accountPkId + '\'' +
                ", turningPoint=" + turningPoint +
                '}';
    }
}
