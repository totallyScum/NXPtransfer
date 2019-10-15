package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.GetServerDateTimeResponseBody;
import com.zthl.nxp.model.InvoicesType;
import com.zthl.nxp.model.ResultData;

import java.util.List;

public interface GetInvoicesTypeResponsePv  extends PresentView {
    void onSuccess(final ResultData<List<InvoicesType>> resultNet);
}