package com.chen.nxp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chen.nxp.dummy.DummyContent;
import com.chen.nxp.ui.main.MainFragment;
import com.chen.nxp.ui.mission.MissionFragment;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;

public class MainActivity extends AppCompatActivity implements MissionFragment.OnListFragmentInteractionListener {
    private static BarcodeReader barcodeReader;
    private AidcManager manager;

    private Button btnAutomaticBarcode;
    private Button btnClientBarcode;
    private Button btnScannerSelectBarcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        AidcManager.create(this, new AidcManager.CreatedCallback() {

            @Override
            public void onCreated(AidcManager aidcManager) {
                manager = aidcManager;
                barcodeReader = manager.createBarcodeReader();
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // create the AidcManager providing a Context and a
        // CreatedCallback implementation.

  //      ActivitySetting();

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
    public static BarcodeReader getBarcodeObject() {
        return barcodeReader;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
