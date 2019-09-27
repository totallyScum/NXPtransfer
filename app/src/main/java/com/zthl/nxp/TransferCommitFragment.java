package com.zthl.nxp;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.nxp.R;
import com.zthl.nxp.data.Result;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurnaroundManList;
import com.zthl.nxp.model.TurnaroundManListRequest;
import com.zthl.nxp.presenter.TurnaroundManListResponseBodyPresenter;
import com.zthl.nxp.presenterView.TurnaroundManListResponsePv;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.ui.main.MainViewModel;
import com.zthl.nxp.utils.TimeUtil;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.TransferCommitRequset;
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
    private Spinner transferSpinner;
    private String accont;
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
        tcr.onCreate();
        tmr.onCreate();
        tcr.BindPresentView(mUserInfoPv);
        tmr.BindPresentView(turnaroundManListResponsePv);
        currentTime=getActivity().findViewById(R.id.current_time);
        submit=getActivity().findViewById(R.id.transfer_commit_submit);
        transferSpinner=getActivity().findViewById(R.id.transfer_man_spinner);
        TextView account=getActivity().findViewById(R.id.account);
        currentTime.setText(TimeUtil.getCurrentTime());
        account.setText(accont);
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransferCommitRequset t=new TransferCommitRequset();
                TurningPoint tp=new TurningPoint();
                tp.setMachineNumber("1234");
                tp.setTurnaroundMan("王五");
                tp.setGrouping("A");
                tp.setOperator("赵四");
                tp.setCurrentName(accont);
                tp.setTargetProgram("ae86");
                tp.setBillingtime("2019/09/19");
                t.setAccountPkId("1");
                t.setTurningPoint(tp);
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
    TurnaroundManListRequest t=new TurnaroundManListRequest();
    t.setAccountPkId("1");
    tmr.getTurnaroundManListResponseInfo(t);
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
            Log.d("2134",resultNet.toString());
        //    JSONObject jsonData = JSONObject.fromObject(school);
      //      System.out.println(jsonData);
            if (resultNet.getState().equals("1"))
            {
                Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_LONG).show();

                                getFragmentManager()
                                     .beginTransaction()
                                     .addToBackStack(null)  //将当前fragment加入到返回栈中
                                     .replace(R.id.container, MainFragment.newInstance()).commit();
            }
            if (resultNet.getState().equals("0"))
            {
                Toast.makeText(getContext(),"用户不存在",Toast.LENGTH_LONG);
            }
        }


        };

    private TurnaroundManListResponsePv turnaroundManListResponsePv = new TurnaroundManListResponsePv(){

        @Override
        public void onSuccess(final ResultData<List<TurnaroundManList>> resultNet) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String[] spinnerItems=new String[resultNet.getData().size()];
                    for (int i=0;i<resultNet.getData().size();i++)
                    {spinnerItems[i]=resultNet.getData().get(i).getRealName();
                    }
                    //自定义选择填充后的字体样式
                    //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_select, spinnerItems);
                    //这个在不同的Theme下，显示的效果是不同的
                    //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
                    transferSpinner.setAdapter(spinnerAdapter);
                }
            });
        }

        @Override
        public void onError(String result) {
            Log.d("111111",result.toString());
        }

    };
    
}

