package com.fatihsenturk.socialapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.fatihsenturk.socialapp.Acrivity.HomePageActivity;
import com.fatihsenturk.socialapp.Fragments.LoginFragment;
import com.fatihsenturk.socialapp.Utils.Utils;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static void setSharedPreferences(SharedPreferences sharedPreferences) {
        MainActivity.sharedPreferences = sharedPreferences;
    }

    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        sharedPreferences = getSharedPreferences("teamType", MODE_PRIVATE);
        String loginStatus = sharedPreferences.getString(Utils.Login_Status, null);

        if (loginStatus == null){

        }else {

        }


        ParseUser currentUser = ParseUser.getCurrentUser();
        String test = currentUser.getUsername();

        if (test == null){
            if (savedInstanceState == null){
                fragmentManager.
                        beginTransaction().
                        replace(R.id.frameContainer, new LoginFragment(), Utils.Login_Fragment).
                        commit();
            }
        }else {
            Intent goToHomePage = new Intent(this, HomePageActivity.class);
            startActivity(goToHomePage);
        }

        findViewById(R.id.close_activity).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public static void editSharedPreference(boolean loginStatus){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Utils.Login_Status,loginStatus);
        editor.apply();
    }

    @Override
    public void onBackPressed() {

        Fragment SignUp_Fragment = fragmentManager.findFragmentByTag(Utils.SignUp_Fragment);
        Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Utils.ForgotPassword_Fragment);

        if (SignUp_Fragment != null){
            replaceLoginFragment();
        }else if (ForgotPassword_Fragment != null ){
            replaceLoginFragment();
        }else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        Toast.makeText(this, "işte burası", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }

    public static void replaceLoginFragment() {
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer,new LoginFragment(),Utils.Login_Fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
