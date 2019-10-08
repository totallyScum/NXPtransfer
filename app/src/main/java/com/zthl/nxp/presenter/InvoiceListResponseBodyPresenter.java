package com.zthl.nxp.presenter;

import android.content.Context;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.InvoiceList;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.request.InvoiceListRequest;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.presenterView.InvoiceListResponsePv;
import com.zthl.nxp.presenterView.PresentView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InvoiceListResponseBodyPresenter  extends BasePresenter {
    private Context mContext;
    private InvoiceListResponsePv invoiceListResponsePv;
    private ResultData<List<InvoiceList>> resultData2;
    public InvoiceListResponseBodyPresenter (Context context){
        this.mContext = context;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        invoiceListResponsePv = (InvoiceListResponsePv)presentView;
    }

    //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
    //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
    //Activity->PresentView->Presenter->retrofit的关系就打通了
    public void getInvoiceListResponseInfo(InvoiceListRequest invoiceListRequest) {
        DataManager.getInstance(mContext).getInvoiceListResponseInfo(invoiceListRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultData<List<InvoiceList>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultData<List<InvoiceList>> resultData) {
                        resultData2 = resultData;
                    }

                    @Override
                    public void onError(Throwable e) {
                        invoiceListResponsePv.onError("请求失败！！");
                    }

                    @Override
                    public void onComplete() {
                        if (resultData2 != null) {
                            invoiceListResponsePv.onSuccess(resultData2);
                        }
                        InvoiceListResponseBodyPresenter.super.compositeDisposable.dispose();
                    }
                });
    }
}
