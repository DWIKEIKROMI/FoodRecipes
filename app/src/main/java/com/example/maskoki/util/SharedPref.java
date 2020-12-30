package com.example.maskoki.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    public static final String SP_MASKOKI_APP = "Maskoki";
    public static final String SP_LOGIN = "Login";
    public static final String SP_USERNAME = "spUsername";
    public static final String SP_TOKEN = "spToken";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPref(Context context) {
        sp = context.getSharedPreferences(SP_MASKOKI_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getUsername(){
        return sp.getString(SP_USERNAME, "");
    }

    public String getToken(){
        return sp.getString(SP_TOKEN, "");
    }


    public Boolean getLogin(){
        return sp.getBoolean(SP_LOGIN, false);
    }
}
