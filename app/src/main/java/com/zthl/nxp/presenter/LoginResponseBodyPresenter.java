package com.zthl.nxp.presenter;

import android.content.Context;

import com.zthl.nxp.manager.DataManager;

import com.zthl.nxp.model.request.LoginRequest;
import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.presenterView.LoginResponsePv;
import com.zthl.nxp.presenterView.PresentView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 */

//该类是具体业务presenter，如需增加另一个业务，比如Order
//则可以再创建一个OrderPresenter
public class LoginResponseBodyPresenter extends BasePresenter {
    private Context mContext;
    private LoginResponsePv loginResponsePv;
    private LoginResponseBody mLoginResponseBody;
    public LoginResponseBodyPresenter (Context context){
        this.mContext = context;
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        loginResponsePv = (LoginResponsePv)presentView;
    }

    //在presenter中实现业务逻辑，此处会调用前面封装好的retrofit的东西
    //将处理结果绑定到对应的PresentView实例，这样Activity和PresentView实例绑定好之后，
    //Activity->PresentView->Presenter->retrofit的关系就打通了
    public void getLoginResponseInfo(LoginRequest loginRequest) {
        DataManager.getInstance(mContext).getLoginResponseInfo(loginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponseBody loginResponseBody) {
                        mLoginResponseBody = loginResponseBody;
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginResponsePv.onError("请求失败！！");
                    }

                    @Override
                    public void onComplete() {
                        if (mLoginResponseBody != null) {
                            loginResponsePv.onSuccess(mLoginResponseBody);
                        }
                        LoginResponseBodyPresenter.super.compositeDisposable.dispose();
                    }
                });
    }
    }

