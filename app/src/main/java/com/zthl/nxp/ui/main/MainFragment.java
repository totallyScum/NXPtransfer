package com.zthl.nxp.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.zthl.nxp.MyApplication;
import com.zthl.nxp.ui.check.CheckStartFragment;
import com.zthl.nxp.ui.AutomaticBarcodeActivity;
import com.zthl.nxp.ui.missionList.MissionListFragment;
import com.zthl.nxp.ui.history.HistoryFragment;
import com.chen.nxp.R;
import com.zthl.nxp.ui.login.LoginActivity;
import com.zthl.nxp.ui.check.CheckListFragment;
import com.zthl.nxp.ui.transfer.TransferCommitFragment;
import com.zthl.nxp.ui.transfer.TransferFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFragment extends Fragment {
    private MainViewModel mViewModel;
    private GridView mainMenuList;
    private GridView mainMenuListCheck;
    private GridView mainMenuListSecondary;
    private static boolean isCheck=false;
    private static boolean isFixed=false;

    public static boolean isIsFixed() {
        return isFixed;
    }

    public static void setIsFixed(boolean isFixed) {
        MainFragment.isFixed = isFixed;
    }
    public static boolean check =false;

    private String[] theme = {"当前机器任务(机台号)", "开票开始界面", "任务列表", "已发放任务", "转机结束", "注销", "开票（开始界面）", "开票（ 结束界面）", "历史查看", "问题恢复", "问题反馈", "扫码", "提交转机"};
    private String[] themeFixed = {"提交转机", "转机", "我的列表", "历史", "注销"};
    public static String[] themeCheck = {"清洁", "保养", "测试"};
    private String[] themeSecondary = {"开票开始", "开票结束", "扫码","开票列表"};



    private int[] imageViews = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] imageViewsFixed = {R.mipmap.main_icon_transfer_commit, R.mipmap.main_icon_transfer, R.mipmap.main_icon_mission_list, R.mipmap.main_icon_history, R.mipmap.main_icon_login_out};
    private int[] imageViewsCheck = {R.mipmap.main_check_clean, R.mipmap.main_check_maintain, R.mipmap.main_icon_test};
    private int[] imageViewsSecondary = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher};


    private String[] themeLeader = {"提交转机",  "历史", "注销"};
    private String[] themeTransfer = { "转机", "我的列表",  "历史", "注销"};
    private int[] imageViewsLeader = {R.mipmap.main_icon_transfer_commit, R.mipmap.main_icon_history, R.mipmap.main_icon_login_out};
    private int[] imageViewsTransfer = {R.mipmap.main_icon_transfer_commit, R.mipmap.main_icon_mission_list, R.mipmap.main_icon_history, R.mipmap.main_icon_login_out};


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        initGridLayout();
        // TODO: Use the ViewModel
    }

    public boolean isCheck() {
        return isCheck;
    }

    public static void setCheck(boolean check) {
        isCheck = check;
    }

    public void initGridLayout() {
        mainMenuList = getView().findViewById(R.id.main_menu_list_fixed);
        mainMenuListCheck = getView().findViewById(R.id.main_menu_list_check);
        mainMenuListSecondary = getView().findViewById(R.id.main_menu_list_secondary);


        if (MyApplication.getRole().equals("1"))
        {
            mainMenuListCheck.setVisibility(View.INVISIBLE );


        }




        List<Map<String, Object>> listsFix = new ArrayList<>();
        for (int i = 0; i < themeFixed.length; i++) {       //固定布局
            Map<String, Object> mapFix = new HashMap<>();
            mapFix.put("image", imageViewsFixed[i]);
            mapFix.put("theme", themeFixed[i]);
            listsFix.add(mapFix);
        }



        List<Map<String, Object>> listsLeader = new ArrayList<>();
        for (int i = 0; i < themeLeader.length; i++) {       //固定布局
            Map<String, Object> mapFix = new HashMap<>();
            mapFix.put("image", imageViewsLeader[i]);
            mapFix.put("theme", themeLeader[i]);
            listsLeader.add(mapFix);
        }




        List<Map<String, Object>> listsTransfer = new ArrayList<>();
        for (int i = 0; i < themeTransfer.length; i++) {       //固定布局
            Map<String, Object> mapFix = new HashMap<>();
            mapFix.put("image", imageViewsTransfer[i]);
            mapFix.put("theme", themeTransfer[i]);
            listsTransfer.add(mapFix);
        }




        List<Map<String, Object>> listsCheck = new ArrayList<>();
        for (int i = 0; i < themeCheck.length; i++) {     //开票
            Map<String, Object> mapCheck = new HashMap<>();
            mapCheck.put("image", imageViewsCheck[i]);
            mapCheck.put("theme", themeCheck[i]);
            listsCheck.add(mapCheck);



        }


        List<Map<String, Object>> listsSecondary = new ArrayList<>();
        for (int i = 0; i < themeSecondary.length; i++) {    //二级
            Map<String, Object> mapSecondary = new HashMap<>();
            mapSecondary.put("image", imageViewsSecondary[i]);
            mapSecondary.put("theme", themeSecondary[i]);
            listsSecondary.add(mapSecondary);
        }


        if (MyApplication.getRole().equals("1"))
        {
            mainMenuList.setAdapter(new SimpleAdapter(getContext(), listsLeader, R.layout.gridview_item
                    , new String[]{"image", "theme"}
                    , new int[]{R.id.image1, R.id.text1}));
        }
        if (MyApplication.getRole().equals("2")){
            mainMenuList.setAdapter(new SimpleAdapter(getContext(), listsTransfer, R.layout.gridview_item
                    , new String[]{"image", "theme"}
                    , new int[]{R.id.image1, R.id.text1}));
        }
//        mainMenuList.setAdapter(new SimpleAdapter(getContext(), listsFix, R.layout.gridview_item
//                , new String[]{"image", "theme"}
//                , new int[]{R.id.image1, R.id.text1}));





        mainMenuListCheck.setAdapter(new SimpleAdapter(getContext(), listsCheck, R.layout.gridview_item
                , new String[]{"image", "theme"}
                , new int[]{R.id.image1, R.id.text1}));





        mainMenuListSecondary.setAdapter(new SimpleAdapter(getContext(), listsSecondary, R.layout.gridview_item
                , new String[]{"image", "theme"}
                , new int[]{R.id.image1, R.id.text1}));






//
        mainMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, MainFragment.newInstance())
