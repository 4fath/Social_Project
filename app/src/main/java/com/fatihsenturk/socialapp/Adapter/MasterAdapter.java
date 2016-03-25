package com.fatihsenturk.socialapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fatihsenturk.socialapp.Model.StuffModel;
import com.fatihsenturk.socialapp.R;
import com.fatihsenturk.socialapp.Utils.Helper;

import java.util.List;

/**
 * Created by TOSHIBA on 25.3.2016. Mart
 * Dont worry !
 */
public class MasterAdapter extends BaseAdapter {

    private List<StuffModel> itemList;
    private final Activity attachActivity;

    public MasterAdapter(Activity activity, List<StuffModel> itemList) {
        this.itemList = itemList;
        this.attachActivity = activity;
    }

    @Override
    public int getCount() {
        if (itemList.isEmpty()) {
            return 0;
        } else {
            return itemList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View cell = convertView;

        if (cell == null) {
            LayoutInflater inflater = (LayoutInflater) attachActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.item_row, parent, false);
        }

        StuffRowHolder stuffRowHolder = new StuffRowHolder();
        init(cell, stuffRowHolder);
        setView(position, stuffRowHolder);

        return null;
    }


    private static class StuffRowHolder {
        private ImageView newsImage;
        private TextView newsDate;
        private TextView newsTitle;
        private TextView newsDescription;
        private LinearLayout newsBackground;
    }


    private void init(View view, StuffRowHolder viewHolder) {
        viewHolder.newsImage = (ImageView) view.findViewById(R.id.news_row_photo);
        viewHolder.newsDate = (TextView) view.findViewById(R.id.news_row_date);
        viewHolder.newsTitle = (TextView) view.findViewById(R.id.news_row_title);
        viewHolder.newsDescription = (TextView) view.findViewById(R.id.news_row_description);
        viewHolder.newsBackground = (LinearLayout) view.findViewById(R.id.news_row_layout);
    }


    private void setView(int position, StuffRowHolder viewHolder) {
        StuffModel stuffItem = itemList.get(position);
        viewHolder.newsDate.setText(Helper.getDateDayMounthYear(attachActivity, stuffItem.getCreatedAt()));
        viewHolder.newsTitle.setText(stuffItem.getName());
        viewHolder.newsDescription.setText(stuffItem.getDescription());
//        viewHolder.newsImage.setImageDrawable();

        if (position % 2 == 0) {
            viewHolder.newsBackground.setBackgroundColor(ContextCompat.getColor(attachActivity, R.color.item_color_1));
        } else {
            viewHolder.newsBackground.setBackgroundColor(ContextCompat.getColor(attachActivity, R.color.item_color_2));
        }

    }
}
