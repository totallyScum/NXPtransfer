package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.model.ResultNoData;

public interface TransferCommitResponsePv extends PresentView {
    void onSuccess(ResultNoData resultNet);
}
