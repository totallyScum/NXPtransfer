package com.zthl.nxp.ui.transfer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.chen.nxp.R;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.model.MachineRequest;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.presenter.TransferListPresenter;
import com.zthl.nxp.presenterView.TurringListResponsePv;
import com.zthl.nxp.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;


public class TransferFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listview;
    private static  List<TurringList> list=new ArrayList<>();
    private TurringListviewViewAdapter adapter;
    private OnFragmentInteractionListener mListener;
    private TransferListPresenter tp =new TransferListPresenter(getContext());

    public TransferFragment() {
        // Required empty public constructor
    }

    public static TransferFragment newInstance() {
        
        Bundle args = new Bundle();

        TransferFragment fragment = new TransferFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MissionPublishedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransferFragment newInstance(String param1, String param2) {
        TransferFragment fragment = new TransferFragment();
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
    public void onStart() {
        super.onStart();
//        listview=getView().findViewById(R.id.mission_list);
//        adapter=new MissionPublishedListviewViewAdapter(getView().getContext(),list,getActivity().getSupportFragmentManager());
//        listview.setAdapter(adapter);
        tp.onCreate();
        tp.BindPresentView(mUserInfoPv);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_turring, container, false);
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
    public void onResume() {
        super.onResume();
        MainViewModel mainViewModel= ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        MachineRequest m=new MachineRequest();
        m.setMachineNumber(mainViewModel.getBarCodeData());
        m.setAccountPkId(MyApplication.getPkId());
        tp.getTransferListResponseInfo(m);
    }

    @Override
    public void onStop() {
        super.onStop();
        list.clear();
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
    private TurringListResponsePv mUserInfoPv = new TurringListResponsePv(){
        @Override
        public void onSuccess(ResultData<List<TurringList>> resultNet) {
        listview=getView().findViewById(R.id.turring_list);
        Log.d("zzaa",resultNet.getData().size()+"");
        for (int i=0;i<resultNet.getData().size();i++)
        {
//            if (resultNet.getData().get(i).getTransitSituation().equals("0"))

            list.add(resultNet.getData().get(i));
        }
        adapter=new TurringListviewViewAdapter(getView().getContext(),list,getActivity().getSupportFragmentManager());
        listview.setAdapter(adapter);

        }

        @Override
        public void onError(String result) {
            Toast.makeText(getContext(),result, Toast.LENGTH_SHORT).show();
        }




    };
}
