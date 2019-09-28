package com.zthl.nxp.presenter;

import android.content.Context;
import android.util.Log;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.LoginRequest;
import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.SimpleRequest;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.presenterView.HistoryResponsePv;
import com.zthl.nxp.presenterView.LoginResponsePv;
import com.zthl.nxp.presenterView.PresentView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryResponseBodyPresenter extends BasePresenter {
    private Context mContext;
    private HistoryResponsePv historyResponsePv;
    private ResultData<List<TurringList>> resultData2;
    public HistoryResponseBodyPresenter (Context context){
        this.mContext = context;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        historyResponsePv = (HistoryResponsePv)presentView;
    }

    //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
    //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
    //Activity->PresentView->Presenter->retrofit的关系就打通了
    public void getHistoryResponseInfo(SimpleRequest simpleRequest) {
        DataManager.getInstance(mContext).getHistoryResponseInfo(simpleRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultData<List<TurringList>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultData<List<TurringList>> resultData) {
                        resultData2 = resultData;
                    }

                    @Override
                    public void onError(Throwable e) {
                        historyResponsePv.onError("请求失败！！");
                    }

                    @Override
                    public void onComplete() {
                        if (resultData2 != null) {
                            historyResponsePv.onSuccess(resultData2);
                        }
                        HistoryResponseBodyPresenter.super.compositeDisposable.dispose();
                    }
                });
    }
}
