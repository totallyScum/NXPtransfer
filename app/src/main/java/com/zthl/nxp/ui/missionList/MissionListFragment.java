package com.zthl.nxp.ui.missionList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.nxp.R;
import com.zthl.nxp.ListViewFragment;
import com.zthl.nxp.QueryFactorFragment;
import com.zthl.nxp.SearchFragment;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.ResultNoData;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.presenterView.TransferCommitResponsePv;
import com.zthl.nxp.presenterView.TurringListResponsePv;
import com.zthl.nxp.ui.AutomaticBarcodeActivity;
import com.zthl.nxp.ui.check.CheckStartFragment;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.ui.main.MainViewModel;
import com.zthl.nxp.ui.transfer.TransferFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MissionListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MissionListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MissionListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int mColumnCount = 1;
    private MainViewModel mViewModel;
 //   private MissionFragment.OnListFragmentInteractionListener mListener;
    private ListView listview;
    private ArrayList<String> list;
    private MissionListItemListviewViewAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText search;
    private OnFragmentInteractionListener mListener;
    private androidx.appcompat.widget.Toolbar toolbar;
    private ListView mListview;
    public MissionListFragment() {
        // Required empty public constructor
    }

    public static MissionListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MissionListFragment fragment = new MissionListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MissionListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MissionListFragment newInstance(String param1, String param2) {
        MissionListFragment fragment = new MissionListFragment();
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
        View rootView=inflater.inflate(R.layout.fragment_mission_list, container, false);
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar=getActivity().findViewById(R.id.mission_list_toolbar);
        toolbar.inflateMenu(R.menu.options_transfer_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.transfer_start)
                {






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

//                    getChildFragmentManager().beginTransaction()
//                    .replace(R.id.mission_container, ListViewFragment.newInstance())
//                    .commitAllowingStateLoss();




        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.mission_container, ListViewFragment.newInstance());
        ft.commit();



        search=getActivity().findViewById(R.id.search);
        mListview=getView().findViewById(R.id.mission_list);
        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.mission_container, QueryFactorFragment.newInstance());
                ft.commit();
                return false;
            }

//            @Override
//            public void onClick(View view) {
//                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//                ft.replace(R.id.mission_container, QueryFactorFragment.newInstance());
//                ft.commit();
//
//            }



//            @Override
//            public void onFocusChange(View view, boolean b) {
////                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
////                ft.replace(R.id.mission_container, QueryFactorFragment.newInstance());
////             //   ft.addToBackStack("UserTag");
////                ft.commit();
//
//                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//                ft.replace(R.id.mission_container, QueryFactorFragment.newInstance());
//                   ft.addToBackStack(null);
//                ft.commit();
//
//
////                                getFragmentManager()
////                        .beginTransaction()
////                        .addToBackStack(null)  //将当前fragment加入到返回栈中
////                        .replace(R.id.container, SearchFragment.newInstance()).commit();
//            }

        });



        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                }
                return false;
            }
        });


//        search.setOnCapturedPointerListener(new View.OnCapturedPointerListener() {
//            @Override
//            public boolean onCapturedPointer(View view, MotionEvent motionEvent) {
//                Log.d("66666644","23333");
//                return false;
//            }
//        });
   //     initView();
        initRequest();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.options_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initView(){


        FragmentTransaction ft =   getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mission_container, ListViewFragment.newInstance());
        ft.commit();
    }
    private void initRequest(){



    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
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


    private TurringListResponsePv mUserInfoPv = new TurringListResponsePv(){
        @Override
        public void onSuccess(ResultData<List<TurringList>> resultNet) {

            adapter=new MissionListItemListviewViewAdapter(getView().getContext(),resultNet.getData());
            listview.setAdapter(adapter);

        }

        @Override
        public void onError(String result) {
            Toast.makeText(getContext(),result, Toast.LENGTH_SHORT).show();
        }

//        @Override
//        public void onSuccess(ResultNoData resultNet) {
//            //    JSONObject jsonData = JSONObject.fromObject(school);
//            //      System.out.println(jsonData);
//            if (resultNet.getState().equals("1"))
//            {
//                Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_LONG).show();
//
//                getFragmentManager()
//                        .beginTransaction()
//                        .addToBackStack(null)  //将当前fragment加入到返回栈中
//                        .replace(R.id.container, MainFragment.newInstance()).commit();
//            }
//            if (resultNet.getState().equals("0"))
//            {
//                Toast.makeText(getContext(),"用户不存在",Toast.LENGTH_LONG);
//            }
//        }


    };




}
