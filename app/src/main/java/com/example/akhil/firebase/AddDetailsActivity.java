package com.example.akhil.firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
  Created by akhil on 28/3/18.
 */

public class AddDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText etEmail,etUsername,etMobile,etPassword,etConfPassword;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddetails);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etMobile = findViewById(R.id.etMobileNum);
        etPassword = findViewById(R.id.etPassword);
        etConfPassword = findViewById(R.id.etConfPassword);
        register = findViewById(R.id.btn_register);
        register.setOnClickListener(this);
    }

    private void registerIntoFirebase(){
       RestClient restClient = new RestClient();

       LoginBody user = new LoginBody();
       user.setEmail(etEmail.getText().toString().trim());
       user.setPassword(etPassword.getText().toString().trim());
       user.setReturnSecureToken(true);

        Call<LoginResponse> call = restClient.getLoginService().registerUser(user);

      call.enqueue(new Callback<LoginResponse>() {
          @Override
          public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
              if(response.code()==200)
              {
                  registerIntoDb(response.body().getLocalId());
              }
          }

          @Override
          public void onFailure(Call<LoginResponse> call, Throwable t) {

          }
      });

    }

    private void registerIntoDb(String uid){

        RestClient client = new RestClient();
        String userid = uid+".json";

        UserDetailsModel detailsModel = new UserDetailsModel();
        detailsModel.setEmail(etEmail.getText().toString().trim());
        detailsModel.setUsername(etUsername.getText().toString().trim());
        detailsModel.setMobileNum(etMobile.getText().toString().trim());
        detailsModel.setPassword(etPassword.getText().toString().trim());
        Call<Void> call = client.getEventsService().registerUserDb(userid,detailsModel);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200)
                {
                    Toasty.success(AddDetailsActivity.this,"Success").show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toasty.error(AddDetailsActivity.this,"failure").show();

            }
        });

    }

    @Override
    public void onClick(View v) {
        registerIntoFirebase();

        Log.i("url",""+CommonUtils.randomImage());
    }
}
