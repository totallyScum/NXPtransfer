package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.InvoiceList;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurringList;

import java.util.List;

public interface InvoiceListResponsePv  extends PresentView {
    void onSuccess(final ResultData<List<InvoiceList>> resultNet);
}
