package com.fatihsenturk.socialapp.Fragments.Volunteer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fatihsenturk.socialapp.Adapter.Volunteer.MyStuffAdapter;
import com.fatihsenturk.socialapp.Adapter.Volunteer.WaitnigAllowAdapter;
import com.fatihsenturk.socialapp.Fragments.BaseFragment;
import com.fatihsenturk.socialapp.Model.StuffModel;
import com.fatihsenturk.socialapp.R;
import com.fatihsenturk.socialapp.Utils.Utils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by TOSHIBA on 19.3.2016. Mart
 * Dont worry !
 */
public class WaitnigAllow extends BaseFragment {

    private List<StuffModel> waitinAllowList;
    private WaitnigAllowAdapter waitnigAllowAdapter;
    private String currentUsername;
    private ItemReceiver ıtemReceiver;


    @Override
    public void onResume() {
        super.onResume();
        ıtemReceiver = new ItemReceiver();
        getActivity().registerReceiver(ıtemReceiver, new IntentFilter(Utils.SendBroadcat));

    }

    @Override
    public void onStop() {
        super.onStop();
       getActivity().unregisterReceiver(ıtemReceiver);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View mainView = inflater.inflate(R.layout.item_list, container, false);
        final ListView listView = (ListView) mainView.findViewById(R.id.item_list);
        FloatingActionButton floatingActionButton = (FloatingActionButton) mainView.findViewById(R.id.add_item_button);

        ParseQuery<StuffModel> stuffModelParseQuery = new ParseQuery<StuffModel>(StuffModel.class);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String username = currentUser.getUsername();
        currentUsername = username;

//        stuffModelParseQuery.whereEqualTo("username", username);
//        stuffModelParseQuery.whereNotEqualTo("allowStatus", "true");

        stuffModelParseQuery.findInBackground(new FindCallback<StuffModel>() {
            @Override
            public void done(List<StuffModel> list, ParseException e) {
                if (e == null) {
                    waitinAllowList = list;
                    String listSize = String.valueOf(list.size()) ;
                    Toast.makeText(getActivity(), "CalBack sonucu geldi", Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(), "list size : "+ listSize,Toast.LENGTH_LONG).show();
                    waitnigAllowAdapter = new WaitnigAllowAdapter(getActivity(),list);
                    listView.setAdapter(waitnigAllowAdapter);
                    waitnigAllowAdapter.notifyDataSetChanged();
                }
            }
        });


//        if (waitinAllowList == null){
//            waitnigAllowAdapter = new WaitnigAllowAdapter(getActivity(),null);
//        }else {
//            waitnigAllowAdapter = new WaitnigAllowAdapter(getActivity(), waitinAllowList);
//        }

        listView.setAdapter(waitnigAllowAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Do some stuff
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StuffModel myStuffModel = new StuffModel();
                myStuffModel.put("somethingNew", "somethingNewValue");
                myStuffModel.setName("test");
                myStuffModel.setCurrentState(true);
                myStuffModel.setDescription("lorem ipssum lorem ipssum");
                myStuffModel.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getActivity(), "basirili selikde kayit edildi", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        return mainView;
    }

    public class ItemReceiver extends BroadcastReceiver {
        private final Handler handler;

        public ItemReceiver() {
            this.handler = new Handler();
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(Utils.SendBroadcat)){
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }
    }
}
