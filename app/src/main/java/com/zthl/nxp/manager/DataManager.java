package com.zthl.nxp.manager;

import android.content.Context;

import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.SimpleRequest;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.TurnaroundManListRequest;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.retrofit.RetrofitApiService;
import com.zthl.nxp.retrofit.RetrofitUtil;
import com.zthl.nxp.model.LoginRequest;
import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.TransferCommitRequset;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 */

//该类用来管理RetrofitApiService中对应的各种API接口，
// 当做Retrofit和presenter中的桥梁，Activity就不用直接和retrofit打交道了
public class DataManager {
    private RetrofitApiService mRetrofitService;
    private volatile static DataManager instance;

    private DataManager(Context context){
        this.mRetrofitService = RetrofitUtil.getInstance(context).getRetrofitApiService();
    }
    //由于该对象会被频繁调用，采用单例模式，下面是一种线程安全模式的单例写法
    public static DataManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager(context);
                }
            }
        }
        return instance;
    }

    public Observable<LoginResponseBody> getLoginResponseInfo(LoginRequest loginRequest){
        return mRetrofitService.getLoginResponseInfo(loginRequest);
    }
    public Observable<ResultNoData> getTransferCommitResponseInfo(TransferCommitRequset transferCommitRequset){   //获取所有提交表单返回的信息
        return mRetrofitService.getTransferCommitResponseInfo(transferCommitRequset);
    }
    public Observable<ResultData<List<TurnaroundManList>>> getTurnaroundManListResponseInfo(TurnaroundManListRequest transferCommitRequset){     //获取所有转换员
        return mRetrofitService.getTurnaroundManListResponseInfo(transferCommitRequset);
    }
    public Observable<ResultData<List<TurringList>>> getTurringListResponseInfo(SimpleRequest simpleRequest){     //获取所有转换员
        return mRetrofitService.getTurringListResponseInfo(simpleRequest);
    }
}
