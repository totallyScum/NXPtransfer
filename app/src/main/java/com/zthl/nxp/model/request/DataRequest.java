package com.zthl.nxp.model.request;

public class DataRequest<T> {
    private String AccountPkId;

    private T data;
    public String getAccountPkId() {
        return AccountPkId;
    }

    public void setAccountPkId(String accountPkId) {
        AccountPkId = accountPkId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataRequest{" +
                "AccountPkId='" + AccountPkId + '\'' +
                ", data=" + data +
                '}';
    }
}
