package com.zthl.nxp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;
import com.xuexiang.xupdate.utils.UpdateUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zthl.nxp.constant.UrlConstant;
import com.zthl.nxp.utils.OKHttpUpdateHttpService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.xuexiang.xupdate.entity.UpdateError.ERROR.CHECK_NO_NEW_VERSION;

public class MyApplication extends android.app.Application {
    public static String[] themeCheck = {"清洁", "保养", "测试"};
    public static String account;
    public static String pkId;
    public static String program;
    public static String logId;
    public static String machineNumber;
    public static String targetProgram;
    public static String sourceProgram;
    public static String TurningState;
    public static String role;

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        MyApplication.role = role;
    }

    public static String[] getThemeCheck() {
        return themeCheck;
    }

    public static void setThemeCheck(String[] themeCheck) {
        MyApplication.themeCheck = themeCheck;
    }

    public static String getTurningState() {
        return TurningState;
    }

    public static void setTurningState(String turningState) {
        TurningState = turningState;
    }

    public static String getMachineNumber() {
        return machineNumber;
    }

    public static void setMachineNumber(String machineNumber) {
        MyApplication.machineNumber = machineNumber;
    }

    public static String getTargetProgram() {
        return targetProgram;
    }

    public static void setTargetProgram(String targetProgram) {
        MyApplication.targetProgram = targetProgram;
    }

    public static String getSourceProgram() {
        return sourceProgram;
    }

    public static void setSourceProgram(String sourceProgram) {
        MyApplication.sourceProgram = sourceProgram;
    }

    public static String getProgram() {
        return program;
    }

    public static void setProgram(String program) {
        MyApplication.program = program;
    }

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        MyApplication.account = account;
    }

    public static String getPkId() {
        return pkId;
    }

    public static void setPkId(String pkId) {
        MyApplication.pkId = pkId;
    }

    public static String getLogId() {
        return logId;
    }

    public static void setLogId(String logId) {
        MyApplication.logId = logId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initIP();

        initOKHttpUtils();

        initUpdate();
    }
    private void forceUpdate(){
        XUpdate.newBuild(getApplicationContext())
                .updateUrl("http://123.206.51.39:20188/APPUpdate/version-info.json")
                .update();
    }
    private  void initIP(){

        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        String ip=sharedPreferences.getString("IP","http://123.206.51.39:20188/");
        if (ip.equals(""))
        UrlConstant.setBaseUrl("http://123.206.51.39:20188/");
    }
    private void initUpdate(){
        XUpdate.get()
                .debug(true)
                .isWifiOnly(true)                                               //默认设置只在wifi下检查版本更新
                .isGet(true)                                                    //默认设置使用get请求检查版本
                .isAutoMode(false)                                              //默认设置非自动模式，可根据具体使用配置
                .param("versionCode", UpdateUtils.getVersionCode(this))         //设置默认公共请求参数
                .param("appKey", getPackageName())
                .setOnUpdateFailureListener(new OnUpdateFailureListener() {     //设置版本更新出错的监听
                    @Override
                    public void onFailure(UpdateError error) {
                        if (error.getCode() != CHECK_NO_NEW_VERSION) {          //对不同错误进行处理
                   //         ToastUtils.toast(error.toString());
                            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .supportSilentInstall(true)                                     //设置是否支持静默安装，默认是true
                .setIUpdateHttpService(new OKHttpUpdateHttpService())           //这个必须设置！实现网络请求功能。
                .init(this);
    }
    private void initOKHttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
