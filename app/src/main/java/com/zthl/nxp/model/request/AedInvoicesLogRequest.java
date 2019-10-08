package com.zthl.nxp.model.request;

import com.google.gson.annotations.SerializedName;

public class AedInvoicesLogRequest {

    @SerializedName("AccountPkId")
    String accountPkId;
    @SerializedName("LogId")
    String logId;
    @SerializedName("Invoices_Remark")
    String invoicesRemark;
    @SerializedName("NextState")
    String nextState;

    public String getAccountPkId() {
        return accountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        this.accountPkId = accountPkId;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getInvoicesRemark() {
        return invoicesRemark;
    }

    public void setInvoicesRemark(String invoicesRemark) {
        this.invoicesRemark = invoicesRemark;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }
}
