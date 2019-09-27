package com.zthl.nxp.retrofit;

import com.zthl.nxp.model.LoginRequest;
import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.SimpleRequest;
import com.zthl.nxp.model.TransferCommitRequset;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.TurnaroundManListRequest;
import com.zthl.nxp.model.TurringList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitApiService {
    @Headers({"Content-Type: application/json","Accept: application/json"})//登陆
     @POST("api/Login")
    Observable<LoginResponseBody> getLoginResponseInfo(@Body LoginRequest loginRequest);



    @Headers({"Content-Type: application/json","Accept: application/json"})//创建转机
    @POST("api/CreatTransform")
    Observable<ResultNoData> getTransferCommitResponseInfo(@Body TransferCommitRequset transferCommitRequset);

    @Headers({"Content-Type: application/json","Accept: application/json"})//获取所有转换员
    @POST("Api/TurnaroundManList")
    Observable<ResultData<List<TurnaroundManList>>> getTurnaroundManListResponseInfo(@Body TurnaroundManListRequest transferCommitRequset);



    @Headers({"Content-Type: application/json","Accept: application/json"})//获取所有转机任务
    @POST("Api/TurringList")
    Observable<ResultData<List<TurringList>>> getTurringListResponseInfo(@Body SimpleRequest simpleRequest);
}
