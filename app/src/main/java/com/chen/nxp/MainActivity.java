package com.chen.nxp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chen.nxp.dummy.DummyContent;
import com.chen.nxp.ui.main.MainFragment;
import com.chen.nxp.ui.mission.MissionFragment;

public class MainActivity extends AppCompatActivity implements MissionFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }


}
