package com.zthl.nxp.model.request;

public class TurnaroundManListRequest {
    private int AccountPkId;

    public int getAccountPkId() {
        return AccountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        this.AccountPkId = Integer.parseInt(accountPkId);
    }
}
