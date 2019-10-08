package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.ProgramList;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;

import java.util.List;

public interface ProgramListResponsePv extends PresentView {
    void onSuccess(ResultData<List<ProgramList>> resultData);
}