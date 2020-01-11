package com.zthl.nxp.model.request;

import com.google.gson.annotations.SerializedName;

public class SimpleRequest {
    private String AccountPkId;
    @SerializedName("CustomSort")
    private String customSort;

    public String getAccountPkId() {
        return AccountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        AccountPkId = accountPkId;
    }


    public String getCustomSort() {
        return customSort;
    }

    public void setCustomSort(String customSort) {
        this.customSort = customSort;
    }

    @Override
    public String toString() {
        return "SimpleRequest{" +
                "AccountPkId='" + AccountPkId + '\'' +
                '}';
    }
}
