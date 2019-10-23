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
import android.widget.ListView;

import com.chen.nxp.R;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.model.request.PersonalListRequest;
import com.zthl.nxp.presenter.PersonalListResponseBodyPresenter;
import com.zthl.nxp.presenterView.PersonalListResponsePv;
import com.zthl.nxp.ui.history.HistoryItemListViewAdapter;
import com.zthl.nxp.ui.main.MainViewModel;
import com.zthl.nxp.ui.missionList.MissionListItemListviewViewAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private MainViewModel mViewModel;
    private ListView mListview;
    private PersonalListResponseBodyPresenter prb=new PersonalListResponseBodyPresenter(getContext());
    private static HistoryItemListViewAdapter adapter;
    public ListViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListViewFragment newInstance(String param1, String param2) {
        ListViewFragment fragment = new ListViewFragment();
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
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mListview=getActivity().findViewById(R.id.mission_list);

    }

    @Override
    public void onResume() {
        super.onResume();

        if (MyApplication.getTurringList()!=null)
        {
            Log.d("12333345","不空");
            adapter =new HistoryItemListViewAdapter(getContext(),MyApplication.getTurringList(),false);
            mListview.setAdapter(adapter);
        }
        if (MyApplication.getTurringList()==null) {
            prb.onCreate();
            prb.BindPresentView(personalListResponsePv);
            PersonalListRequest p=new PersonalListRequest();
            p.setAccountPkId(MyApplication.getPkId());
            prb.getPersonalListResponseInfo(p);

        }
        MyApplication.setTurringList(null);
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModel.setmTurringList(null);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public static ListViewFragment newInstance() {

        Bundle args = new Bundle();

        ListViewFragment fragment = new ListViewFragment();
        fragment.setArguments(args);
        return fragment;
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

    private PersonalListResponsePv personalListResponsePv = new PersonalListResponsePv(){

        @Override
        public void onSuccess(ResultData<List<TurringList>> resultData) {
            mViewModel.setmTurringList(resultData.getData());
            Log.d("1233334","6666");
            Log.d("123333457",resultData.getData().size()+"");

//            FragmentManager fm = getFragmentManager();
//            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//            ft.replace(R.id.mission_container, ListViewFragment.newInstance());
//            //   ft.addToBackStack("UserTag");
//            ft.commit();
//            Log.d("1233334","6666777");

            adapter  =new HistoryItemListViewAdapter(getContext(),resultData.getData(),true);
            mListview.setAdapter(adapter);


        }



        @Override
        public void onError(String result) {
            Log.d("11111122",result);
        }

    };
}
