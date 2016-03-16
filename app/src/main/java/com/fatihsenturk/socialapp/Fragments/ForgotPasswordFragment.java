package com.fatihsenturk.socialapp.Fragments;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.fatihsenturk.socialapp.Model.CustomToast;
import com.fatihsenturk.socialapp.MainActivity;
import com.fatihsenturk.socialapp.R;
import com.fatihsenturk.socialapp.Utils.Utils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by TOSHIBA on 15.3.2016. Mart
 * Dont worry !
 */
public class ForgotPasswordFragment extends Fragment implements View.OnClickListener {

    private static View view;

    private static EditText emailId;
    private static TextView submit, back;

    public ForgotPasswordFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.forgotpassword_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        emailId = (EditText) view.findViewById(R.id.registered_emailid);
        submit = (TextView) view.findViewById(R.id.forgot_button);
        back = (TextView) view.findViewById(R.id.backToLoginBtn);

        Resources res = getResources();
        XmlResourceParser xrp = res.getXml(R.xml.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),xrp);

            back.setTextColor(csl);
            submit.setTextColor(csl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.backToLoginBtn:
                MainActivity.replaceLoginFragment();
                break;
            case R.id.forgot_button:
                submitButtonTask();
                break;
        }
    }

    private void submitButtonTask() {
        String getEmailId = emailId.getText().toString().trim();

        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        if (getEmailId.equals("") || getEmailId.length() == 0)

            new CustomToast().Show_Toast(getActivity(), view,
                    "Please enter your Email Id.");

        else if (!m.find())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");

        else
            sendResetRequestToParse(getEmailId);

    }

    private void sendResetRequestToParse(String getEmailId) {
        ParseUser.requestPasswordResetInBackground(getEmailId, new RequestPasswordResetCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    new CustomToast().Show_Toast(getActivity(), view, "Email adresinize şifre yenileme maili gönderilmiştir, " +
                            " Lütfen kontrol ediniz !");
                }else {
                    // see the error codes here : https://parse.com/docs/android/api/constant-values.html#com.parse.ParseException.EMAIL_NOT_FOUND
                    if (e.getCode() == 205 ){
                        new CustomToast().Show_Toast(getActivity(), view, "Bu email adresine ait kullanıcı bulunamamdı ! ");
                    }else {
                        new CustomToast().Show_Toast(getActivity(), view, e.getMessage());
                    }
                }
            }
        });
    }
}
