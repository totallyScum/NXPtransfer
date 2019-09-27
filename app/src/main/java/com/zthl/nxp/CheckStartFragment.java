package com.zthl.nxp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.chen.nxp.R;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.ui.main.MainViewModel;


public class CheckStartFragment extends Fragment {
    private ListView mListView;
    private MainViewModel mainViewModel;
    private TextView checkMachineNumber ;   //机器码
   private TextView checkType; //开票类型
    public static CheckStartFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CheckStartFragment fragment = new CheckStartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check_start, container, false);
    //    return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        checkMachineNumber=getView().findViewById(R.id.check_machine_number);
        checkType=getView().findViewById(R.id.check_type);
        if (mainViewModel!=null)
            checkMachineNumber.setText(mainViewModel.getBarCodeData());
        if (mainViewModel.getFragmentID()!=-1)
            checkType.setText(MainFragment.themeCheck[mainViewModel.getFragmentID()]);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkMachineNumber.setText(mainViewModel.getBarCodeData());
    }
}
