package com.zthl.nxp.presenter;

import android.content.Context;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.GetServerDateTimeResponseBody;
import com.zthl.nxp.model.InvoiceList;
import com.zthl.nxp.model.InvoicesType;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.presenterView.GetInvoicesTypeResponsePv;
import com.zthl.nxp.presenterView.GetServerDateTimeResponsePv;
import com.zthl.nxp.presenterView.PresentView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetInvoicesTypeResponseBodyPresenter extends BasePresenter  {
    private Context mContext;
    private ResultData<List<InvoicesType>> resultData2;
    private GetInvoicesTypeResponsePv getServerDateTimeResponsePv;
    public GetInvoicesTypeResponseBodyPresenter (Context context){
        this.mContext = context;
    }
    @Override
    public void BindPresentView(PresentView presentView) {
        getServerDateTimeResponsePv = (GetInvoicesTypeResponsePv) presentView;
    }

    public void getInvoicesTypeResponseInfo(SimpleRequest simpleRequest) {
        DataManager.getInstance(mContext).getInvoicesTypeResponseInfo(simpleRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultData<List<InvoicesType>>>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultData<List<InvoicesType>> listResultData) {
                        resultData2 = listResultData;
                    }


                    @Override
                    public void onError(Throwable e) {
                        getServerDateTimeResponsePv.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        if (resultData2 != null) {
                            getServerDateTimeResponsePv.onSuccess(resultData2);

                        }
                        GetInvoicesTypeResponseBodyPresenter.super.compositeDisposable.dispose();
                    }
                });

    }
}
