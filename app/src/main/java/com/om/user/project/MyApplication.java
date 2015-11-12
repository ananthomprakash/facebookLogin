package com.om.user.project;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        printHashKey();
    }

    public void printHashKey(){
       try {
           PackageInfo info = getPackageManager().getPackageInfo(
                   "com.om.user.project",
                   PackageManager.GET_SIGNATURES);
           for (android.content.pm.Signature signature : info.signatures) {
               MessageDigest md = MessageDigest.getInstance("SHA");
               md.update(signature.toByteArray());
               Log.d("omprakash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
           }
       } catch (PackageManager.NameNotFoundException e) {

       } catch (NoSuchAlgorithmException e) {

       }
   }


}
