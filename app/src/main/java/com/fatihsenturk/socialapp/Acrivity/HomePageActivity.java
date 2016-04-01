package com.fatihsenturk.socialapp.Acrivity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.fatihsenturk.socialapp.ApplicationContext;
import com.fatihsenturk.socialapp.Fragments.Admin.AddAdmin;
import com.fatihsenturk.socialapp.Fragments.Admin.ApproveItemRequest;
import com.fatihsenturk.socialapp.Fragments.Admin.ApproveNormalUser;
import com.fatihsenturk.socialapp.Fragments.Admin.ApproveStuff;
import com.fatihsenturk.socialapp.Fragments.Admin.ApproveVolunteer;
import com.fatihsenturk.socialapp.Fragments.ContactFragment;
import com.fatihsenturk.socialapp.Fragments.DashboardFragment;
import com.fatihsenturk.socialapp.Fragments.NormalUser.MyItemsFragment;
import com.fatihsenturk.socialapp.Fragments.NormalUser.MyRequestFragment;
import com.fatihsenturk.socialapp.Fragments.Volunteer.Allitems;
import com.fatihsenturk.socialapp.Fragments.Volunteer.MyStuffs;
import com.fatihsenturk.socialapp.Fragments.Volunteer.WaitnigAllow;
import com.fatihsenturk.socialapp.R;
import com.fatihsenturk.socialapp.Utils.Helper;
import com.fatihsenturk.socialapp.Utils.Utils;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.parse.Parse;
import com.parse.ParseUser;

import java.nio.BufferOverflowException;

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

