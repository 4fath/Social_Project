package com.fatihsenturk.socialapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseUser;

/**
 * Created by TOSHIBA on 15.3.2016. Mart
 * Dont worry !
 */
public class DispatchActivity extends AppCompatActivity {

    public DispatchActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ParseUser.getCurrentUser() != null){
            Intent goToInside = new Intent(DispatchActivity.this, MainActivity.class);
            startActivity(goToInside);
        }else {
            Intent goToOutside = new Intent(DispatchActivity.this, MainActivity.class);
            startActivity(goToOutside);
        }
    }
}
