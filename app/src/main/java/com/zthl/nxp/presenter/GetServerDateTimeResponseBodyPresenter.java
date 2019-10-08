package com.zthl.nxp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.GetServerDateTimeResponseBody;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.presenterView.AedTransformLogResponsePv;
import com.zthl.nxp.presenterView.GetServerDateTimeResponsePv;
import com.zthl.nxp.presenterView.PresentView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetServerDateTimeResponseBodyPresenter  extends BasePresenter  {
    private Context mContext;
    private GetServerDateTimeResponseBody resultData2;
    private GetServerDateTimeResponsePv getServerDateTimeResponsePv;
    public GetServerDateTimeResponseBodyPresenter (Context context){
        this.mContext = context;
    }
    @Override
    public void BindPresentView(PresentView presentView) {
        getServerDateTimeResponsePv = (GetServerDateTimeResponsePv) presentView;
    }

    public void getServerDateTimeResponseInfo(SimpleRequest simpleRequest) {
        DataManager.getInstance(mContext).getServerDateTimeResponseInfo(simpleRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetServerDateTimeResponseBody>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetServerDateTimeResponseBody resultData) {
                        resultData2 = resultData;
                    }

                    @Override
                    public void onError(Throwable e) {
                        getServerDateTimeResponsePv.onError("请求失败！！");
                    }

                    @Override
                    public void onComplete() {
                        if (resultData2 != null) {
                            getServerDateTimeResponsePv.onSuccess(resultData2);

                        }
                        GetServerDateTimeResponseBodyPresenter.super.compositeDisposable.dispose();
                    }
                });

    }
}
