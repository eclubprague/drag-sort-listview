package com.mobeta.android.demodslv;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DSLVFragment extends ListFragment {

    private ArrayAdapter<String> mAdapter;

    private final DragSortListView.DropListener mDropListener =
            new DragSortListView.DropListener() {
                @Override
                public void drop(int from, int to) {
                    if (from != to) {
                        String item = mAdapter.getItem(from);
                        mAdapter.remove(item);
                        mAdapter.insert(item, to);
                    }
                }
            };

    private final DragSortListView.RemoveListener mRemoveListener =
            new DragSortListView.RemoveListener() {
                @Override
                public void remove(int which) {
                    mAdapter.remove(mAdapter.getItem(which));
                }
            };

    private DragSortListView mDslv;
    private DragSortController mController;

    public int dragStartMode = DragSortController.ON_DOWN;
    public boolean removeEnabled = false;
    public int removeMode = DragSortController.FLING_REMOVE;
    public boolean sortEnabled = true;
    public boolean dragEnabled = true;


    public DSLVFragment() {
        super();
    }


    public DragSortController getController() {
        return mController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDslv = (DragSortListView) inflater.inflate(R.layout.dslv_fragment_main, container, false);

        // defaults are
        //   dragStartMode = onDown
        //   removeMode = flingRight
        DragSortController controller = new DragSortController(mDslv);
        controller.setDragHandleId(R.id.drag_handle);
        controller.setClickRemoveId(R.id.click_remove);
        controller.setRemoveEnabled(removeEnabled);
        controller.setSortEnabled(sortEnabled);
        controller.setDragInitMode(dragStartMode);
        controller.setRemoveMode(removeMode);

        mController = controller;
        mDslv.setFloatViewManager(mController);
        mDslv.setOnTouchListener(mController);
        mDslv.setDragEnabled(dragEnabled);

        return mDslv;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDslv = (DragSortListView) getListView();

        mDslv.setDropListener(mDropListener);
        mDslv.setRemoveListener(mRemoveListener);


        String[] array = getResources().getStringArray(R.array.jazz_artist_names);
        List<String> list = new ArrayList<>(Arrays.asList(array));

        mAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_handle_left, R.id.text, list);
        setListAdapter(mAdapter);
    }


}
