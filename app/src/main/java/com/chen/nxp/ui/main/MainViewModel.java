package com.chen.nxp.ui.main;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private int fragmentID;
    private String barCodeData;

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
