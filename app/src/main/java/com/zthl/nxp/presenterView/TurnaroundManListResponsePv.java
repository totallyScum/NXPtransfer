package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.TurnaroundManList;

import java.util.ArrayList;
import java.util.List;

public interface TurnaroundManListResponsePv extends PresentView  {
    void onSuccess(ResultData<List<TurnaroundManList>> resultNet);
}
