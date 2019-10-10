package com.zthl.nxp.ui.check;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.nxp.R;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.model.request.CreateInvoicesPointRequset;
import com.zthl.nxp.model.request.DataRequest;
import com.zthl.nxp.model.InvoicesPoint;
import com.zthl.nxp.model.MachWorkingStatusByMachNameResponseBody;
import com.zthl.nxp.model.MachineRequest;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.presenter.CreatInvoicesResponseBodyPresenter;
import com.zthl.nxp.presenter.MachWorkingStatusByMachNameResponseBodyPresenter;
import com.zthl.nxp.presenterView.CreateInvoicesResponsePV;
import com.zthl.nxp.presenterView.MachWorkingStatusByMachNameResponsePv;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.ui.main.MainViewModel;
import com.zthl.nxp.utils.TimeUtil;

import java.util.List;


public class CheckStartFragment extends Fragment {
    private ListView mListView;
    private MainViewModel mainViewModel;
    private TextView checkMachineNumber ;   //机器码
   private TextView checkType; //开票类型\
    private Button commit;
    private  String userId;


    private TextView invoicesMan;
    private TextView operator;
    private TextView checkTime;
    private TextView currentProgram; //当前程序名
    private TextView matchGroupId; //设备分组


    private CreatInvoicesResponseBodyPresenter cir=new CreatInvoicesResponseBodyPresenter(getContext());
    private MachWorkingStatusByMachNameResponseBodyPresenter mbp=new MachWorkingStatusByMachNameResponseBodyPresenter(getContext());

    public static CheckStartFragment newInstance() {

        Bundle args = new Bundle();
        
        CheckStartFragment fragment = new CheckStartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check_start, container, false);
    //    return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUser();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cir.BindPresentView(createInvoicesResponsePV);
        mbp.BindPresentView(machWorkingStatusByMachNameResponsePv);
        cir.onCreate();
        mbp.onCreate();

        initView();


        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        checkMachineNumber=getView().findViewById(R.id.check_machine_number);
        checkType=getView().findViewById(R.id.check_type);
        if (mainViewModel!=null)
            checkMachineNumber.setText(mainViewModel.getBarCodeData());
        if (mainViewModel.getFragmentID()!=-1)
            checkType.setText(MainFragment.themeCheck[mainViewModel.getFragmentID()]);
        commit=getActivity().findViewById(R.id.check_start_submit);



        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateInvoicesPointRequset t=new CreateInvoicesPointRequset();
                InvoicesPoint ip=new InvoicesPoint();
                ip.setMachineNumber(mainViewModel.getBarCodeData());
                ip.setInvoicesMan(MyApplication.getPkId());
                ip.setGrouping(matchGroupId.getText().toString());
                ip.setOperator(operator.getText().toString());
                ip.setCurrentTime(currentProgram.getText().toString());
                ip.setInvoicesTime(checkTime.getText().toString());
                ip.setInvoicesTypeID(mainViewModel.getFragmentID()+1+"");
                t.setAccountPkId(MyApplication.getPkId());




                Log.d("211111",ip.toString());
                t.setData(ip);
                Log.d("211111",t.toString());
                cir.getCreateInvoicesResponseInfo(t);




        }
        });
    }
public void initUser(){
    SharedPreferences sharedPreferences= getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
     userId=sharedPreferences.getString("account","");
}
    @Override
    public void onResume() {
        super.onResume();
        initList();
        checkMachineNumber.setText(mainViewModel.getBarCodeData());
    }
    public void initView(){
        invoicesMan=getActivity().findViewById(R.id.invoicesMan);
        operator=getActivity().findViewById(R.id.operator);
        checkTime=getActivity().findViewById(R.id.check_time);
        currentProgram=getActivity().findViewById(R.id.currentProgram);
        matchGroupId=getActivity().findViewById(R.id.matchGroupId);
    }
public void initList(){
        MachineRequest m=new MachineRequest();
        m.setAccountPkId(MyApplication.getPkId());
        m.setMachineNumber(mainViewModel.getBarCodeData());
       Log.d("2134",mainViewModel.getBarCodeData().toString());
        mbp.getMachWorkingStatusByMachNameResponseInfo(m);


}
    private CreateInvoicesResponsePV createInvoicesResponsePV = new CreateInvoicesResponsePV(){
        @Override
        public void onError(String result) {
            Toast.makeText(getContext(),result, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(ResultNoData resultNet) {
            if (resultNet.getState().equals("1"))
            {
                Log.d("111111","2222222222222");
                Toast.makeText(getContext(),"提交成功",Toast.LENGTH_LONG).show();

                getFragmentManager()
                        .beginTransaction()
                //        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.container, CheckListFragment.newInstance()).commit();
            }
            if (resultNet.getState().equals("0"))
            {
                Log.d("111111","4444444444444");
                Toast.makeText(getContext(),"提交失败",Toast.LENGTH_LONG);
            }
        }


    };

    private MachWorkingStatusByMachNameResponsePv machWorkingStatusByMachNameResponsePv = new MachWorkingStatusByMachNameResponsePv(){


        @Override
        public void onError(String result) {
            Log.d("21345",result);
        }

        @Override
        public void onSuccess(final ResultData<List<MachWorkingStatusByMachNameResponseBody>> resultData) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences= getActivity().getSharedPreferences("data", Context .MODE_PRIVATE);
                String userId=sharedPreferences.getString("account","");
                currentProgram.setText(resultData.getData().get(0).getCurrentProgram());
                matchGroupId.setText(resultData.getData().get(0).getMatchGroupId());
                TimeUtil.setServerTime(getContext(),checkTime);
                invoicesMan.setText(userId);
                operator.setText(resultData.getData().get(0).getOperator());
            }
        });
            //    JSONObject jsonData = JSONObject.fromObject(school);
            //      System.out.println(jsonData);
//            if (resultData.getState()("1"))
//            {
//                Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_LONG).show();
//
//                getFragmentManager()
//                        .beginTransaction()
//                        .addToBackStack(null)  //将当前fragment加入到返回栈中
//                        .replace(R.id.container, CheckListFragment.newInstance()).commit();
//            }
//            if (resultData.getState().equals("0"))
//            {
//                Toast.makeText(getContext(),"提交失败",Toast.LENGTH_LONG);
//            }
        }


    };


}
