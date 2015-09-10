package com.mobeta.android.demodslv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;


public class TestBedDSLV extends FragmentActivity {

    private static final String TAG_DSLV_FRAGMENT = "dslv_fragment";
    public static final String TAG = TestBedDSLV.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_bed_main);

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(TAG_DSLV_FRAGMENT) == null) {
            fm.beginTransaction()
                    .add(R.id.test_bed, getNewDslvFragment(), TAG_DSLV_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    private Fragment getNewDslvFragment() {
        DSLVFragmentClicks f = DSLVFragmentClicks.newInstance(0, 0);
        f.removeMode = DragSortController.FLING_REMOVE;
        f.removeEnabled = true;
        f.dragStartMode = DragSortController.ON_DRAG;
        f.sortEnabled = true;
        f.dragEnabled = true;
        return f;
    }

}
