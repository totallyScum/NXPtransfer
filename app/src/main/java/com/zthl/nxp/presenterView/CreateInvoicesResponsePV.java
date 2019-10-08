package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.MachWorkingStatusByMachNameResponseBody;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.TurringList;

import java.util.List;

public interface CreateInvoicesResponsePV extends PresentView {
    void onSuccess(final ResultNoData resultNoData);
}
