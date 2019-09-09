package com.chen.nxp.ui.main;

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

import com.chen.nxp.CheckEndFragment;
import com.chen.nxp.CheckStartFragment;
import com.chen.nxp.TransferEndFragment;
import com.chen.nxp.ui.missionList.MissionListFragment;
import com.chen.nxp.ProblemFeedbackFragment;
import com.chen.nxp.ProblemRecoveryFragment;
import com.chen.nxp.ui.history.HistoryFragment;
import com.chen.nxp.ui.mission.MissionFragment;
import com.chen.nxp.R;
import com.chen.nxp.ui.login.LoginActivity;
import com.zthl.nxp.AutomaticBarcodeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFragment extends Fragment {
    private MainViewModel mViewModel;
    private GridView mainMenuList;
    private String[] theme = {"当前机器任务(机台号)","开票开始界面","任务列表","已发放任务","转机结束","注销","开票（开始界面）","开票（ 结束界面）","历史查看" ,"问题恢复","问题反馈","扫码"};
         private int[] imageViews = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

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
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initGridLayout();
        // TODO: Use the ViewModel
    }
    public void initGridLayout(){
        mainMenuList=getView().findViewById(R.id.main_menu_list);
        List<Map<String,Object>> lists = new ArrayList<>(); for(int i = 0; i < theme.length; i++){
            Map<String,Object> map =new HashMap<>();
             map.put("image",imageViews[i]);
             map.put("theme",theme[i]);
                lists.add(map);
 }

        mainMenuList.setAdapter(new SimpleAdapter(getContext(),lists,R.layout.gridview_item
                ,new String[]{"image","theme"}
                 ,new int[]{R.id.image1,R.id.text1}));

        mainMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, MainFragment.newInstance())
//                        .commitNow();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //        Bundle mBundle = new Bundle();
                switch (i){
                    case 0:
                        ft.replace(R.id.container, MissionFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                        break;
                    case 1:
                        ft.replace(R.id.container, CheckStartFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                        break;
                    case 2:  //任务列表
                        ft.replace(R.id.container, MissionListFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                        break;
                    case 4:  //传输结束页面
                        ft.replace(R.id.container, TransferEndFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                        break;
                    case 5:
                    {
//                        ft.replace(R.id.container, LoginActivity.newInstance());
//                        ft.addToBackStack("UserTag");
//                        ft.commit();
                        Intent intent= new Intent(getContext(),LoginActivity.class);
                        startActivity(intent);
                    }
                    case 6:
                    {
                        ft.replace(R.id.container, CheckStartFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                    }
                        break;
                    case 7:
                        ft.replace(R.id.container, CheckEndFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                    break;
                    case 8:
                        ft.replace(R.id.container, HistoryFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                        break;
                    case 9:
                        ft.replace(R.id.container, ProblemFeedbackFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                        break;
                    case 10:
                        ft.replace(R.id.container, ProblemRecoveryFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                        break;
                    case 11:
                        ft.replace(R.id.container, AutomaticBarcodeFragment.newInstance());
                        ft.addToBackStack("UserTag");
                        ft.commit();
                        break;
                }
            }
        });
    }

}
