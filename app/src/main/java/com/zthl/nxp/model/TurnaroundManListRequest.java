package com.zthl.nxp.model;

public class TurnaroundManListRequest {
    private int AccountPkId;

    public int getAccountPkId() {
        return AccountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        this.AccountPkId = Integer.parseInt(accountPkId);
    }
}
