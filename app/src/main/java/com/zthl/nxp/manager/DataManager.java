package com.zthl.nxp.manager;

import android.content.Context;

import com.zthl.nxp.model.GetServerDateTimeResponseBody;
import com.zthl.nxp.model.InvoiceList;
import com.zthl.nxp.model.InvoicesType;
import com.zthl.nxp.model.MachineListResponseBody;
import com.zthl.nxp.model.ProgramList;
import com.zthl.nxp.model.request.AedInvoicesLogRequest;
import com.zthl.nxp.model.request.AedTransformLogRequest;
import com.zthl.nxp.model.request.CreateInvoicesPointRequset;
import com.zthl.nxp.model.request.DataRequest;
import com.zthl.nxp.model.InvoicesPoint;
import com.zthl.nxp.model.MachWorkingStatusByMachNameResponseBody;
import com.zthl.nxp.model.MachineRequest;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.request.InvoiceListRequest;
import com.zthl.nxp.model.request.PersonalListRequest;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.request.TurnaroundManListRequest;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.retrofit.RetrofitApiService;
import com.zthl.nxp.retrofit.RetrofitUtil;
import com.zthl.nxp.model.request.LoginRequest;
import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.request.TransferCommitRequset;

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
    public Observable<ResultData<List<TurringList>>> getTurringListResponseInfo(MachineRequest simpleRequest){     //获取所有转换员
        return mRetrofitService.getTurringListResponseInfo(simpleRequest);
    }
    public Observable<ResultData<List<TurringList>>> getHistoryResponseInfo(SimpleRequest simpleRequest){
        return mRetrofitService.getHistoryResponseInfo(simpleRequest);
    }
        public Observable<ResultNoData> getCreateInvoicesResponseInfo(CreateInvoicesPointRequset dataRequest) {
            return mRetrofitService.getCreateInvoicesResponseInfo(dataRequest);
        }
            public Observable<ResultData<List<MachWorkingStatusByMachNameResponseBody>>> getMachWorkingStatusByMachNameResponseInfo(MachineRequest dataRequest){
                return mRetrofitService.getMachWorkingStatusByMachNameResponseInfo(dataRequest);

    }

    public Observable<ResultData<List<InvoiceList>>> getInvoiceListResponseInfo(InvoiceListRequest invoiceListRequest){
        return mRetrofitService.getInvoiceListResponseInfo(invoiceListRequest);
    }
    public Observable<ResultData<List<MachineListResponseBody>>> getMachineListResponseInfo(SimpleRequest simpleRequest){   //获取所有设备列表
        return mRetrofitService.getMachineListResponseInfo(simpleRequest);
    }

    //获取所有程序列表
    public Observable<ResultData<List<ProgramList>>> getProgramListResponseInfo(SimpleRequest simpleRequest){   //获取所有设备列表
        return mRetrofitService.getProgramListResponseInfo(simpleRequest);
    }




    //处理开票代办
    public Observable<ResultNoData> getAedInvoicesLogResponseInfo(AedInvoicesLogRequest aedInvoicesLogRequest){   //获取所有设备列表
        return mRetrofitService.getAedInvoicesLogResponseInfo(aedInvoicesLogRequest);
    }

    //处理转机代办
    public Observable<ResultNoData> getAedTransformLogResponseInfo(AedTransformLogRequest aedTransformLogRequest){   //获取所有设备列表
        return mRetrofitService.getAedTransformLogResponseInfo(aedTransformLogRequest);
    }



    //处理转机代办
    public Observable<ResultData<List<TurringList>>> getPersonalListResponseInfo(PersonalListRequest personalListRequest) {   //获取所有设备列表
        return mRetrofitService.getPersonalListResponseInfo(personalListRequest);
    }




        public Observable<GetServerDateTimeResponseBody> getServerDateTimeResponseInfo(SimpleRequest s){   //获取所有设备列表
            return mRetrofitService.getServerDateTimeResponseInfo(s);
    }



    public Observable<ResultData<List<InvoicesType>>> getInvoicesTypeResponseInfo(SimpleRequest s){   //获取所有设备列表
        return mRetrofitService.getInvoicesTypeResponseInfo(s);
    }
}
