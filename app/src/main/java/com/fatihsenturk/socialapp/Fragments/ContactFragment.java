package com.fatihsenturk.socialapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatihsenturk.socialapp.R;

/**
 * Created by TOSHIBA on 19.3.2016. Mart
 * Dont worry !
 */
public class ContactFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact, container, false);
    }
}