//                        .commitNow();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (MyApplication.getRole().equals("1")) {
                    switch (i) {
                        case 0:
                            ft.replace(R.id.container, TransferCommitFragment.newInstance());
                            ft.addToBackStack("UserTag");
                            ft.commit();
                            break;

                        case 1:
                            ft.replace(R.id.container, HistoryFragment.newInstance());
                            ft.addToBackStack("UserTag");
                            ft.commit();
                            break;
                        case 2:
                            Intent intent2 = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent2);
                            break;

                    }
                }
                    if (MyApplication.getRole().equals("2")){
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(getActivity(), AutomaticBarcodeActivity.class);      //跳转到开票界面
                                Bundle b = new Bundle();
                                b.putInt("id", i);
                                intent.putExtras(b);
                                mViewModel.setFragmentID(i);
                                check = false;
                                getActivity().startActivity(intent);
                                break;

                            case 1:
                                ft.replace(R.id.container, MissionListFragment.newInstance());
                                ft.addToBackStack("UserTag");
                                ft.commit();
                                break;
                            case 2:
                                ft.replace(R.id.container, HistoryFragment.newInstance());
                                ft.addToBackStack("UserTag");
                                ft.commit();
                                break;
                            case 3:
                                Intent intent2 = new Intent(getContext(), LoginActivity.class);
                                startActivity(intent2);
                                break;

                        }
                    }


            }
        });


        mainMenuListCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, MainFragment.newInstance())
//                        .commitNow();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //        Bundle mBundle = new Bundle();

