package com.zthl.nxp.ui.transfer;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.nxp.R;
import com.wrbug.editspinner.EditSpinner;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.model.MachWorkingStatusByMachNameResponseBody;
import com.zthl.nxp.model.MachineListResponseBody;
import com.zthl.nxp.model.MachineRequest;
import com.zthl.nxp.model.ProgramList;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.model.request.TurnaroundManListRequest;
import com.zthl.nxp.presenter.MachWorkingStatusByMachNameResponseBodyPresenter;
import com.zthl.nxp.presenter.MachineListResponseBodyPresenter;
import com.zthl.nxp.presenter.ProgramListResponseBodyPresenter;
import com.zthl.nxp.presenter.TurnaroundManListResponseBodyPresenter;
import com.zthl.nxp.presenterView.MachWorkingStatusByMachNameResponsePv;
import com.zthl.nxp.presenterView.MachineListResponsePv;
import com.zthl.nxp.presenterView.ProgramListResponsePv;
import com.zthl.nxp.presenterView.TurnaroundManListResponsePv;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.ui.main.MainViewModel;
import com.zthl.nxp.utils.TimeUtil;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.request.TransferCommitRequset;
import com.zthl.nxp.model.TurningPoint;
import com.zthl.nxp.presenter.TransferCommitResponseBodyPresenter;
import com.zthl.nxp.presenterView.TransferCommitResponsePv;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransferCommitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransferCommitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransferCommitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView currentTime;
    private MainViewModel mainViewModel;
    private OnFragmentInteractionListener mListener;
    private Button submit;
    private TransferCommitResponseBodyPresenter tcr =new TransferCommitResponseBodyPresenter(getContext());
    private TurnaroundManListResponseBodyPresenter tmr=new TurnaroundManListResponseBodyPresenter(getContext());
    private MachineListResponseBodyPresenter mbp=new MachineListResponseBodyPresenter(getContext());
    private MachWorkingStatusByMachNameResponseBodyPresenter mwrp=new MachWorkingStatusByMachNameResponseBodyPresenter(getContext());
    private ProgramListResponseBodyPresenter prlrbp=new ProgramListResponseBodyPresenter(getContext());

    private EditSpinner transferSpinner;

    private EditSpinner machineListSpinner;
    private String accont;
    private TextView group;
    private TextView operator;
    private TextView currentProgram;
    private EditSpinner programSpinner;
    private String groupID=new String();
   private static List<TurnaroundManList> turnaroundManList=new ArrayList<>();
    private  static int selectMan=0;

    private  static  ArrayList<String> machineListItems=new ArrayList<String>();


    private static ArrayList<String> programListItems=new ArrayList<String>();
    public TransferCommitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransferCommitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransferCommitFragment newInstance(String param1, String param2) {
        TransferCommitFragment fragment = new TransferCommitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static TransferCommitFragment newInstance() {

        Bundle args = new Bundle();

        TransferCommitFragment fragment = new TransferCommitFragment();
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
        initReuqest();
        return inflater.inflate(R.layout.fragment_transfer_commit, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        tcr.onCreate();
        tmr.onCreate();
        mbp.onCreate();
        mwrp.onCreate();
        prlrbp.onCreate();
        tcr.BindPresentView(mUserInfoPv);
        tmr.BindPresentView(turnaroundManListResponsePv);
        mbp.BindPresentView(machineListResponsePv);
        mwrp.BindPresentView(machWorkingStatusByMachNameResponsePv);
        prlrbp.BindPresentView(programListResponsePv);
        currentTime=getActivity().findViewById(R.id.current_time);
        submit=getActivity().findViewById(R.id.transfer_commit_submit);
        submit.setVisibility(View.INVISIBLE);
        transferSpinner=getActivity().findViewById(R.id.transfer_man_spinner);
        transferSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectMan=i;
            }

        });
        TextView account=getActivity().findViewById(R.id.operator);
        TimeUtil.setServerTime(getContext(),currentTime);
      //  currentTime.setText(TimeUtil.getCurrentTime());
        account.setText(accont);
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransferCommitRequset t=new TransferCommitRequset();
                TurningPoint tp=new TurningPoint();


                if(machineListSpinner.getText().equals(""))
                    tp.setMachineNumber("");
                else {
                    tp.setMachineNumber(machineListSpinner.getText());
                }
                if(transferSpinner.getText().equals(""))
                    tp.setTurnaroundMan("");
                else {
                    tp.setTurnaroundMan(turnaroundManList.get(selectMan).getPkId());
                }

                tp.setGrouping(groupID);
                tp.setOperator(operator.getText().toString());
                tp.setCurrentName(currentProgram.getText().toString());
                tp.setTargetProgram(programSpinner.getText().toString());
                tp.setBillingtime(currentTime.getText().toString());
                t.setAccountPkId(MyApplication.getPkId());
                t.setTurningPoint(tp);
                Log.d("2333300",t.toString());

                tcr.getTransferCommitResponseInfo(t);
            }
        });
    }
    private  void initData(){
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("data", Context .MODE_PRIVATE);
        String userId=sharedPreferences.getString("account","");
        accont=userId;
    }
    private void initReuqest(){
//        machineListItems.clear();
//        programListItems.clear();
        TurnaroundManListRequest t=new TurnaroundManListRequest();
        t.setAccountPkId(MyApplication.getPkId());
        tmr.getTurnaroundManListResponseInfo(t);


        SimpleRequest s=new SimpleRequest();
        s.setAccountPkId(MyApplication.getPkId());
        mbp.getMachineListResponseInfo(s);

        SimpleRequest s1=new SimpleRequest();
        s1.setAccountPkId(MyApplication.getPkId());
        prlrbp.getProgramListResponseInfo(s1);


    }

    private void initView(){
      group=getActivity().findViewById(R.id.group);
      operator=getActivity().findViewById(R.id.operator);
        currentProgram=getActivity().findViewById(R.id.current_program);
        programSpinner =getActivity().findViewById(R.id.program_spinner);
        machineListSpinner=getActivity().findViewById(R.id.machine_list_spinner);

        machineListSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MachineRequest m=new MachineRequest();
                m.setAccountPkId(MyApplication.getPkId());
                m.setMachineNumber(machineListSpinner.getText());
                Log.d("machineListItems",machineListItems.get(i));
                if (machineListItems.get(i).equals(""))
                {
                    group.setText("");
                    operator.setText("");
                    currentProgram.setText("");
                }else
                {
                    mwrp.getMachWorkingStatusByMachNameResponseInfo(m);
                    Log.d("qqqqq",m.getMachineNumber()+"----"+m.getAccountPkId());
                }
            }

        });
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
    private TransferCommitResponsePv mUserInfoPv = new TransferCommitResponsePv(){
        @Override
        public void onError(String result) {
            Toast.makeText(getContext(),result, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(ResultNoData resultNet) {
        //    JSONObject jsonData = JSONObject.fromObject(school);
      //      System.out.println(jsonData);
            Log.d("2333300",resultNet.toString());
            if (resultNet.getState().equals("1"))
            {
                Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_LONG).show();

                                getFragmentManager()
                                     .beginTransaction()
                                     .addToBackStack(null)  //将当前fragment加入到返回栈中
                                     .replace(R.id.container, MainFragment.newInstance()).commit();
            }
            else
            {if (resultNet.getAlertMessage()!=null)
                Toast.makeText(getActivity(),resultNet.getAlertMessage(),Toast.LENGTH_LONG).show();
            }
        }


        };

    private TurnaroundManListResponsePv turnaroundManListResponsePv = new TurnaroundManListResponsePv(){

        @Override
        public void onSuccess(final ResultData<List<TurnaroundManList>> resultNet) {


            turnaroundManList =resultNet.getData();
            final List<String> spinnerItems=new ArrayList<>();
            for (int i=0;i<turnaroundManList.size();i++)
            {spinnerItems.add(turnaroundManList.get(i).getRealName());
            }

            TurnaroundManList t=new TurnaroundManList();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //自定义选择填充后的字体样式
                    //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_select, spinnerItems);
                    //这个在不同的Theme下，显示的效果是不同的
                    //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
            //        transferSpinner.setAdapter(spinnerAdapter);

                    transferSpinner.setItemData(spinnerItems);

                }
            });
        }

        @Override
        public void onError(String result) {
            Log.d("111111",result.toString());
        }

    };




    private MachineListResponsePv machineListResponsePv = new MachineListResponsePv(){

        @Override
        public void onError(String result) {

                Log.d("111111",result);


        }

        @Override
        public void onSuccess( final ResultData<List<MachineListResponseBody>> resultData) {    //渲染设备列表

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    Log.d("111111",resultData.getData().size()+"");
                    machineListItems.clear();
                    for (int i=0;i<resultData.getData().size();i++)
                    {machineListItems.add(resultData.getData().get(i).getName());
                    }

         //           ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_select, machineListItems);
            //        machineListSpinner.setAdapter(spinnerAdapter);
                    machineListSpinner.setItemData(machineListItems);
                }
            });
        }
    };
    private MachWorkingStatusByMachNameResponsePv machWorkingStatusByMachNameResponsePv = new MachWorkingStatusByMachNameResponsePv(){


        @Override
        public void onError(String result) {

        }

        @Override
        public void onSuccess(ResultData<List<MachWorkingStatusByMachNameResponseBody>> resultData) {
            Log.d("qqqqq",resultData.getData().toString());
            if (resultData.getState()!=0) {
                groupID=resultData.getData().get(0).getMatchGroupId();
                group.setText(resultData.getData().get(0).getMatchGroupName());
                operator.setText(resultData.getData().get(0).getOperator());
                currentProgram.setText(resultData.getData().get(0).getCurrentProgram());

            }
            if (resultData.getState()==0) {
                group.setText("");
                operator.setText("");
                currentProgram.setText("");

            }


        }
    };


    private ProgramListResponsePv programListResponsePv = new ProgramListResponsePv(){
        @Override
        public void onError(String result) {

            Log.d("111111",result);


        }

        @Override
        public void onSuccess( final ResultData<List<ProgramList>> programData) {    //获取所有程序

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    programListItems.clear();
                    Log.d("22222223",programData.getData().size()+"");

                    for (int i=0;i<programData.getData().size();i++)
                    {programListItems.add(programData.getData().get(i).getName());
                    }
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_select, programListItems);


                    programSpinner.setItemData(programListItems);
                    submit.setVisibility(View.VISIBLE);
                }
            });
        }
    };
}

