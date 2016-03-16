package com.fatihsenturk.socialapp.Fragments.UserAuth;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fatihsenturk.socialapp.Model.CustomToast;
import com.fatihsenturk.socialapp.Acrivity.HomePageActivity;
import com.fatihsenturk.socialapp.MainActivity;
import com.fatihsenturk.socialapp.R;
import com.fatihsenturk.socialapp.Utils.Utils;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by TOSHIBA on 15.3.2016. Mart
 * Dont worry !
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private static View view;

    private static EditText  password, username;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    public LoginFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        return view;
    }
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        username = (EditText) view.findViewById(R.id.login_username);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view.findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        Resources res = getResources();
        XmlResourceParser xrp = res.getXml(R.xml.text_selector);

        try {
            ColorStateList colorStateList = ColorStateList.createFromXml(res,xrp);
            forgotPassword.setTextColor(colorStateList);
            show_hide_password.setTextColor(colorStateList);
            signUp.setTextColor(colorStateList);
        } catch  (Exception e) {
            e.printStackTrace();
        }

    }
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    show_hide_password.setText(R.string.hide_pwd);
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    show_hide_password.setText(R.string.show_pwd);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtn:
                checkValidation();
                break;
            case R.id.forgot_password:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new ForgotPasswordFragment(), Utils.ForgotPassword_Fragment)
                        .commit();
                break;
            case R.id.createAccount:

                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignUpFragment(), Utils.SignUp_Fragment)
                        .commit();
                break;
        }
    }

    private void checkValidation() {

        String getPassword = password.getText().toString().trim();
        String getUsername = username.getText().toString().trim();

        if (getUsername.equals("") || getUsername.length() == 0 || getPassword.equals("") || getPassword.length() == 0 ){
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(),view, getActivity().getString(R.string.check_login_both_field));
        }else {
            loginToParse(getUsername, getPassword);
        }

    }

    private void loginToParse(String getUsername, String getPassword) {
        ParseUser.logInInBackground(getUsername, getPassword, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null ){


//                  This part for emaial verification , succesfull login
//                    Boolean isVerified = parseUser.getBoolean("emailVerified");
//                    if (isVerified){
//                        Intent goToHomePage = new Intent(getActivity(), HomePageActivity.class);
//                        startActivity(goToHomePage);
//                    }else {
//                        ParseUser.logOut();
//                    }

                    MainActivity.editSharedPreference(true);
                    Intent goToHomePage = new Intent(getActivity(), HomePageActivity.class);
                    startActivity(goToHomePage);

                }else {
                    new CustomToast().Show_Toast(getContext(), view, e.getMessage());
                }
            }
        });
    }
}
