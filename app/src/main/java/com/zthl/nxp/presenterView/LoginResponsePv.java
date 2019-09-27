package com.zthl.nxp.presenterView;

import com.zthl.nxp.model.LoginResponseBody;

/**
 * Created by 夏德旺 on 2017/12/8.
 */

public interface LoginResponsePv extends PresentView {
    //对基础接口PresentView进行扩展，添加onSuccess回调
    //因为该回调与具体的业务对应，所以不能写到基础接口里面
    //比如UserInfo的回调就创建一个UserInfoPv的接口，如果新增一个Order的业务，
    //则新增一个OrderPv的接口
    void onSuccess(LoginResponseBody loginResponseBody);
}
