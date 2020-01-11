package com.zthl.nxp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chen.nxp.R;
import com.wrbug.editspinner.EditSpinner;
import com.zthl.nxp.model.MachineListResponseBody;
import com.zthl.nxp.model.ProgramList;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.model.request.PersonalListRequest;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.model.request.TurnaroundManListRequest;
import com.zthl.nxp.presenter.MachineListResponseBodyPresenter;
import com.zthl.nxp.presenter.PersonalListResponseBodyPresenter;
import com.zthl.nxp.presenter.ProgramListResponseBodyPresenter;
import com.zthl.nxp.presenter.TurnaroundManListResponseBodyPresenter;
import com.zthl.nxp.presenterView.MachineListResponsePv;
import com.zthl.nxp.presenterView.PersonalListResponsePv;
import com.zthl.nxp.presenterView.ProgramListResponsePv;
import com.zthl.nxp.presenterView.TurnaroundManListResponsePv;
import com.zthl.nxp.ui.SlideDeleteListviewFragment;
import com.zthl.nxp.ui.datepicker.CustomDatePicker;
import com.zthl.nxp.ui.datepicker.DateFormatUtils;
import com.zthl.nxp.ui.main.MainViewModel;
import com.zthl.nxp.ui.missionList.MissionListFragment;
import com.zthl.nxp.ui.transfer.SlideTransferCancelFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QueryFactorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QueryFactorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryFactorFragment extends Fragment   {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private ProgramListResponseBodyPresenter prlrbp=new ProgramListResponseBodyPresenter(getContext());
    private MachineListResponseBodyPresenter mbp=new MachineListResponseBodyPresenter(getContext());
    private TurnaroundManListResponseBodyPresenter tmr=new TurnaroundManListResponseBodyPresenter(getContext());
    private PersonalListResponseBodyPresenter prb=new PersonalListResponseBodyPresenter(getContext());


    private static List<TurnaroundManList> turnaroundManList=new ArrayList<>();
    private  static  ArrayList<String> machineListItems=new ArrayList<String>();
    private static ArrayList<String> programListItems=new ArrayList<String>();

    private TextView startTime, mEndTime;
    private CustomDatePicker mStartTimePicker, mEndTimePicker;
    private Button submit;
    private LinearLayout llStartTime,llEndTime;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    private OnFragmentInteractionListener mListener;
    private Button queryCommitSubmit;

    private EditSpinner programSpinner;
    private EditSpinner transferSpinner;
    private EditSpinner machineListSpinner;
    private EditText search;
     final ArrayList<String> turnaroundManRealName=new ArrayList<>();
    final ArrayList<String> turnaroundManID=new ArrayList<>();


    private MainViewModel mViewModel;
    private  static int selectMan=0;
    public QueryFactorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueryFactorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QueryFactorFragment newInstance(String param1, String param2) {
        QueryFactorFragment fragment = new QueryFactorFragment();
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

    public static QueryFactorFragment newInstance() {
        
        Bundle args = new Bundle();
        
        QueryFactorFragment fragment = new QueryFactorFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_query_factor, container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        machineListItems.clear();
        programListItems.clear();
        startTime=getActivity().findViewById(R.id.startTime);
        mEndTime=getActivity().findViewById(R.id.endTime);
         llStartTime=(LinearLayout) view.findViewById(R.id.ll_start_time);
        llEndTime=(LinearLayout) view.findViewById(R.id.ll_end_time);
        queryCommitSubmit=getActivity().findViewById(R.id.query_submit);
        search=getActivity().findViewById(R.id.search);
        llStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mStartTimePicker.show(startTime.getText().toString());
            }
        });
        llStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mStartTimePicker.show(startTime.getText().toString());
            }
        });
        llEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEndTimePicker.show(mEndTime.getText().toString());
            }
        });
        //       getActivity().findViewById(R.id.ll_end_time).setOnClickListener(this);
        initTimerPicker();


        queryCommitSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.mission_container, QueryFactorFragment.newInstance());
