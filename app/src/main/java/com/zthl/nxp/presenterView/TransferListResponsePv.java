package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.TurringList;

import java.util.List;

public interface TransferListResponsePv  extends PresentView  {
    void onSuccess(ResultData<List<TurringList>> resultNet);
}
