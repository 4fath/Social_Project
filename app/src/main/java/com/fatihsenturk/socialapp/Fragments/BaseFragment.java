package com.fatihsenturk.socialapp.Fragments;

import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.fatihsenturk.socialapp.R;

/**
 * Created by TOSHIBA on 16.3.2016. Mart
 * Dont worry !
 */
public class BaseFragment extends android.app.Fragment{

    public static boolean isFragmentVisible(Fragment fragment) {
        return fragment != null && fragment.isAdded() && !fragment.isDetached() && !fragment.isRemoving();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // TODO:

    protected void setTitle(ActionBar actionBar, int resourceId){
        if(actionBar != null){
            View view = actionBar.getCustomView().findViewById(R.id.custom_header);
            TextView textView = (TextView) view.findViewById(R.id.header_title);
            textView.setText(resourceId);
        }
    }

    protected void setTitle(ActionBar actionBar, String name) {
        if (actionBar != null) {
            View view = actionBar.getCustomView().findViewById(R.id.custom_header);
            TextView textView = (TextView) view.findViewById(R.id.header_title);
            textView.setText(name);
        }
    }

}

