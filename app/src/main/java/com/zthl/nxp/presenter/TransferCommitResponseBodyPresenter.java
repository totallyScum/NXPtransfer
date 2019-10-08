package com.zthl.nxp.presenter;

import android.content.Context;
import android.util.Log;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.request.TransferCommitRequset;
import com.zthl.nxp.presenterView.PresentView;
import com.zthl.nxp.presenterView.TransferCommitResponsePv;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TransferCommitResponseBodyPresenter extends BasePresenter {
    private Context mContext;
    private TransferCommitResponsePv transferCommitResponsePv;
    private ResultNoData resultNet;
    public TransferCommitResponseBodyPresenter (Context context){
        this.mContext = context;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        transferCommitResponsePv = (TransferCommitResponsePv)presentView;
    }

    //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
    //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
    //Activity->PresentView->Presenter->retrofit的关系就打通了
    public void getTransferCommitResponseInfo(TransferCommitRequset transferCommitRequset) {
        DataManager.getInstance(mContext).getTransferCommitResponseInfo(transferCommitRequset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultNoData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultNoData loginResponseBody) {
                        resultNet = loginResponseBody;
                    }

                    @Override
                    public void onError(Throwable e) {
                        transferCommitResponsePv.onError("请求失败！！");
                    }

                    @Override
                    public void onComplete() {
                        if (resultNet != null) {
                            transferCommitResponsePv.onSuccess(resultNet);
                            Log.d("23333456","77777");
                            TransferCommitResponseBodyPresenter.super.compositeDisposable.dispose();
                        }
                    }
                    });
    }
}
