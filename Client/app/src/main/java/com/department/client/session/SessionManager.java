package com.department.client.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences("AppKey", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setLogin(Boolean login) {
        editor.putBoolean("KEY_LOGIN", login);
        editor.commit();
    }

    public boolean getLogin() {
        return sharedPreferences.getBoolean("KEY_LOGIN", false);
    }

    public void setUserId(int userId) {
        editor.putInt("KEY_USERID", userId);
        editor.commit();
    }

    public int getUserId() {
        return sharedPreferences.getInt("KEY_USERID", 0);
    }

    public void setFullName(String fullName) {
        editor.putString("KEY_FULLNAME", fullName);
        editor.commit();
    }

    public String getFullName() {
        return sharedPreferences.getString("KEY_FULLNAME", "");
    }
}
