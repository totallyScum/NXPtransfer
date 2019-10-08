package com.zthl.nxp.presenter;

import android.content.Context;
import android.util.Log;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.ProgramList;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.model.request.TransferCommitRequset;
import com.zthl.nxp.presenterView.PresentView;
import com.zthl.nxp.presenterView.ProgramListResponsePv;
import com.zthl.nxp.presenterView.TransferCommitResponsePv;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProgramListResponseBodyPresenter extends BasePresenter  {
    private Context mContext;
    private ProgramListResponsePv  programListResponsePv;
    private ResultData<List<ProgramList>> resultNet;
    public ProgramListResponseBodyPresenter (Context context){
        this.mContext = context;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        programListResponsePv = (ProgramListResponsePv) presentView;
    }

    //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
    //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
    //Activity->PresentView->Presenter->retrofit的关系就打通了
    public void getProgramListResponseInfo(SimpleRequest simpleRequest) {
        DataManager.getInstance(mContext).getProgramListResponseInfo(simpleRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultData<List<ProgramList>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultData<List<ProgramList>> listResultData) {
                        resultNet = listResultData;
                    }



                    @Override
                    public void onError(Throwable e) {
                        programListResponsePv.onError("请求失败！！");
                    }

                    @Override
                    public void onComplete() {
                        if (resultNet != null) {
                            programListResponsePv.onSuccess(resultNet);
                            Log.d("23333456","77777");
                            ProgramListResponseBodyPresenter.super.compositeDisposable.dispose();
                        }
                    }
                });
    }
}
