package com.fatihsenturk.socialapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by TOSHIBA on 16.3.2016. Mart
 * Dont worry !
 */
public class Helper  {

    public static final String TEXT_LIGT_FONT = "fonts/sf_ui_text_light_webfont.ttf";
    public static final String TEXT_HEAVY_LIGHT = "fonts/sf-ui-text-heavy-webfont.ttf";
    public static final String TEXT_REGULAR_FONT = "fonts/sf-ui-text-regular-webfont.ttf";
    public static final String TEXT_MEDIUM_FONT = "fonts/sf-ui-text-medium-webfont.ttf";
    public static final String TEXT_SEMIBOLD_FONT = "fonts/sf-ui-text-semibold-webfont.ttf";

    public static Typeface textLigtFont, textHeavyFont, textRegularFont, textMediumFont, textSemiboldFont;

    public static void loadFonts() {

        textLigtFont = Typeface.createFromAsset(ApplicationContext.getAppContext().getAssets(), Helper.TEXT_LIGT_FONT);
        textHeavyFont = Typeface.createFromAsset(ApplicationContext.getAppContext().getAssets(), Helper.TEXT_HEAVY_LIGHT);
        textRegularFont = Typeface.createFromAsset(ApplicationContext.getAppContext().getAssets(), Helper.TEXT_REGULAR_FONT);
        textMediumFont = Typeface.createFromAsset(ApplicationContext.getAppContext().getAssets(), Helper.TEXT_MEDIUM_FONT);
        textSemiboldFont = Typeface.createFromAsset(ApplicationContext.getAppContext().getAssets(), Helper.TEXT_SEMIBOLD_FONT);
    }

    @SuppressWarnings("ConstantConditions")
    public static void setFontAllView(ViewGroup parent) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                setFontAllView((ViewGroup) child);
            } else if (child != null) {
                Helper.setFontView(child);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static void setFontView(View child) {
        Typeface face;

        if(child.getTag() != null
                && child.getTag().toString().toLowerCase()
                .equals("text_light_font")) {
            face = textLigtFont;
        }else if (child.getTag() != null
                && child.getTag().toString().toLowerCase()
                .equals("text_regular_font")){
            face = textRegularFont;
        }else if (child.getTag() != null
                && child.getTag().toString().toLowerCase()
                .equals("text_medium_font")){
            face = textMediumFont;
        }else if (child.getTag() != null
                && child.getTag().toString().toLowerCase()
                .equals("text_semibold_font")){
            face = textSemiboldFont;
        }else if (child.getTag() != null
                && child.getTag().toString().toLowerCase()
                .equals("text_heavy_font")){
            face = textHeavyFont;
        } else{
            face = textLigtFont;
        }

        if (child instanceof TextView) {
            TextView textView = (TextView) child;
            textView.setTypeface(face);
        } else if (child instanceof EditText) {
            EditText editView = (EditText) child;
            editView.setTypeface(face);
        } else if (child instanceof RadioButton) {
            RadioButton radioView = (RadioButton) child;
            radioView.setTypeface(face);
        } else if (child instanceof CheckBox) {
            CheckBox checkboxView = (CheckBox) child;
            checkboxView.setTypeface(face);
        }
    }

    /**
     * Unix Timestamp to Date
     * @param timestamp timestamp
     * @return date
     */
    public static Date unixToDate(Long timestamp){
        return new Date(timestamp);
    }

    /**
     * Unix Timestamp to Date
     * @param millisecond timestamp
     * @return date
     */
    public static Date milisecondToDate(Long millisecond){
        return new Date(millisecond);
    }

    /**
     * Date to Unix Timestamp
     * @param date date
     * @return unix timestamp
     */

    public static Long dateToUnix(Date date){
        return date.getTime()/1000l;
    }

    /**
     * Date to String
     * @param date date
     * @return string
     */

    // TODO : remove context add format : define formats as constants

    public static String getDateString(Context context, Date date){
        if (date != null) {
            DateFormat df = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return df.format(date);
        } else {
            return "-";
        }
    }

    public static String getDateWithDayMounth(Context context, Date date){
        if (date != null){
            DateFormat dateFormat = new SimpleDateFormat("d MMMM", Locale.getDefault());
            return dateFormat.format(date);
        }else {
            return "-";
        }
    }

    public static String getDateDayMounthYear(Context context, Date date){
        if (date != null){
            DateFormat dateFormat = new SimpleDateFormat("d MMMM y", Locale.getDefault());
            return dateFormat.format(date);
        }else {
            return "-";
        }
    }

}
