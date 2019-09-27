package com.zthl.nxp.presenter;


import com.zthl.nxp.presenterView.PresentView;

/**
 */

public interface Presenter {
    //Presenter初始化
    void onCreate();
    //销毁
    void onDestroy();
    //绑定视图
    void BindPresentView(PresentView presentView);
}
