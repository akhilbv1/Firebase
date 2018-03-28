package com.example.akhil.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private TextInputEditText etEmail,etPassword;
  private Button btnLogin;
  private FirebaseAuth auth;
  private TextView register;



  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    auth = FirebaseAuth.getInstance();

    etEmail = findViewById(R.id.EditText_email);
    etPassword = findViewById(R.id.EditText_password);
    btnLogin = findViewById(R.id.button_login);
    register = findViewById(R.id.register);
    btnLogin.setOnClickListener(this);
    register.setOnClickListener(this);
    btnLogin.setText("login");
    //insertIntoFirebase();

  }

  @Override public void onClick(View v) {
      if(v.getId()==R.id.button_login)
      validateLogin();
      else if(v.getId()==R.id.register) {
          Intent intent = new Intent(this, RegisterUser.class);
           startActivity(intent);
      }

  }

  private void validateLogin(){
    if(TextUtils.isEmpty(etEmail.getText().toString().trim())){
      etEmail.setError("enter email");
      Toasty.error(this,"please enter the email").show();
    }
    else if(TextUtils.isEmpty(etPassword.getText().toString().trim())){
      etPassword.setError("enter password");
    }
    else {
    //  loginWithFirebase();
        loginWithFirebaseRest();
    }
  }

  private void loginWithFirebaseRest(){


      RestClient restClient = new RestClient();

      LoginBody user = new LoginBody();
      user.setEmail(etEmail.getText().toString().trim());
      user.setPassword(etPassword.getText().toString().trim());
      user.setReturnSecureToken(true);
      Call<LoginResponse> call = restClient.getLoginService().loginUser(user);
      call.enqueue(new Callback<LoginResponse>() {
          @Override
          public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
              if(response.code()==200)
              {
                Toasty.success(MainActivity.this,"login success").show();
                LoginResponse loginResponse = response.body();
                String uid = loginResponse.getLocalId();
                Intent intent = new Intent(MainActivity.this,DetailsListActivity.class);
                intent.putExtra("uid",uid);
                startActivity(intent);

              }
          }

          @Override
          public void onFailure(Call<LoginResponse> call, Throwable t) {

          }
      });

  }


  private void loginWithFirebase() {
       String email = etEmail.getText().toString().trim();
       String password = etPassword.getText().toString().trim();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
              Toasty.success(MainActivity.this,"login Success").show();
              String uid = auth.getUid();
              Intent intent = new Intent(MainActivity.this,DetailsListActivity.class);
              intent.putExtra("uid",uid);
              startActivity(intent);
            }
            else {
              Toasty.error(MainActivity.this,"failed").show();
            }
          }
        });
  }

private void insertIntoFirebase(){
  DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("events");
  FirebaseDatabaseTableEventDetails eventDetails = new FirebaseDatabaseTableEventDetails();
  String eventId = CommonUtils.randomString(6);
  eventDetails.setEventId(eventId);
  eventDetails.setEventName("sports");
  eventDetails.setEventDescription("enjoy sports and have fun");
  eventDetails.setEventData("12-08-18");
  eventDetails.setEventLocation("hyderabad");
  eventDetails.setEventImage("http://cdn.playbuzz.com/cdn/dc684763-c546-43fa-80b8-91c5627d00b7/0e1d574c-3cc1-4008-8c80-98e900f2b76e.jpg");
  List<String> mlist = new ArrayList<>();
  mlist.add("");
  eventDetails.setPunchedlist(mlist);
  String uid = mDatabase.push().getKey();
   mDatabase.child(eventId).setValue(eventDetails);
  }


}
