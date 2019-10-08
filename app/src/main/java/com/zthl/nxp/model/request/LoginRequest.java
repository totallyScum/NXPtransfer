package com.zthl.nxp.model.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("LoginName")
    private String loginName;
    @SerializedName("LoginPassword")
    private String loginPassword;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
