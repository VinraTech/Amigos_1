package com.amigos.amigos.Storage;

import android.content.Context;
import android.preference.PreferenceManager;

public class SharePrefernce {
    
    public static void setSharePrefernceData(Context ctx, String name, String data) {
        PreferenceManager.getDefaultSharedPreferences(ctx).edit().putString(name, data).commit();
    }
    
    public static String getSharePrefernceData(Context ctx, String name) {
        return PreferenceManager.getDefaultSharedPreferences(ctx).getString(name, "");
    }
    
    public static void setSharePrefernceBoolean(Context ctx, String name, boolean flag) {
        PreferenceManager.getDefaultSharedPreferences(ctx).edit().putBoolean(name, flag).commit();
    }
    
    public static boolean getSharePrefernceBoolean(Context ctx, String name) {
        return PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean(name, false);
    }
    
    public static void setSharePrefernceInt(Context ctx, String name, int flag) {
        PreferenceManager.getDefaultSharedPreferences(ctx).edit().putInt(name, flag).commit();
    }
    
    public static int getSharePrefernceInt(Context ctx, String name) {
        return PreferenceManager.getDefaultSharedPreferences(ctx).getInt(name, 0);
    }
    
}
