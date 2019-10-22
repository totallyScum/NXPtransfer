package com.zthl.nxp.ui.check;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.chen.nxp.R;
import com.zthl.nxp.MainActivity;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.request.AedInvoicesLogRequest;
import com.zthl.nxp.presenter.AedInvoicesLogResponseBodyPresenter;
import com.zthl.nxp.presenterView.AedInvoicesLogResponsePv;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.ui.main.MainViewModel;
import com.zthl.nxp.utils.TimeUtil;

public class CheckCancelFragment extends Fragment {

    private TextView invoicesAccount;
    private TextView invoicesProgram;
    private TextView invoicesTime;
    private TextView invoiceMachineNumber;
    private TextView invoicesCurrentProgram,invoicesType;
    private Button checkEndSubmit;
    private EditText remark;
    private MainViewModel mainViewModel;
    private AedInvoicesLogResponseBodyPresenter arbp=new AedInvoicesLogResponseBodyPresenter(getContext());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_cancel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    public  void initView(){
        invoicesAccount=getActivity().findViewById(R.id.invoices_account);
        invoicesProgram=getActivity().findViewById(R.id.invoices_program);
        invoicesTime=getActivity().findViewById(R.id.invoices_start_time);
        checkEndSubmit=getActivity().findViewById(R.id.check_cancel_submit);
        checkEndSubmit=getActivity().findViewById(R.id.check_cancel_submit);
        invoicesType=getActivity().findViewById(R.id.invoices_cancel_type);
        invoiceMachineNumber=getActivity().findViewById(R.id.invoices_cancel_machine_number);
        invoicesCurrentProgram=getActivity().findViewById(R.id.invoices_current_program);
        remark=getActivity().findViewById(R.id.invoices_cancel_remark);
        invoicesCurrentProgram.setText(MyApplication.getProgram()!=null?MyApplication.getProgram():"程序名为空");
        invoiceMachineNumber.setText(MyApplication.getMachineNumber());
        invoicesAccount.setText(MyApplication.getAccount());
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        invoicesType.setText(MainFragment.invoiceTypeName.get(MyApplication.getFragmentID()));    //选择开票类型
  //      invoicesProgram.setText(MyApplication.getProgram()!=null?MyApplication.getProgram():"程序名为空");
        TimeUtil.setServerTime(getContext(),invoicesTime);
     //   invoicesTime.setText(TimeUtil.getCurrentTime());
        checkEndSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AedInvoicesLogRequest a=new AedInvoicesLogRequest();
                a.setAccountPkId(MyApplication.getPkId());
                a.setLogId(MyApplication.getLogId());
                a.setInvoicesRemark(remark.getText().toString());
                a.setNextState("5");
                arbp.getAedInvoicesLogResponseInfo(a);
            }
        });
    }
    public  void initData(){
        arbp.onCreate();
        arbp.BindPresentView(aedInvoicesLogResponsePv);
    }

    public static CheckCancelFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CheckCancelFragment fragment = new CheckCancelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    AedInvoicesLogResponsePv aedInvoicesLogResponsePv=new AedInvoicesLogResponsePv() {
        @Override
        public void onSuccess(ResultNoData resultNoData) {
            if (resultNoData.getState().equals("1"))
            {
                Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_LONG).show();

                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.container, CheckListFragment.newInstance()).commit();
            }
            if (resultNoData.getState().equals("0"))
            {
                Toast.makeText(getContext(),"提交失败",Toast.LENGTH_LONG);
            }
        }

        @Override
        public void onError(String result) {
            Toast.makeText(getContext(),result, Toast.LENGTH_SHORT).show();
        }
    };
}
