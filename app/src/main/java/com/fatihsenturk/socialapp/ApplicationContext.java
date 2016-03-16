package com.fatihsenturk.socialapp;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.fatihsenturk.socialapp.Model.StuffModel;
import com.fatihsenturk.socialapp.Utils.Helper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by TOSHIBA on 15.3.2016. Mart
 * Dont worry !
 */
public class ApplicationContext extends Application {

    private static Context context;
    public static DisplayImageOptions options;
    private static ApplicationContext mInstance;

    public static synchronized ApplicationContext getInstance() {
        return mInstance;
    }

    public static void initImageLoader(Context context) {

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        ImageLoader.getInstance().init(config.build());
    }



    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationContext.context = getApplicationContext();
        Helper.loadFonts();
        initImageLoader(getApplicationContext());

        mInstance = this;

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ParseObject.registerSubclass(StuffModel.class);
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

        ParseUser.enableAutomaticUser();
        ParseUser.enableRevocableSessionInBackground();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }


    public static Context getAppContext() {
        return ApplicationContext.context;
    }
}
