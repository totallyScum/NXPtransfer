package com.zthl.nxp.ui.check;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chen.nxp.R;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.model.InvoiceList;
import com.zthl.nxp.model.ResultData;
import com.zthl.nxp.model.request.InvoiceListRequest;
import com.zthl.nxp.presenter.InvoiceListResponseBodyPresenter;
import com.zthl.nxp.presenterView.InvoiceListResponsePv;
import com.zthl.nxp.ui.history.HistoryItemListViewAdapter;
import com.zthl.nxp.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private androidx.appcompat.widget.Toolbar toolbar;
    private ListView listView;
    private InvoiceListResponseBodyPresenter ilp=new InvoiceListResponseBodyPresenter(getContext());
    public CheckListFragment() {
        // Required empty public constructor
    }

    public static CheckListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CheckListFragment fragment = new CheckListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckListFragment newInstance(String param1, String param2) {
        CheckListFragment fragment = new CheckListFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_list, container, false);
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
        toolbar=getActivity().findViewById(R.id.check_toolbar);
        toolbar.inflateMenu(R.menu.options_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.check_start)
                {
                    Log.d("234567777","111");
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    //      mViewModel.setFragmentID(1);
                    ft.replace(R.id.container, CheckStartFragment.newInstance());
                 //   ft.addToBackStack("UserTag");
                    ft.commit();
                }
//                if (item.getItemId()==R.id.invoices_cancel){
//                    FragmentManager fm = getFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
//                    //      mViewModel.setFragmentID(1);
//                    ft.replace(R.id.container, CheckEndFragment.newInstance());
//                    ft.addToBackStack("UserTag");
//                    ft.commit();
//                }
                return true;
            }
        });
//        ActionBar actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();
//        actionBar.setTitle("233333");
        initList();
        MainViewModel mainViewModel= ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        InvoiceListRequest i=new InvoiceListRequest();
        i.setAccountPkId(MyApplication.getPkId());
        i.setFounder(MyApplication.getPkId());
        i.setIncoicesTypeID(MyApplication.getFragmentID()+1+"");
        i.setMachineNumber(mainViewModel.getBarCodeData());
        ilp.onCreate();
        ilp.BindPresentView(invoiceListResponsePv);
        ilp.getInvoiceListResponseInfo(i);
    }

    private void initList(){
        listView=getActivity().findViewById(R.id.invoices_list);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.add(Menu.NONE, 0 , 0, "设置").setIcon(R.mipmap.setting);
      menu.clear();
      inflater.inflate(R.menu.options_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
//         if (item.getItemId()==R.id.invoices_)
//         {
//             Log.d("id8888",item.getItemId()+"");
//         }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
//        switch (item.getItemId())
//        {
//            case 0:{
//                ft.replace(R.id.container, CheckStartFragment.newInstance());
//                ft.addToBackStack("UserTag");
//                ft.commit();

        Log.d("id8888",item.getItemId()+"");
//        Intent intent = new Intent(getActivity(), AutomaticBarcodeActivity.class);
//        intent.putExtra("id",10);
//        startActivity(intent);

        //  }break;
        //}
        return true;
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
    private InvoiceListResponsePv invoiceListResponsePv = new InvoiceListResponsePv(){

        @Override
        public void onSuccess(ResultData<List<InvoiceList>> resultNet) {


            Log.d("234567777",resultNet.getData().size()+"");

//            ArrayList<InvoiceList> temp=new ArrayList<>();
//
//            for (int i=0;i<resultNet.getData().size();i++)
//            {
//                if (resultNet.getData().get(i).get)
//                temp
//            }
//            if (resultNet.getData().get())
            CheckListviewAdapter adapter=new CheckListviewAdapter(getView().getContext(),resultNet.getData(),getFragmentManager());
            listView.setAdapter(adapter);




        }


        @Override
        public void onError(String result) {
            Log.d("111111",result.toString());
        }

    };
}
