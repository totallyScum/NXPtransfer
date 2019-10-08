package com.zthl.nxp.ui.transfer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.nxp.R;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.model.MachWorkingStatusByMachNameResponseBody;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.request.AedTransformLogRequest;
import com.zthl.nxp.presenter.AedInvoicesLogResponseBodyPresenter;
import com.zthl.nxp.presenter.AedTransformLogResponseBodyPresenter;
import com.zthl.nxp.presenter.ProgramListResponseBodyPresenter;
import com.zthl.nxp.presenterView.AedTransformLogResponsePv;
import com.zthl.nxp.presenterView.MachWorkingStatusByMachNameResponsePv;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.utils.TimeUtil;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransferStartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransferStartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransferStartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AedTransformLogResponseBodyPresenter alr=new AedTransformLogResponseBodyPresenter(getContext());
    private OnFragmentInteractionListener mListener;
    private Button transferStart;
    private EditText  transferStartRemark;
    private TextView sourceProgram;
    private TextView targetProgram;
    private TextView machineNumber;
    private TextView transferMan;
    private TextView transferTime,title;
    public TransferStartFragment() {
        // Required empty public constructor
    }

    public static TransferStartFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TransferStartFragment fragment = new TransferStartFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransferStartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransferStartFragment newInstance(String param1, String param2) {
        TransferStartFragment fragment = new TransferStartFragment();
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
        return inflater.inflate(R.layout.fragment_transfer_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        alr.onCreate();
        alr.BindPresentView(aedTransformLogResponseBodyPresenter);
        transferStart=getActivity().findViewById(R.id.transfer_start_submit);
        transferStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AedTransformLogRequest a=new AedTransformLogRequest();
                a.setAccountPkId(MyApplication.getPkId());
                a.setLogId(MyApplication.getLogId());
                a.setTurningRemark(transferStartRemark.getText().toString());
                a.setNextState(MyApplication.getTurningState());
                alr.getAedTransformLogResponseInfo(a);
            }
        });
    }
    public void initView(){
        title=getActivity().findViewById(R.id.transfer_title);
        transferStartRemark=getActivity().findViewById(R.id.transfer_start_remark);
        sourceProgram=getActivity().findViewById(R.id.transfer_start_source_program);
        targetProgram=getActivity().findViewById(R.id.transfer_start_target_program);
        machineNumber=getActivity().findViewById(R.id.transfer_start_machineNumber);
        transferMan=getActivity().findViewById(R.id.transfer_start_founder);
        transferTime=getActivity().findViewById(R.id.transfer_start_time);
        sourceProgram.setText(MyApplication.getSourceProgram());
        targetProgram.setText(MyApplication.getTargetProgram());
        machineNumber.setText(MyApplication.getMachineNumber());
        transferMan.setText(MyApplication.getAccount());
//        transferTime.setText(TimeUtil.getCurrentTime());
        TimeUtil.setServerTime(getContext(),transferTime);
            initTitle();
    }
    private void initTitle(){
        switch (MyApplication.getTurningState()){
            case "3":{
                title.setText("开始转机");
                break;
            }
            case "4":{
                title.setText("结束转机");
                break;
            }
            case "5":{
                title.setText("报修");
                break;
            }
            case "6":{
                title.setText("故障恢复");
                break;
            }
            case "7":{
                title.setText("取消");
                break;
            }
        }
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


    @Override
    public void onStop() {
        super.onStop();
    }

    private AedTransformLogResponsePv aedTransformLogResponseBodyPresenter = new AedTransformLogResponsePv(){


        @Override
        public void onSuccess(ResultNoData resultNoData) {
            if (resultNoData.getState().equals("1"))
            {
                Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_LONG).show();

                getFragmentManager()
                        .beginTransaction()
//                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.container, MainFragment.newInstance()).commit();
            }
        }

        @Override
        public void onError(String result) {

            Toast.makeText(getActivity(),"提交失败"+result.toString(),Toast.LENGTH_LONG).show();


        }

    };
}
