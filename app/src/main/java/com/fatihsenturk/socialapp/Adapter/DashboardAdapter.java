package com.fatihsenturk.socialapp.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by TOSHIBA on 16.3.2016. Mart
 * Dont worry !
 */
public class DashboardAdapter extends BaseAdapter {

    private static int ITEM_ROW_WITH_PICTURE = 0;
    private static int ITEM_ROW_WITH_NOPIC = 1;

    private final Activity activity;
    private List dashboardItemList;

    public DashboardAdapter(Activity activity, List dashboardItemList) {
        this.activity = activity;
        this.dashboardItemList = dashboardItemList;
    }

    @Override
    public int getCount() {
        if (dashboardItemList == null){
            return 0;
        }
        return dashboardItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return dashboardItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
