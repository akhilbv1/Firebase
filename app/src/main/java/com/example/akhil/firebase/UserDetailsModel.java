package com.example.akhil.firebase;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akhil on 28/3/18.
 */

public class UserDetailsModel {

    @SerializedName("username")
    public String username;

    @SerializedName("email")
    public String email;

    @SerializedName("mobileNum")
    public String mobileNum;

    @SerializedName("password")
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
