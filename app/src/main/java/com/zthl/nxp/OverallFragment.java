package com.zthl.nxp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.chen.nxp.R;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.model.request.PersonalListRequest;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.presenter.OverallListResponseBodyPresenter;
import com.zthl.nxp.presenter.PersonalListResponseBodyPresenter;
import com.zthl.nxp.presenterView.OverallListResponsePv;
import com.zthl.nxp.presenterView.PersonalListResponsePv;
import com.zthl.nxp.ui.AutomaticBarcodeActivity;
import com.zthl.nxp.ui.history.HistoryItemListViewAdapter;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OverallFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OverallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverallFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView mListview;
    private androidx.appcompat.widget.Toolbar toolbar;
    private OnFragmentInteractionListener mListener;
    private OverallListResponseBodyPresenter prb = new OverallListResponseBodyPresenter(getContext());
    private static HistoryItemListViewAdapter adapter;
    private MainViewModel mViewModel;

    Button machineNumber, currentName, targetProgram, defaultButton;
    int[] select = new int[]{0, 0, 0,0};
    List<String> selectName = new ArrayList<>();

    //Boolean machineNumberSelect=false,currentNameSelect=false,targetProgramSelect=false,defaultButtonSelect=false;
    public OverallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OverallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OverallFragment newInstance(String param1, String param2) {
        OverallFragment fragment = new OverallFragment();
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
        return inflater.inflate(R.layout.fragment_overall, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectName.add(0, "Machine_Number");
        selectName.add(1, "Current_Name");
        selectName.add(2, "Target_Program");


        mListview = getActivity().findViewById(R.id.over_all_list);
        prb.onCreate();
        prb.BindPresentView(overallListResponsePv);
        SimpleRequest p = new SimpleRequest();
        p.setAccountPkId(MyApplication.getPkId());
        prb.getOverallListResponseInfo(p);


        initToolbar();
        machineNumber = getActivity().findViewById(R.id.machine_number);
        currentName = getActivity().findViewById(R.id.current_name);
        targetProgram = getActivity().findViewById(R.id.target_program);
        defaultButton= getActivity().findViewById(R.id.default_sort);

        machineNumber.setOnClickListener(this);
        currentName.setOnClickListener(this);
        targetProgram.setOnClickListener(this);
        defaultButton.setOnClickListener(this);

    }

    public void initToolbar() {
        toolbar = getActivity().findViewById(R.id.mission_list_toolbar);
        toolbar.inflateMenu(R.menu.options_transfer_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.transfer_start) {


                    mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
                    Intent intent = new Intent(getActivity(), AutomaticBarcodeActivity.class);      //跳转到开票界面
                    Bundle b = new Bundle();
                    b.putInt("id", 0);
                    intent.putExtras(b);
                    mViewModel.setFragmentID(0);
                    MainFragment.check = false;
                    getActivity().startActivity(intent);

                }
                return true;
            }
        });
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

    public static OverallFragment newInstance() {

        Bundle args = new Bundle();

        OverallFragment fragment = new OverallFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.machine_number: {
                if (select[0] == 0) {
                    select[0]= 1;
         //           machineNumber.setBackgroundColor(R.color.colorButtonYellow);
        //            machineNumber.getBackground().setColorFilter(R.color.colorButtonYellow, PorterDuff.Mode.MULTIPLY);
            machineNumber.setBackgroundResource(R.drawable.button_background_yellow);
                    sortList(0,false);
break;
                }
                if (select[0] == 1) {
                    select[0] = 0;
                    machineNumber.setBackgroundResource(R.drawable.button_background_grey);

                    //  machineNumber.setBackgroundColor(R.color.select_title_text);
                    machineNumber.getBackground().clearColorFilter();

                    sortList(0,true);
                }
                break;
            }
            case R.id.current_name: {


                if (select[1] == 0) {
                    select[1] = 1;
               //     currentName.setBackgroundColor(R.color.colorButtonYellow);
                    currentName.setBackgroundResource(R.drawable.button_background_yellow);

                    sortList(1,false);
break;
                }
                if (select[1] == 1) {
                    select[1] = 0;
                    currentName.setBackgroundResource(R.drawable.button_background_grey);

                    //   currentName.setBackgroundColor(R.color.select_title_text);
                    sortList(1,true);
                }
                break;
            }
            case R.id.target_program:
                if (select[2] == 0) {
                    select[2] = 1;
           //         targetProgram.getBackground().setBackgroundColor(R.color.colorButtonYellow);

                    targetProgram.setBackgroundResource(R.drawable.button_background_yellow);

//                    targetProgram.getBackground().setColorFilter(R.color.colorButtonYellow, PorterDuff.Mode.MULTIPLY);
                    sortList(2,false);
break;
                }
                if (select[2] == 1) {
                    select[2] = 0;
            //        targetProgram.setBackgroundColor(R.color.select_title_text);
                    targetProgram.setBackgroundResource(R.drawable.button_background_grey);

                    sortList(2,true);
                }
                break;

            case R.id.default_sort:
                    select[0] = 0;
                    select[1] = 0;
                    select[2] = 0;
                    machineNumber.setBackgroundResource(R.drawable.button_background_grey);
                    currentName.setBackgroundResource(R.drawable.button_background_grey);
                    //        targetProgram.setBackgroundColor(R.color.select_title_text);
                    targetProgram.setBackgroundResource(R.drawable.button_background_grey);


                    SimpleRequest p = new SimpleRequest();
                    p.setAccountPkId(MyApplication.getPkId());
                    prb.getOverallListResponseInfo(p);




                break;

        }

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


    private OverallListResponsePv overallListResponsePv = new OverallListResponsePv() {

        @Override
        public void onSuccess(ResultData<List<TurringList>> resultData) {
            Log.d("1233334", "6666");
            Log.d("12333345", resultData.getData().size() + "");

//            FragmentManager fm = getFragmentManager();
//            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//            ft.replace(R.id.mission_container, ListViewFragment.newInstance());
//            //   ft.addToBackStack("UserTag");
//            ft.commit();
//            Log.d("1233334","6666777");

            adapter = new HistoryItemListViewAdapter(getContext(), resultData.getData(), false);
            mListview.setAdapter(adapter);


        }


        @Override
        public void onError(String result) {
            Log.d("11111122", result);
        }

    };


    private void sortList(int j,boolean desc){
        String customSort = new String();
        customSort="";
        for (int i = 0; i < 3; i++) {
            if (select[i] == 1) {
                if (i==j)
                {
                    if (desc)
                    {
                        if (customSort == "")
                            customSort = selectName.get(i)+" Desc";
                        else
                            customSort = customSort + "," + selectName.get(i)+" Desc";
                    }
                    else {
                        if (customSort == "")
                            customSort = selectName.get(i);
                        else
                            customSort = customSort + "," + selectName.get(i);
                    }
                    }
                else  if (customSort == "")
                    customSort = selectName.get(i);
                else
                    customSort = customSort + "," + selectName.get(i);
            }
        }
        SimpleRequest p = new SimpleRequest();
        p.setAccountPkId(MyApplication.getPkId());
        p.setCustomSort(customSort);
        Log.d("customSort",customSort);
        prb.getOverallListResponseInfo(p);

    }
}
