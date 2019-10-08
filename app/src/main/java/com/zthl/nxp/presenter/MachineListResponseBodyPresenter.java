package com.zthl.nxp.presenter;

import android.content.Context;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.MachWorkingStatusByMachNameResponseBody;
import com.zthl.nxp.model.MachineListResponseBody;
import com.zthl.nxp.model.MachineRequest;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.presenterView.MachWorkingStatusByMachNameResponsePv;
import com.zthl.nxp.presenterView.MachineListResponsePv;
import com.zthl.nxp.presenterView.PresentView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MachineListResponseBodyPresenter extends BasePresenter {
    private Context mContext;
    private MachineListResponsePv machineListResponsePv;
    private ResultData<List<MachineListResponseBody>> resultData2;
    public MachineListResponseBodyPresenter (Context context){
        this.mContext = context;
    }
    @Override
    public void BindPresentView(PresentView presentView) {
        machineListResponsePv = (MachineListResponsePv) presentView;
    }

    //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
    //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
    //Activity->PresentView->Presenter->retrofit的关系就打通了
    public void getMachineListResponseInfo(SimpleRequest simpleRequest) {
        DataManager.getInstance(mContext).getMachineListResponseInfo(simpleRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultData<List<MachineListResponseBody>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultData<List<MachineListResponseBody>> resultData) {
                        resultData2 = resultData;
                    }

                    @Override
                    public void onError(Throwable e) {
                        machineListResponsePv.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        if (resultData2 != null) {
                            machineListResponsePv.onSuccess(resultData2);
                        }
                        MachineListResponseBodyPresenter.super.compositeDisposable.dispose();
                    }
                });
    }

}
