package com.zthl.nxp.ui.main;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.zthl.nxp.model.TurringList;

import java.util.List;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private int fragmentID;
    private String barCodeData;
    private List<TurringList> mTurringList;
    private List<TurringList> historyTurringList;

    public List<TurringList> getHistoryTurringList() {
        return historyTurringList;
    }

    public void setHistoryTurringList(List<TurringList> historyTurringList) {
        this.historyTurringList = historyTurringList;
    }

    public List<TurringList> getmTurringList() {
        return mTurringList;
    }

    public void setmTurringList(List<TurringList> mTurringList) {
        this.mTurringList = mTurringList;
    }

    public int getFragmentID() {
        return fragmentID;
    }

    public void setFragmentID(int fragmentID) {
        Log.d("MainViewModel",fragmentID+"");
        this.fragmentID = fragmentID;
    }

    public String getBarCodeData() {
        Log.d("MainViewModel_get",barCodeData+"");
        return barCodeData;
    }

    public void setBarCodeData(String barCodeData) {
        Log.d("MainViewModel_put",barCodeData+"");
        this.barCodeData = barCodeData;
    }
}
