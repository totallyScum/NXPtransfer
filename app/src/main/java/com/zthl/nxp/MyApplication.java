package com.zthl.nxp;

import android.content.Context;
import android.content.SharedPreferences;

import com.zthl.nxp.constant.UrlConstant;

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
    }
    private  void initIP(){

        SharedPreferences sharedPreferences= getSharedPreferences("data", Context.MODE_PRIVATE);
        String ip=sharedPreferences.getString("IP","http://123.206.51.39:20188/");
        if (ip.equals(""))
        UrlConstant.setBaseUrl("http://123.206.51.39:20188/");
    }
}
