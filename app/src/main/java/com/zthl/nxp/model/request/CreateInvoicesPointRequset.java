package com.zthl.nxp.model.request;

import com.google.gson.annotations.SerializedName;
import com.zthl.nxp.model.InvoicesPoint;

public class CreateInvoicesPointRequset {
    private String AccountPkId;
    @SerializedName("InvoicesPoint")
    private InvoicesPoint data;
    public String getAccountPkId() {
        return AccountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        AccountPkId = accountPkId;
    }

    public InvoicesPoint getData() {
        return data;
    }

    public void setData(InvoicesPoint data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CreateInvoicesPointRequset{" +
                "AccountPkId='" + AccountPkId + '\'' +
                ", data=" + data +
                '}';
    }
}
