package com.example.akhil.firebase;

import com.google.gson.annotations.SerializedName;

/**
 Created by akhil on 28/3/18.
 */

public class LoginResponse {
    /*"kind": "identitytoolkit#VerifyPasswordResponse",
    "localId": "BEGDHG0l8hRQA44GH2O3rSZIpzY2",
    "email": "akhil@gmail.com",
    "displayName": "",
    "idToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwOWZmZTJhN2NhYjMxMDA2MTlhNGY2MzBmZjVhOGU1ZWY0NTBiOTYifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vc2FtcGxlLWQ4OWY4IiwiYXVkIjoic2FtcGxlLWQ4OWY4IiwiYXV0aF90aW1lIjoxNTE5ODk3NDUxLCJ1c2VyX2lkIjoiQkVHREhHMGw4aFJRQTQ0R0gyTzNyU1pJcHpZMiIsInN1YiI6IkJFR0RIRzBsOGhSUUE0NEdIMk8zclNaSXB6WTIiLCJpYXQiOjE1MTk4OTc0NTEsImV4cCI6MTUxOTkwMTA1MSwiZW1haWwiOiJha2hpbEBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsiYWtoaWxAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.qLs3vY_ycASRtoMwqHKyVipaQ78192oeAjDjtMO2Nxm7kBbEWrNA8K3gR11HfzZy_t7BqmCyo896kvekfD-0TtF5ZRfxCaAp74q-iWnJFhZS1l4Q_N0xdPiiwOZEBWuKMHbPqVmzNvYqw_qeF-leV15NaeGjkYeJ0ESfOA5kgMhqEgTmLr4Fh9zh89dS1kNJleHMpR2IGXnV0dcmLGNiLucTZJlbO601b3lWvUPyDqygPN_4h0Kth3PCqraxg8P0MaLQn8LOhFFCM3j0epub96zR5QM-BYRXsU94cuTTkBu-60JGHjiekWHjY-yOLL9Vx3tZZqQHWrOK60DSYgxW0Q",
    "registered": true,
    "refreshToken": "APyOXy23pzgC7J2zNLqr5M3fHIMRAw-XNoXgnrAl4RHEM2SpRTRIJ8--pKprQUvH-bc-_wA7uSTtficOfndCEfYby4P0K0rQHJMl0AmremcEv07DwKzJ6xmC6TvozeXXEJQ-QhZKZ_-eiaa0bqpmOjraPapBPXeyyhmTva08fqznKw0C-wdICA4bLWK2-YvuDtveh61xxap9FmpmSEiENhqosEHcCfDc8A",
    "expiresIn": "3600"*/

    @SerializedName("kind")
    private  String kind;

    @SerializedName("localId")
    private  String localId;

    @SerializedName("email")
    private  String email;

    @SerializedName("idToken")
    private  String idToken;

    @SerializedName("registered")
    private  String isRegistered;

    @SerializedName("refershToken")
    private  boolean refreshToken;

    @SerializedName("expiresIn")
    private  String expiresIn;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(String isRegistered) {
        this.isRegistered = isRegistered;
    }

    public boolean isRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(boolean refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
