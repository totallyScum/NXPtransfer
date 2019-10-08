package com.zthl.nxp.presenter;

import android.content.Context;

import com.zthl.nxp.manager.DataManager;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.request.AedInvoicesLogRequest;
import com.zthl.nxp.model.request.AedTransformLogRequest;
import com.zthl.nxp.presenterView.AedInvoicesLogResponsePv;
import com.zthl.nxp.presenterView.AedTransformLogResponsePv;
import com.zthl.nxp.presenterView.PresentView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AedTransformLogResponseBodyPresenter  extends BasePresenter{
        private Context mContext;
        private AedTransformLogResponsePv aedTransformLogResponsePv;
        private ResultNoData resultData2;
        public AedTransformLogResponseBodyPresenter (Context context){
            this.mContext = context;
        }

        @Override
        public void BindPresentView(PresentView presentView) {
            aedTransformLogResponsePv = (AedTransformLogResponsePv) presentView;
        }

        //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
        //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
        //Activity->PresentView->Presenter->retrofit的关系就打通了
        public void getAedTransformLogResponseInfo(AedTransformLogRequest aedInvoicesLogRequest) {

            DataManager.getInstance(mContext).getAedTransformLogResponseInfo(aedInvoicesLogRequest)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<ResultNoData>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResultNoData resultNoData) {
                            resultData2 = resultNoData;
                        }

                        @Override
                        public void onError(Throwable e) {
                            aedTransformLogResponsePv.onError("请求失败！！");
                        }

                        @Override
                        public void onComplete() {
                            if (resultData2 != null) {
                                aedTransformLogResponsePv.onSuccess(resultData2);
                            }
                            AedTransformLogResponseBodyPresenter.super.compositeDisposable.dispose();
                        }
                    });
        }
}