package com.moelle.deepdarkness;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.Key;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "LottieIntro";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }


    // this method will save the nightMode State : True or False
    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    // this method will load the Night Mode State
    public Boolean loadNightModeState() {
        Boolean state = pref.getBoolean("NightMode", false);
        return state;
    }

    //
    // this method will save the nightMode State : True or False

    public static final class Key {
        static final String COMMON = "COMMON";
    }

    public void setScale(String key, float value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat("DEFAULT", 0.7f).apply();
        editor.putFloat("COMMON", 1.2f).commit();
        editor.putFloat("SLOW", 2f).commit();
    }

}