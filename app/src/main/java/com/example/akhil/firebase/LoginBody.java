package com.example.akhil.firebase;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akhil on 28/3/18.
 */

public class LoginBody {
    /*{"email":"[user@example.com]",
            "password":"[PASSWORD]",
            "returnSecureToken":true}*/

     @SerializedName("email")
    public   String email;

     @SerializedName("password")
    public  String password;

     @SerializedName("returnSecureToken")
    public  boolean returnSecureToken;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isReturnSecureToken() {
        return returnSecureToken;
    }

    public void setReturnSecureToken(boolean returnSecureToken) {
        this.returnSecureToken = returnSecureToken;
    }
}
