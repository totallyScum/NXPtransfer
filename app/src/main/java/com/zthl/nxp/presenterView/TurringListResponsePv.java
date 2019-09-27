package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.TurringList;

import java.util.List;

public interface TurringListResponsePv  extends PresentView {
    void onSuccess(ResultData<List<TurringList>> resultNet);
}
