package com.zthl.nxp.presenter;

import android.content.Context;
import android.util.Log;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.SimpleRequest;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.TurnaroundManListRequest;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.presenterView.PresentView;
import com.zthl.nxp.presenterView.TurnaroundManListResponsePv;
import com.zthl.nxp.presenterView.TurringListResponsePv;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TurringListResponseBodyPresenter extends BasePresenter {
    private Context mContext;
    private TurringListResponsePv turringListResponsePv;
    private ResultData<List<TurringList>> resultNet;
    public TurringListResponseBodyPresenter (Context context){
        this.mContext = context;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        turringListResponsePv = (TurringListResponsePv) presentView;
    }

    //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
    //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
    //Activity->PresentView->Presenter->retrofit的关系就打通了
    public void getTurringListResponseInfo(SimpleRequest simpleRequest) {
        DataManager.getInstance(mContext).getTurringListResponseInfo(simpleRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultData<List<TurringList>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultData<List<TurringList>> resultNetdata) {
                        resultNet = resultNetdata;
                    }

                    @Override
                    public void onError(Throwable e) {
                        turringListResponsePv.onError("请求失败！！");
                    }

                    @Override
                    public void onComplete() {
                        if (resultNet != null) {
                            turringListResponsePv.onSuccess(resultNet);
                            Log.d("23333456","77777");
                            TurringListResponseBodyPresenter.super.compositeDisposable.dispose();
                        }
                    }
                });
    }
}
