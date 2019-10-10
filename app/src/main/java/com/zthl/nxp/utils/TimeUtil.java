package com.zthl.nxp.utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zthl.nxp.MyApplication;
import com.zthl.nxp.model.GetServerDateTimeResponseBody;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.presenter.GetServerDateTimeResponseBodyPresenter;
import com.zthl.nxp.presenter.ProgramListResponseBodyPresenter;
import com.zthl.nxp.presenterView.GetServerDateTimeResponsePv;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtil {

    static Calendar calendars;
    private static GetServerDateTimeResponseBodyPresenter prlrbp;
    private  static Context mContext;
    private static TextView mTextView;
    public static  void setServerTime(final Context context, final TextView textView){
        mContext=context;
        mTextView=textView;

        prlrbp=new GetServerDateTimeResponseBodyPresenter(context);
        prlrbp.onCreate();
        prlrbp.BindPresentView(g);
        SimpleRequest s1=new SimpleRequest();
        s1.setAccountPkId(MyApplication.getPkId());
        prlrbp.getServerDateTimeResponseInfo(s1);



    }
    static  GetServerDateTimeResponsePv g=new GetServerDateTimeResponsePv() {
        @Override
        public void onSuccess(GetServerDateTimeResponseBody resultNet) {
            mTextView.setText(resultNet.getDateTime());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(mContext,"获取时间失败！",Toast.LENGTH_LONG).show();
        }
    };


}
