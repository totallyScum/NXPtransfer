package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.model.MachWorkingStatusByMachNameResponseBody;
import com.zthl.nxp.model.ResultData;

import java.util.List;

public interface  MachWorkingStatusByMachNameResponsePv  extends PresentView {
    void onSuccess(ResultData<List<MachWorkingStatusByMachNameResponseBody>> resultData);
}
