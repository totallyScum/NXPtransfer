package com.zthl.nxp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;

import com.chen.nxp.R;
import com.xuexiang.xupdate.XUpdate;
import com.zthl.nxp.constant.UrlConstant;
import com.zthl.nxp.dummy.DummyContent;
import com.zthl.nxp.ui.main.MainFragment;
import com.zthl.nxp.ui.mission.MissionFragment;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;
import com.zthl.nxp.utils.NotifyUtils;

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
        if (!NotifyUtils.isNotifyPermissionOpen(this)) {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage("通知权限未打开，是否前去打开？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface d, int w) {
                            NotifyUtils.openNotifyPermissionSetting(MainActivity.this);
                        }
                    })
                    .setNegativeButton("否", null)
                    .show();
        }
        forceUpdate();
    }

    public static MainActivity newInstance() {
        
        Bundle args = new Bundle();
        
        MainActivity fragment = new MainActivity();
    //    fragment.setArguments(args);
        return fragment;
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

    private void forceUpdate(){
        XUpdate.newBuild(this)
                .updateUrl(UrlConstant.getBaseUrl()+"APPUpdate/version-info.json")
                .update();
    }
}
