package com.fatihsenturk.socialapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by TOSHIBA on 15.3.2016. Mart
 * Dont worry !
 */
public class HomePageActivity extends AppCompatActivity {

    private Drawer leftMenuDrawer;
    private int defaultSelectedLeftItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_page_activity);
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        leftMenuDrawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.material_drawer_header)
                .withSelectedItem(defaultSelectedLeftItem)
                .withSelectedItemByPosition(defaultSelectedLeftItem)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.anasayfa_ihtiyacli)
                                .withIdentifier(R.string.anasayfa)
                                .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                        new PrimaryDrawerItem()
                                .withName(R.string.isteklerim)
                                .withIdentifier(R.string.anasayfa)
                                .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                        new PrimaryDrawerItem()
                                .withName(R.string.aldiklarim)
                                .withIdentifier(R.string.anasayfa)
                                .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                        new PrimaryDrawerItem()
                                .withName(R.string.iletisim)
                                .withIdentifier(R.string.anasayfa)
                                .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (drawerItem.getIdentifier()){
                            case R.string.anasayfa_ihtiyacli:

                                break;
                            case R.string.aldiklarim:

                                break;
                            case R.string.iletisim:

                                break;
                            case R.string.isteklerim:

                                break;
                        }

                        return false;
                    }
                })
                .build();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