//                ft.replace(R.id.container, CheckStartFragment.newInstance());
//                ft.addToBackStack("UserTag");
//                ft.commit();
//                Intent intent=new Intent(this,AutomaticBarcodeActivity);
//                intent.putExtra("id",i);
//                startActivity(intent);

                Intent intent = new Intent(getActivity(), AutomaticBarcodeActivity.class);      //跳转到开票界面
                Bundle b = new Bundle();
                b.putInt("CheckID",i);
                intent.putExtras(b);
                mViewModel.setFragmentID(i);
                check=true;
                getActivity().startActivity(intent);
//
//                ft.replace(R.id.container, CheckListFragment.newInstance());
//                ft.addToBackStack("UserTag");
//                ft.commit();








//                switch (i) {
//                    case 0:
//                        ft.replace(R.id.container, CheckStartFragment.newInstance());
//                        ft.addToBackStack("UserTag");
//                        ft.commit();
//                        break;
//                    case 1:
//                        ft.replace(R.id.container, CheckEndFragment.newInstance());
//                        ft.addToBackStack("UserTag");
//                        ft.commit();
//                        break;
//                    case 2:
////                        ft.replace(R.id.container, CheckStartFragment.newInstance());
////                        ft.addToBackStack("UserTag");
////                        ft.commit();
//                        break;
//                }
            }
        });


        mainMenuListSecondary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, MainFragment.newInstance())
//                        .commitNow();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //        Bundle mBundle = new Bundle();
                switch (i) {
                    case 0:

                        ft.replace(R.id.container, CheckStartFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                        break;
                    case 1:


//                        ft.replace(R.id.container, CheckEndFragment.newInstance());
//                        ft.addToBackStack("UserTag");
//                        ft.commit();
                        break;
                    case 2:
//                        ft.replace(R.id.container, AutomaticBarcodeFragment.newInstance());
//                        ft.addToBackStack("UserTag");
//                        ft.commit();

                        Intent intent = new Intent(getActivity(), AutomaticBarcodeActivity.class);
                        startActivity(intent);

                        break;
                    case 3:

                        ft.replace(R.id.container, CheckListFragment.newInstance());
                        ft.commit();

                        break;


//                        Intent barcodeIntent = new Intent("android.intent.action.AUTOMATICBARCODEACTIVITY");
//                        startActivity(barcodeIntent);
//                        break;
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (isCheck == true) {
            isCheck=false;
            Intent intent = getActivity().getIntent();
            int id =intent.getIntExtra("checkID",0);
      //      int id = intent.getIntExtra("id", -1);
            String barCodeData = intent.getStringExtra("barCodeData");
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mViewModel.setBarCodeData(barCodeData);
            mViewModel.setFragmentID(id);
            ft.replace(R.id.container, CheckListFragment.newInstance());
            ft.addToBackStack("UserTag");
            ft.commit();
//            switch (id) {
//                case 0:           //  清洁
//                   // mViewModel.setFragmentID(0);
//                    ft.replace(R.id.container, CheckStartFragment.newInstance());
//                    ft.addToBackStack("UserTag");
//                    ft.commit();
//                    break;
//                case 1:
//              //      mViewModel.setFragmentID(1);
//                    ft.replace(R.id.container, CheckStartFragment.newInstance());
//                    ft.addToBackStack("UserTag");
//                    ft.commit();
//                    break;
//
//                case 2:
//                    mViewModel.setFragmentID(2);
//                    ft.replace(R.id.container, CheckStartFragment.newInstance());
//                    ft.addToBackStack("UserTag");
//                    ft.commit();
//                    break;
////            case 4:
////                break;
//            }
        }
        if (isFixed==true)
        {
            isFixed=false;
            Intent intent = getActivity().getIntent();
            int id =intent.getIntExtra("id",0);
            String barCodeData = intent.getStringExtra("barCodeData");
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mViewModel.setBarCodeData(barCodeData);
            mViewModel.setFragmentID(id);
switch (id){
    case 0:
//        ft.replace(R.id.container, TransferCommitFragment.newInstance());
//        ft.addToBackStack("UserTag");
//        ft.commit();
                        ft.replace(R.id.container, TransferFragment.newInstance());
                        ft.commit();
        break;
}
        }
    }
}
