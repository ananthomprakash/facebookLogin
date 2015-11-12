package com.om.user.project;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivityFragment extends Fragment {


    private  TextView tv1;

    private CallbackManager mcallbackmanager;
    private FacebookCallback<LoginResult> mcallback=new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken=loginResult.getAccessToken();
            Profile profile=Profile.getCurrentProfile();
            if(profile !=null){
                tv1.setText("Welcome"+profile.getName());
            }


        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize Facebook SDK before you can use it
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mcallbackmanager=CallbackManager.Factory.create();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton=(LoginButton)view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("User Permissions");
        loginButton.setFragment(this);
        loginButton.registerCallback(mcallbackmanager, mcallback);
        tv1=(TextView)view.findViewById(R.id.tv1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallbackmanager.onActivityResult(requestCode,resultCode,data);
    }
}
