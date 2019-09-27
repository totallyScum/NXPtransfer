package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.LoginResponseBody;

public interface HistoryResponsePv  extends PresentView{
    void onSuccess(LoginResponseBody loginResponseBody);
}
