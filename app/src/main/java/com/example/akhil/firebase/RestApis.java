package com.example.akhil.firebase;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
  Created by akhil on 28/3/18.
 */

public interface RestApis {

    @POST("verifyPassword?key= AIzaSyAUjt3lOrt0cFwbbd5Ag8M1-ISzQlE0Wjg")
    Call<LoginResponse> loginUser(@Body LoginBody loginBody);

    @GET("events.json")
    Call<EventKeyModel> getAllEvents();

    @PATCH("events/{eventid}")
    Call<Void> punchit(@Path("eventid") String eventid,@Body AddpunchedListModel addpunchedListModel);

    @POST("signupNewUser?key= AIzaSyAUjt3lOrt0cFwbbd5Ag8M1-ISzQlE0Wjg")
    Call<LoginResponse> registerUser(@Body LoginBody loginBody);

    @POST("users/{uid}")
    Call<Void> registerUserDb(@Path("uid")String uid,@Body UserDetailsModel eventsDetailsBodyModel);
}