//                //   ft.addToBackStack("UserTag");
//                ft.commit();
                PersonalListRequest p=new PersonalListRequest();
                p.setTargetProgram(programSpinner.getText().toString());
                p.setAccountPkId(MyApplication.getPkId());
                p.setFounder(MyApplication.getPkId());
                p.setSeek(search.getText().toString());
                p.setCustomSort(SlideDeleteListviewFragment.customSort);
                p.setMachineNumber(machineListSpinner.getText().toString());
                p.setBillingTimeEnd(mEndTime.getText().toString());
                p.setBillingTimeStart(startTime.getText().toString());
                prb.getPersonalListResponseInfo(p);










            }
        });

        tmr.onCreate();
        mbp.onCreate();
        prlrbp.onCreate();
        prb.onCreate();
        tmr.BindPresentView(turnaroundManListResponsePv);
        mbp.BindPresentView(machineListResponsePv);
        prlrbp.BindPresentView(programListResponsePv);
        prb.BindPresentView(personalListResponsePv);
        initView();
        initReuqest();
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        transferSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectMan=i;
            }
        });


    }
    private void initView(){
        transferSpinner=getActivity().findViewById(R.id.query_transfer_spinner);
        programSpinner=getActivity().findViewById(R.id.query_program_spinner);
        machineListSpinner=getActivity().findViewById(R.id.query_machine_spinner);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.ll_start_time:
//                // 日期格式为yyyy-MM-dd
//                mStartTimePicker.show(startTime.getText().toString());
//                Log.d("123qqq","223344");
//                break;
//
//            case R.id.ll_end_time:
//                // 日期格式为yyyy-MM-dd HH:mm
//                Log.d("123qqq","22334466");
//                mEndTimePicker.show(endTime.getText().toString());
//                break;
//        }
//    }

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




    private void initTimerPicker() {
        String beginTime = "2015-1-1 18:00";
        final String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);

        startTime.setText(beginTime);
        mEndTime.setText(endTime);
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mStartTimePicker = new CustomDatePicker(getContext(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                startTime.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mStartTimePicker.setCancelable(true);
        // 显示时和分
        mStartTimePicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mStartTimePicker.setScrollLoop(true);
        // 允许滚动动画
        mStartTimePicker.setCanShowAnim(true);



        mEndTimePicker = new CustomDatePicker(getContext(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mEndTime.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mEndTimePicker.setCancelable(true);
        // 显示时和分
        mEndTimePicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mEndTimePicker.setScrollLoop(true);
        // 允许滚动动画
        mEndTimePicker.setCanShowAnim(true);

    }

    @Override
    public void onStop() {
        super.onStop();

    }



    private MachineListResponsePv machineListResponsePv = new MachineListResponsePv(){

        @Override
        public void onError(String result) {

            Log.d("111111",result);


        }

        @Override
        public void onSuccess( final ResultData<List<MachineListResponseBody>> resultData) {    //渲染设备列表
            if (getActivity() == null)
                return;
            else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        Log.d("111111", resultData.getData().size() + "");

                        for (int i = 0; i < resultData.getData().size(); i++) {
                            machineListItems.add(resultData.getData().get(i).getName());
                        }
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_select, machineListItems);
                        machineListSpinner.setItemData(machineListItems);
                    }
                });
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


            if (getActivity() == null)
                return;
            else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        Log.d("22222223", programData.getData().size() + "");

                        for (int i = 0; i < programData.getData().size(); i++) {
                            programListItems.add(programData.getData().get(i).getName());
                        }
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_select, programListItems);
                        programSpinner.setItemData(programListItems);
                    }
                });
            }
        }
    };


    private TurnaroundManListResponsePv turnaroundManListResponsePv = new TurnaroundManListResponsePv(){

        @Override
        public void onSuccess(final ResultData<List<TurnaroundManList>> resultNet) {

            turnaroundManList =resultNet.getData();
            final String[] spinnerItems=new String[turnaroundManList.size()];
            for (int i=0;i<turnaroundManList.size();i++)
            {spinnerItems[i]=turnaroundManList.get(i).getRealName();
                turnaroundManRealName.add(turnaroundManList.get(i).getRealName());
                turnaroundManID.add(turnaroundManList.get(i).getPkId());
            }

            if (getActivity() == null)
                return;
            else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //自定义选择填充后的字体样式
                        //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_select, turnaroundManRealName);
                        //这个在不同的Theme下，显示的效果是不同的
                        //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
                        transferSpinner.setItemData(turnaroundManRealName);

                    }
                });
            }
        }

        @Override
        public void onError(String result) {
            Log.d("111111",result.toString());
        }

    };

    private PersonalListResponsePv personalListResponsePv = new PersonalListResponsePv(){

        @Override
        public void onSuccess(ResultData<List<TurringList>> resultData) {
//            mViewModel.setmTurringList(resultData.getData());
            MyApplication.setTurringList(resultData.getData());
            Log.d("1233334","6666");
            Log.d("12333345",resultData.getData().size()+"");

            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mission_container, ListViewFragment.newInstance());
            ft.commit();
            Log.d("1233334","6666777");
        }



        @Override
        public void onError(String result) {
            Log.d("11111122",result);
        }

    };

    private void initReuqest(){
        TurnaroundManListRequest t=new TurnaroundManListRequest();
        t.setAccountPkId("1");
        tmr.getTurnaroundManListResponseInfo(t);


        SimpleRequest s=new SimpleRequest();
        s.setAccountPkId(MyApplication.getPkId());
        mbp.getMachineListResponseInfo(s);

        SimpleRequest s1=new SimpleRequest();
        s1.setAccountPkId(MyApplication.getPkId());
        prlrbp.getProgramListResponseInfo(s1);


    }


}
