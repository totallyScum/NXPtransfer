package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.model.MachineListResponseBody;
import com.zthl.nxp.model.ResultData;

import java.util.List;

public interface MachineListResponsePv  extends PresentView{
    void onSuccess(ResultData<List<MachineListResponseBody>> resultData);
}
