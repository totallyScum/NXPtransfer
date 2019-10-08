package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.GetServerDateTimeResponseBody;
import com.zthl.nxp.model.InvoiceList;
import com.zthl.nxp.model.ResultData;

import java.util.List;

public interface GetServerDateTimeResponsePv  extends PresentView {
    void onSuccess(final GetServerDateTimeResponseBody resultNet);
}

