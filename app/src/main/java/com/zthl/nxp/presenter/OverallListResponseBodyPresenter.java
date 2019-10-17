package com.zthl.nxp.presenter;

import android.content.Context;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.model.request.PersonalListRequest;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.presenterView.OverallListResponsePv;
import com.zthl.nxp.presenterView.PersonalListResponsePv;
import com.zthl.nxp.presenterView.PresentView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OverallListResponseBodyPresenter extends BasePresenter  {

    private Context mContext;
    private OverallListResponsePv overallListResponsePv;
    private ResultData<List<TurringList>> resultData2;
    public OverallListResponseBodyPresenter (Context context){
        this.mContext = context;
    }
    @Override
    public void BindPresentView(PresentView presentView) {
        overallListResponsePv = (OverallListResponsePv) presentView;
    }

    //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
    //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
    //Activity->PresentView->Presenter->retrofit的关系就打通了
    public void getOverallListResponseInfo(SimpleRequest simpleRequest) {
        DataManager.getInstance(mContext).getOverallListResponseInfo(simpleRequest)
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
                        overallListResponsePv.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        if (resultData2 != null) {
                            overallListResponsePv.onSuccess(resultData2);
                        }
                        OverallListResponseBodyPresenter.super.compositeDisposable.dispose();
                    }
                });
    }
}
