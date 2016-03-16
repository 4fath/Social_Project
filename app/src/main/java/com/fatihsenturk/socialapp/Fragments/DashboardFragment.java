package com.fatihsenturk.socialapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fatihsenturk.socialapp.Acrivity.HomePageActivity;
import com.fatihsenturk.socialapp.Adapter.DashboardAdapter;
import com.fatihsenturk.socialapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOSHIBA on 16.3.2016. Mart
 * Dont worry !
 */
public class DashboardFragment extends BaseFragment {

    private final static String ARG_POSITION = "position";
    private List dashboardItemList;
    private DashboardAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ActionBar actionBar = ((HomePageActivity) getActivity()).getSupportActionBar();

        setTitle(actionBar, R.string.anasayfa_ihtiyacli);

        View rootView = inflater.inflate(R.layout.dashboard, container, false);

        dashboardItemList = new ArrayList();

        if (savedInstanceState != null) {
            int mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        adapter = new DashboardAdapter(getActivity(), dashboardItemList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dashboardItemList.get(position);

            }
        });

        return rootView;
    }

}
