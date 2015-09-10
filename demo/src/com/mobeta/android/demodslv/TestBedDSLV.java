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


public class TestBedDSLV extends FragmentActivity implements RemoveModeDialog.RemoveOkListener,
        DragInitModeDialog.DragOkListener {

    private static final String TAG_DSLV_FRAGMENT = "dslv_fragment";
    public static final String TAG = TestBedDSLV.class.getSimpleName();

    private int mNumHeaders = 0;
    private int mNumFooters = 0;

    private int mDragStartMode = DragSortController.ON_DRAG;
    private boolean mRemoveEnabled = true;
    private int mRemoveMode = DragSortController.FLING_REMOVE;
    private boolean mSortEnabled = true;
    private boolean mDragEnabled = true;

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

    @Override
    public void onRemoveOkClick(int removeMode) {
        Log.d(TAG, "onRemoveOkClick");
        if (removeMode != mRemoveMode) {
            mRemoveMode = removeMode;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.test_bed, getNewDslvFragment(), TAG_DSLV_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void onDragOkClick(int dragStartMode) {
        mDragStartMode = dragStartMode;
        DSLVFragment f =
                (DSLVFragment) getSupportFragmentManager().findFragmentByTag(TAG_DSLV_FRAGMENT);
        f.getController().setDragInitMode(dragStartMode);
    }
    
    private Fragment getNewDslvFragment() {
        DSLVFragmentClicks f = DSLVFragmentClicks.newInstance(mNumHeaders, mNumFooters);
        f.removeMode = mRemoveMode;
        f.removeEnabled = mRemoveEnabled;
        f.dragStartMode = mDragStartMode;
        f.sortEnabled = mSortEnabled;
        f.dragEnabled = mDragEnabled;
        return f;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
