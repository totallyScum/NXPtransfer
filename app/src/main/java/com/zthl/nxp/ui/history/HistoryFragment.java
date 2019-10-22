package com.zthl.nxp.ui.history;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.zthl.nxp.ListViewFragment;
import com.zthl.nxp.QueryFactorFragment;
import com.zthl.nxp.QueryFactorHistoryFragment;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.request.SimpleRequest;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.presenter.HistoryResponseBodyPresenter;
import com.zthl.nxp.presenterView.HistoryResponsePv;
import com.zthl.nxp.ui.mission.MissionFragment;
import com.chen.nxp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int mColumnCount = 1;
    private MissionFragment.OnListFragmentInteractionListener mListener;
    private ListView listview;
    private ArrayList<String> list;
    private HistoryItemListViewAdapter adapter;
    private EditText search;
  //  private OnFragmentInteractionListener mListener;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        if (view instanceof ListView) {
            listview = (ListView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
        }
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search=getActivity().findViewById(R.id.search);
        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.mission_container, QueryFactorHistoryFragment.newInstance());
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

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.mission_container, HistoryListViewFragment.newInstance());
        ft.commit();


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




    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
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
}
