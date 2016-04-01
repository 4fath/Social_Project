package com.fatihsenturk.socialapp.Fragments.UserAuth;

import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fatihsenturk.socialapp.Model.CustomToast;
import com.fatihsenturk.socialapp.MainActivity;
import com.fatihsenturk.socialapp.R;
import com.fatihsenturk.socialapp.Utils.Utils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;
import java.util.TooManyListenersException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by TOSHIBA on 15.3.2016. Mart
 * Dont worry !
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {

    private static View view;
    private static EditText fullName, username, emailId, mobileNumber, password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions, userType ;

    public SignUpFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        fullName = (EditText) view.findViewById(R.id.fullName);
        username = (EditText) view.findViewById(R.id.usernameId);
        emailId = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);
        userType = (CheckBox) view.findViewById(R.id.user_type);


        Resources res = getResources();
        XmlResourceParser xrp = res.getXml(R.xml.text_selector);

        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
            login.setTextColor(csl);
            terms_conditions.setTextColor(csl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:
                checkValidation();
                break;
            case R.id.already_user:
                MainActivity.replaceLoginFragment();
                break;
        }
    }

    private void checkValidation() {

        // Get all edit text
        String getFullName = fullName.getText().toString().trim();
        String getUsername = username.getText().toString().trim();
        String getEmailId = emailId.getText().toString().trim();
        String getMobileNumber = mobileNumber.getText().toString().trim();
        String getPassword = password.getText().toString().trim();
        String getConfirmPassword = confirmPassword.getText().toString().trim();

        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        if (getFullName.equals("") || getFullName.length() == 0 || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0 ||getUsername.equals("")|| getUsername.length() == 0
                || getPassword.equals("") || getPassword.length() == 0 || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0)
                new CustomToast().Show_Toast(getActivity(),view, "Tüm alanların doldurulması zorunludur !");
        else if (!m.find())
            new CustomToast().Show_Toast(getActivity(), view, "Geçersiz email adresi lütfen control ediniz..");
        else if (!getConfirmPassword.equals(getPassword))
            new CustomToast().Show_Toast(getActivity(), view, "Sifreler eslesmiyor");
        else if (!terms_conditions.isChecked())
            new CustomToast().Show_Toast(getActivity(), view, "Please select Terms and Conditions.");
        else {
            Boolean userTypee = userType.isChecked();
            signUpToParse(getFullName, getUsername, getEmailId, getMobileNumber, getPassword, userTypee);
        }

    }

    private void signUpToParse(String getFullName, String getUsername, String getEmailId, String getMobileNumber,
                               String getPassword, Boolean userType) {
        ParseUser currentUser = new ParseUser();

        // Required fields
        currentUser.setEmail(getEmailId);
        currentUser.setUsername(getUsername);
        currentUser.setPassword(getPassword);

        // Custom fields PUT ile isler yuruyecek
        currentUser.put("fullName",getFullName);
        currentUser.put("phoneNumber", getMobileNumber);
        currentUser.put("isAdmin", false);
        currentUser.put("userType", userType);

//        if (userType){
//            currentUser.add("isVolunteer", "yes");
//        }else{
//            currentUser.add("isVolunteer", "no");
//        }

//        currentUser.add("test1", "test 1 add kullanildi");
//        currentUser.put("test2", "test 2 put kullanildi");
//
//        currentUser.add("test 1 boolean", true);
//        currentUser.put("test 2 boolean", true);
//
//        currentUser.put("myStringValue","heyheyput");
//        currentUser.add("myStringValue","heyheyadd");
//        final ParseObject myNewParseObject = new ParseObject("myNewParseObject");
//
//        // add olunca array olarak kayıt ediyor
//        myNewParseObject.add("test1", "test 1 string");
//        myNewParseObject.add("test2", true);
//
//        // put olunca doğru
//        myNewParseObject.put("test3", "test 3 string");
//        myNewParseObject.put("test4", true);
//        myNewParseObject.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null){
//                    Toast.makeText(getActivity(), "obje kayit sirasinda hata OLMADI",Toast.LENGTH_LONG).show();
//                    ParseQuery<ParseObject> parseObjectParseQuery = new ParseQuery<ParseObject>("myNewParseObject");
//                    final String verbana = myNewParseObject.getObjectId();
//                }
//            }
//        });
//        if (userType){
//            currentUser.put(Utils.userStatus, Utils.gonulluUser);
//        }else {
//            currentUser.put(Utils.userStatus, Utils.ihtiyacliUeer);
//        }

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"Lütfen Bekleyiniz!","Kayit işlemi devam ediyor");
        currentUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                progressDialog.dismiss();
                if (e != null) {
                    new CustomToast().Show_Toast(getActivity(), view, e.getMessage());
                } else {
                    new CustomToast().Show_Toast(getContext(), view, "Başarılı bir şekilde kayıt oldunuz !");
                    MainActivity.replaceLoginFragment();
                }
            }
        });

    }
}
