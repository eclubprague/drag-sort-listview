package com.mobeta.android.demodslv;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class DSLVFragmentClicks extends DSLVFragment {

    public DSLVFragmentClicks() {
        super();
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                String message = String.format("Clicked item %d", arg2);
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                String message = String.format("Long-clicked item %d", arg2);
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