//        populateLeftMenu(leftMenuDrawer);
//        Boolean isAdmin = ApplicationContext.loggedInUser.getBoolean("isAdmin");
//        String userStatus = ApplicationContext.loggedInUser.getString("userStatus");
//        Boolean isAdmin = ApplicationContext.isAdmin;
//        ParseUser cuurenUser = ApplicationContext.loggedInUser;
//        Object aBoolean = cuurenUser.get("isVolunteer");
//        String hay = (String) cuurenUser.get("isVolunteer");
//        aBoolean.toString();
//        String durum = cuurenUser.getString("isVolunteer");
//        aBoolean.equals(true);
//        Boolean testFlag = cuurenUser.getBoolean("isVolunteer");
//        Boolean testGlag2 = ApplicationContext.userType;
//        Boolean userStatus = ParseUser.getCurrentUser().getBoolean("isVolunteer");

        ApplicationContext applicationContext = (ApplicationContext) getApplicationContext();
        ParseUser currentUser = applicationContext.getLoggedInUser();
        Boolean currentUserType = applicationContext.getUserType();
        Boolean isAdmin = applicationContext.getIsAdmin();

        if (isAdmin){

            leftMenuDrawer = new DrawerBuilder()
                    .withActivity(this)
                    .withHeader(R.layout.material_drawer_header)
                    .withSelectedItem(defaultSelectedLeftItem)
                    .withSelectedItemByPosition(defaultSelectedLeftItem)
                    .withSliderBackgroundColorRes(R.color.sol_meu_backround)
                    .withSliderBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.list_row_start_color))
                    .addDrawerItems(
                            new PrimaryDrawerItem()
                                    .withName(R.string.anasayfa_admin)
                                    .withIdentifier(R.string.anasayfa_admin)
                                    .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                    .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                    .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                            new PrimaryDrawerItem()
                                    .withName(R.string.kullanici_onayla_gonullu)
                                    .withIdentifier(R.string.kullanici_onayla_gonullu)
                                    .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                    .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                    .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                            new PrimaryDrawerItem()
                                    .withName(R.string.kullanici_onayla_ihtiyacli)
                                    .withIdentifier(R.string.kullanici_onayla_ihtiyacli)
                                    .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                    .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                    .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                            new PrimaryDrawerItem()
                                    .withName(R.string.esya_onayla)
                                    .withIdentifier(R.string.esya_onayla)
                                    .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                    .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                    .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                            new PrimaryDrawerItem()
                                    .withName(R.string.eslesme_onayla)
                                    .withIdentifier(R.string.eslesme_onayla)
                                    .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                    .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                    .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                            new PrimaryDrawerItem()
                                    .withName(R.string.admin_ekle)
                                    .withIdentifier(R.string.admin_ekle)
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

                                case R.string.anasayfa_admin:
                                    DashboardFragment dashboardFragment = new DashboardFragment();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, dashboardFragment);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                                case R.string.kullanici_onayla_gonullu:
                                    ApproveVolunteer approveVolunteer = new ApproveVolunteer();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, approveVolunteer);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();

                                    break;
                                case R.string.kullanici_onayla_ihtiyacli:
                                    ApproveNormalUser approveNormalUser = new ApproveNormalUser();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, approveNormalUser);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();

                                    break;
                                case R.string.esya_onayla:
                                    ApproveStuff approveStuff = new ApproveStuff();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, approveStuff);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                                case R.string.eslesme_onayla:
                                    ApproveItemRequest approveItemRequest = new ApproveItemRequest();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, approveItemRequest);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                                case R.string.admin_ekle:
                                    AddAdmin addAdmin = new AddAdmin();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, addAdmin);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                                case R.string.iletisim:
                                    ContactFragment contactFragment = new ContactFragment();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, contactFragment);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                            }

                            sendNotifyList();

                            return false;
                        }
                    })
                    .build();

        }else if (currentUserType){

            leftMenuDrawer = new DrawerBuilder()
                    .withActivity(this)
                    .withHeader(R.layout.material_drawer_header)
                    .withSelectedItem(defaultSelectedLeftItem)

                    .withSelectedItemByPosition(defaultSelectedLeftItem)
                    .withSliderBackgroundColorRes(R.color.sol_meu_backround)
                    .withSelectedItemByPosition(defaultSelectedLeftItem)
                    .addDrawerItems(
                            new PrimaryDrawerItem()
                                    .withName(R.string.anasayfa_gonullu)
                                    .withBadge(new StringHolder("2"))
                                    .withIdentifier(R.string.anasayfa_gonullu)
                                    .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                    .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                    .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                            new PrimaryDrawerItem()
                                    .withName(R.string.esyalar)
                                    .withIdentifier(R.string.esyalar)
                                    .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                    .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                    .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                            new PrimaryDrawerItem()
                                    .withName(R.string.benim_esyalarim)
                                    .withIdentifier(R.string.benim_esyalarim)
                                    .withSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar))
                                    .withSelectedColorRes(R.color.sag_menu_tiklaninca).withTypeface(Helper.textHeavyFont)
                                    .withTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sol_menu_yazilar)),

                            new PrimaryDrawerItem()
                                    .withName(R.string.onay_bekleyenler)
                                    .withIdentifier(R.string.onay_bekleyenler)
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

                                case R.string.anasayfa_gonullu:
                                    DashboardFragment dashboardFragment = new DashboardFragment();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, dashboardFragment);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                                case R.string.esyalar:
                                    Allitems allitems = new Allitems();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, allitems);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                                case R.string.benim_esyalarim:
                                    MyStuffs myStuffs = new MyStuffs();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, myStuffs);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;

                                case R.string.onay_bekleyenler:
                                    WaitnigAllow waitnigAllow = new WaitnigAllow();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, waitnigAllow);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;

                                case R.string.iletisim:
                                    ContactFragment contactFragment = new ContactFragment();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, contactFragment);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                            }
                            sendNotifyList();
                            return false;


                        }
                    })
                    .build();

        }else {

            leftMenuDrawer = new DrawerBuilder()
                    .withActivity(this)
                    .withHeader(R.layout.material_drawer_header)
                    .withSelectedItem(defaultSelectedLeftItem)
                    .withSelectedItemByPosition(defaultSelectedLeftItem)
                    .withSliderBackgroundColorRes(R.color.sol_meu_backround)
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
                                    MyItemsFragment myItemsFragment = new MyItemsFragment();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, myItemsFragment);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                                case R.string.isteklerim:
                                    MyRequestFragment myRequestFragment = new MyRequestFragment();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, myRequestFragment);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                                case R.string.iletisim:
                                    ContactFragment contactFragment = new ContactFragment();
                                    fragmentTransactionForDrawerItem.replace(R.id.frame_container, contactFragment);
                                    fragmentTransactionForDrawerItem.addToBackStack(null);
                                    fragmentTransactionForDrawerItem.commit();
                                    break;
                            }

                            sendNotifyList();
                            return false;
                        }
                    })
                    .build();

            leftMenuDrawer.updateBadge(R.string.anasayfa_gonullu, new StringHolder("12"));
        }
    }


    public void sendNotifyList() {
        Intent sendModify = new Intent();
        sendModify.setAction(Utils.SendBroadcat);
        sendBroadcast(sendModify);
    }


    // TODO :
//    private void populateLeftMenu(Drawer leftMenuDrawer) {
//        Boolean isAdmin = ApplicationContext.loggedInUser.getBoolean("isAdmin");
//        String userStatus = ApplicationContext.loggedInUser.getString("userStatus");
//
//        if (isAdmin){
//            //populate left menu for admin
//        }else if (userStatus.equals("volunteer")){
//            //populate left menu for volunteer
//        }else {
//            //populate left menu for the other users
//        }
//
//
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
