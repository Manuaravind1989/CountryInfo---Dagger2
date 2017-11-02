package com.mobiledev.countrylist.storage;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by Manu on 10/31/2017.
 */

public class PreferenceManager {

    private SharedPreferences mSharedPreferences;

    @Inject
    public PreferenceManager(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public void putData(String key, int data) {
        mSharedPreferences.edit().putInt(key,data).apply();
    }

    public int getData(String key) {
        return mSharedPreferences.getInt(key,0);
    }
}
