package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurringList;

import java.util.List;

public interface OverallListResponsePv extends PresentView {
    void onSuccess(ResultData<List<TurringList>> resultData);
}