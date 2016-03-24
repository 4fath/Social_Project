package com.fatihsenturk.socialapp.Adapter.Volunteer;

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
import com.fatihsenturk.socialapp.Utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Date;
import java.util.List;

/**
 * Created by TOSHIBA on 19.3.2016. Mart
 * Dont worry !
 */
public class AllItemAdapter extends BaseAdapter {


    private static int PLAYER_ROW = 0; // already there is just one cell type

    private final Activity activity;
    private List<StuffModel> playerList;

    public AllItemAdapter ( Activity activity , List<StuffModel> playerList){
        this.activity = activity;
        this.playerList = playerList;
    }

    @Override
    public int getCount() {
        if (playerList == null){
            return 0;
        }else {
            return playerList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return playerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View cell = convertView;
        if (cell == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.item_row, parent, false);
        }

        NewsRowHolder newsRowHolder = new NewsRowHolder();
        init(cell,newsRowHolder);
        setView(position, newsRowHolder);

        return cell;
    }


    private static class NewsRowHolder {
        private ImageView newsImage;
        private TextView newsDate;
        private TextView newsTitle;
        private TextView newsDescription;
        private LinearLayout newsBackground;
    }

    private void init(View view, NewsRowHolder viewHolder) {
        viewHolder.newsImage = (ImageView) view.findViewById(R.id.news_row_photo);
        viewHolder.newsDate = (TextView) view.findViewById(R.id.news_row_date);
        viewHolder.newsTitle = (TextView) view.findViewById(R.id.news_row_title);
        viewHolder.newsDescription = (TextView)view.findViewById(R.id.news_row_description);
        viewHolder.newsBackground = (LinearLayout) view.findViewById(R.id.news_row_layout);

    }

    private void setView(int position, NewsRowHolder viewHolder) {
        StuffModel news =  playerList.get(position);
        // Set
//        String imageUrl = news.getNewsImagesList().get(0).getNewsImageUrl();
//        ImageLoader.getInstance().displayImage(imageUrl, viewHolder.newsImage, GalatasarayApp.options);
        viewHolder.newsDate.setText(Helper.getDateDayMounthYear(activity, news.getCreatedAt() ));
        viewHolder.newsTitle.setText(news.getName());
        viewHolder.newsDescription.setText(news.getDescription());

        if (position % 2 == 0) {
            viewHolder.newsBackground.setBackgroundColor(ContextCompat.getColor(activity, R.color.item_color_1));
        } else {
            viewHolder.newsBackground.setBackgroundColor(ContextCompat.getColor(activity, R.color.item_color_2));
        }

    }





}
