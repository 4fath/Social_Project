package com.fatihsenturk.socialapp.Fragments.Volunteer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fatihsenturk.socialapp.Acrivity.ItemDetailActivity;
import com.fatihsenturk.socialapp.Adapter.Volunteer.AllItemAdapter;
import com.fatihsenturk.socialapp.Fragments.BaseFragment;
import com.fatihsenturk.socialapp.Model.StuffModel;
import com.fatihsenturk.socialapp.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by TOSHIBA on 19.3.2016. Mart
 * Dont worry !
 */
public class Allitems extends BaseFragment {

    private List<StuffModel> allItemList ;
    private AllItemAdapter allItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();

        if (allItemList == null){
            ParseQuery<StuffModel> stuffModelParseQuery = new ParseQuery<StuffModel>(StuffModel.class);
            stuffModelParseQuery.whereNotEqualTo("allowStatus","false");

            stuffModelParseQuery.findInBackground(new FindCallback<StuffModel>() {
                @Override
                public void done(List<StuffModel> list, ParseException e) {
                    if (e == null){
                        allItemList = list;
                        allItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View mainView = inflater.inflate(R.layout.item_list, container, false);
        ListView listView = (ListView) mainView.findViewById(R.id.item_list);
        FloatingActionButton floatingActionButton = (FloatingActionButton) mainView.findViewById(R.id.add_item_button);

        ParseQuery<StuffModel> stuffModelParseQuery = new ParseQuery<StuffModel>(StuffModel.class);

        stuffModelParseQuery.findInBackground(new FindCallback<StuffModel>() {
            @Override
            public void done(List<StuffModel> list, ParseException e) {
                if (e == null){
                    allItemList = list;
                }
            }
        });

//        allItemList = ParseQuery<Par>

        if (allItemList == null){
            allItemAdapter = new AllItemAdapter(getActivity(),null);
        }else {
            allItemAdapter = new AllItemAdapter(getActivity(), allItemList);

        }

        listView.setAdapter(allItemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Do some stuff
                Intent goToStuffDetail = new Intent(getActivity(), ItemDetailActivity.class);

                // pass some date to intent
                // TODO:

                startActivity(goToStuffDetail);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return mainView;

    }



    public class ItemBroadcastREceiver extends BroadcastReceiver{

        private final Handler handler;

        public ItemBroadcastREceiver() {
            this.handler = new Handler();
        }


        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("allFetchEnded")){
                handler.post(new Runnable() {
                    @Override
                    public void run() {


                    }
                });
            }
        }
    }
}
