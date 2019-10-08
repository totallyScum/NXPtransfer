package com.zthl.nxp.ui.check;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.nxp.R;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.request.AedInvoicesLogRequest;
import com.zthl.nxp.presenter.AedInvoicesLogResponseBodyPresenter;
import com.zthl.nxp.presenter.CreatInvoicesResponseBodyPresenter;
import com.zthl.nxp.presenterView.AedInvoicesLogResponsePv;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.ui.main.MainViewModel;
import com.zthl.nxp.utils.TimeUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckEndFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckEndFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckEndFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView invoicesAccount;
    private TextView invoicesProgram;
    private TextView invoicesTime;
    private Button checkEndSubmit;





    private TextView invoiceMachineNumber;
    private TextView invoicesCurrentProgram,invoicesType;
    private EditText remark;
    private MainViewModel mainViewModel;


    private OnFragmentInteractionListener mListener;
    private AedInvoicesLogResponseBodyPresenter arbp=new AedInvoicesLogResponseBodyPresenter(getContext());
    public CheckEndFragment() {
        // Required empty public constructor
    }

    public static CheckEndFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CheckEndFragment fragment = new CheckEndFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckEndFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckEndFragment newInstance(String param1, String param2) {
        CheckEndFragment fragment = new CheckEndFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_end, container, false);
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
        checkEndSubmit=getActivity().findViewById(R.id.check_end_submit);


        invoiceMachineNumber=getActivity().findViewById(R.id.invoices_end_machine_number);
        invoicesCurrentProgram=getActivity().findViewById(R.id.invoices_current_program);
        remark=getActivity().findViewById(R.id.invoices_end_remark);
        invoicesCurrentProgram.setText(MyApplication.getProgram()!=null?MyApplication.getProgram():"程序名为空");
        invoiceMachineNumber.setText(MyApplication.getMachineNumber());
        invoicesAccount.setText(MyApplication.getPkId());


        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        invoicesType=getActivity().findViewById(R.id.invoices_end_type);
        invoicesType.setText(MyApplication.themeCheck[mainViewModel.getFragmentID()]);    //选择开票类型
        invoicesAccount.setText(MyApplication.getPkId());
 //       invoicesProgram.setText(MyApplication.getProgram()!=null?MyApplication.getProgram():"程序名为空");
        invoicesTime.setText(TimeUtil.getCurrentTime());
        checkEndSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AedInvoicesLogRequest a=new AedInvoicesLogRequest();
                a.setAccountPkId(MyApplication.getPkId());
                a.setLogId(MyApplication.getLogId());
                a.setInvoicesRemark("开票3");
                a.setNextState("3");
                arbp.getAedInvoicesLogResponseInfo(a);
            }
        });
    }
    public  void initData(){
        arbp.onCreate();
        arbp.BindPresentView(aedInvoicesLogResponsePv);
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
