package com.moelle.deepdarkness;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefManager {
    SharedPreferences PREFS;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "PREFS";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        PREFS = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = PREFS.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return PREFS.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}

