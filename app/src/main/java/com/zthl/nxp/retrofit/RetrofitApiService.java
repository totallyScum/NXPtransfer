package com.zthl.nxp.retrofit;

import com.zthl.nxp.model.GetServerDateTimeResponseBody;
import com.zthl.nxp.model.InvoiceList;
import com.zthl.nxp.model.MachineListResponseBody;
import com.zthl.nxp.model.ProgramList;
import com.zthl.nxp.model.request.AedInvoicesLogRequest;
import com.zthl.nxp.model.request.AedTransformLogRequest;
import com.zthl.nxp.model.request.CreateInvoicesPointRequset;
import com.zthl.nxp.model.request.DataRequest;
import com.zthl.nxp.model.InvoicesPoint;
import com.zthl.nxp.model.request.InvoiceListRequest;
import com.zthl.nxp.model.request.LoginRequest;
import com.zthl.nxp.model.LoginResponseBody;
import com.zthl.nxp.model.MachWorkingStatusByMachNameResponseBody;
import com.zthl.nxp.model.MachineRequest;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.request.PersonalListRequest;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.model.request.TransferCommitRequset;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.request.TurnaroundManListRequest;
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
    Observable<ResultData<List<TurringList>>> getTurringListResponseInfo(@Body MachineRequest machineRequest);

    @Headers({"Content-Type: application/json","Accept: application/json"})//获取所有转机任务
    @POST("Api/HistoryList")
    Observable<ResultData<List<TurringList>>> getHistoryResponseInfo(@Body SimpleRequest simpleRequest);

    @Headers({"Content-Type: application/json","Accept: application/json"})//新建开票任务
@POST("api/CreatInvoices")
    Observable<ResultNoData> getCreateInvoicesResponseInfo(@Body CreateInvoicesPointRequset dataRequest);


    @Headers({"Content-Type: application/json","Accept: application/json"})//设备当前运行状态
    @POST("Api/GetMachWorkingStatusByMachName")
    Observable<ResultData<List<MachWorkingStatusByMachNameResponseBody>>> getMachWorkingStatusByMachNameResponseInfo(@Body MachineRequest dataRequest);


    @Headers({"Content-Type: application/json","Accept: application/json"})//设备当前运行状态
    @POST("Api/InvoiceList")
    Observable<ResultData<List<InvoiceList>>> getInvoiceListResponseInfo(@Body InvoiceListRequest invoiceListRequest);



    @Headers({"Content-Type: application/json","Accept: application/json"})//设备当前运行状态
    @POST("Api/TurringList")
    Observable<ResultData<List<TurringList>>> getTurringListResponseInfo(@Body InvoiceListRequest invoiceListRequest);

    @Headers({"Content-Type: application/json","Accept: application/json"})//设备当前运行状态
    @POST("Api/MachineList")
    Observable<ResultData<List<MachineListResponseBody>>> getMachineListResponseInfo(@Body SimpleRequest simpleRequest);

    @Headers({"Content-Type: application/json","Accept: application/json"})//获取所有程序列表
    @POST("Api/ProgramList")
    Observable<ResultData<List<ProgramList>>> getProgramListResponseInfo(@Body SimpleRequest simpleRequest);

    @Headers({"Content-Type: application/json","Accept: application/json"})//处理开票待办
    @POST("api/AedInvoicesLog")
    Observable<ResultNoData> getAedInvoicesLogResponseInfo(@Body AedInvoicesLogRequest aedInvoicesLogRequest);



    @Headers({"Content-Type: application/json","Accept: application/json"})//处理转机待办
    @POST("api/AedTransformLog")
    Observable<ResultNoData> getAedTransformLogResponseInfo(@Body AedTransformLogRequest aedTransformLogRequest);






    @Headers({"Content-Type: application/json","Accept: application/json"})//处理转机待办
    @POST("Api/PersonalList")
    Observable<ResultData<List<TurringList>>> getPersonalListResponseInfo(@Body PersonalListRequest personalListRequest);




    @Headers({"Content-Type: application/json","Accept: application/json"})//获取服务器时间
    @POST("Api/GetServerDateTime")
    Observable<GetServerDateTimeResponseBody> getServerDateTimeResponseInfo(@Body SimpleRequest simpleRequest);

}
