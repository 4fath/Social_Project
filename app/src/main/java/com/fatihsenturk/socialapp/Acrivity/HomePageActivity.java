package com.fatihsenturk.socialapp.Acrivity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.fatihsenturk.socialapp.Fragments.DashboardFragment;
import com.fatihsenturk.socialapp.R;
import com.fatihsenturk.socialapp.Utils.Helper;
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
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_header, null);
        ImageButton menu = (ImageButton) view.findViewById(R.id.menu_icon);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setCustomView(view);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);

            menu.setBackground(ContextCompat.getDrawable(this, R.drawable.menuicon));


            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!leftMenuDrawer.isDrawerOpen()) {
                        leftMenuDrawer.openDrawer();

                    }
                }
        });
            getSupportActionBar().show();
        }

        leftMenuDrawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.material_drawer_header)
                .withSelectedItem(defaultSelectedLeftItem)
                .withSelectedItemByPosition(defaultSelectedLeftItem)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.anasayfa_ihtiyacli)
                                .withIdentifier(R.string.anasayfa_ihtiyacli)
                                .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                        new PrimaryDrawerItem()
                                .withName(R.string.isteklerim)
                                .withIdentifier(R.string.anasayfa_ihtiyacli)
                                .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                        new PrimaryDrawerItem()
                                .withName(R.string.aldiklarim)
                                .withIdentifier(R.string.anasayfa_ihtiyacli)
                                .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                        new PrimaryDrawerItem()
                                .withName(R.string.iletisim)
                                .withIdentifier(R.string.anasayfa_ihtiyacli)
                                .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        final FragmentManager fragmentManagerForDrawerItem = getFragmentManager();
                        final android.app.FragmentTransaction fragmentTransactionForDrawerItem = fragmentManagerForDrawerItem.beginTransaction();
                        switch (drawerItem.getIdentifier()){
                            case R.string.anasayfa_ihtiyacli:
                                DashboardFragment dashboardFragment = new DashboardFragment();
                                fragmentTransactionForDrawerItem.replace(R.id.frame_container, dashboardFragment);
                                fragmentTransactionForDrawerItem.addToBackStack(null);
                                fragmentTransactionForDrawerItem.commit();

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
