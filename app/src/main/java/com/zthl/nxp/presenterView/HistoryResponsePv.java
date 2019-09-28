package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.TurringList;

import java.util.List;

public interface HistoryResponsePv  extends PresentView{
    void onSuccess(final ResultData<List<TurringList>> resultNet);
}
