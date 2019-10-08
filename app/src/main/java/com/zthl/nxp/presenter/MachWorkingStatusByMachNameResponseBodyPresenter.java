package com.zthl.nxp.presenter;

import android.content.Context;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.MachWorkingStatusByMachNameResponseBody;
import com.zthl.nxp.model.MachineRequest;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.presenterView.MachWorkingStatusByMachNameResponsePv;
import com.zthl.nxp.presenterView.PresentView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MachWorkingStatusByMachNameResponseBodyPresenter extends BasePresenter {
    private Context mContext;
    private MachWorkingStatusByMachNameResponsePv createInvoicesResponsePV;
    private ResultData<List<MachWorkingStatusByMachNameResponseBody>> resultData2;
    public MachWorkingStatusByMachNameResponseBodyPresenter (Context context){
        this.mContext = context;
    }
    @Override
    public void BindPresentView(PresentView presentView) {
        createInvoicesResponsePV = (MachWorkingStatusByMachNameResponsePv) presentView;
    }

    //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
    //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
    //Activity->PresentView->Presenter->retrofit的关系就打通了
    public void getMachWorkingStatusByMachNameResponseInfo(MachineRequest machineRequest) {
        DataManager.getInstance(mContext).getMachWorkingStatusByMachNameResponseInfo(machineRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultData<List<MachWorkingStatusByMachNameResponseBody>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultData<List<MachWorkingStatusByMachNameResponseBody>> resultData) {
                        resultData2 = resultData;
                    }

                    @Override
                    public void onError(Throwable e) {
                        createInvoicesResponsePV.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        if (resultData2 != null) {
                            createInvoicesResponsePV.onSuccess(resultData2);
                        }
                        MachWorkingStatusByMachNameResponseBodyPresenter.super.compositeDisposable.dispose();
                    }
                });
    }
}
