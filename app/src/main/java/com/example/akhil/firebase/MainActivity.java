package com.example.akhil.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import es.dmoral.toasty.Toasty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private TextInputEditText etEmail,etPassword;
  private Button btnLogin;
  private FirebaseAuth auth;



  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    auth = FirebaseAuth.getInstance();

    etEmail = findViewById(R.id.EditText_email);
    etPassword = findViewById(R.id.EditText_password);
    btnLogin = findViewById(R.id.button_login);
    btnLogin.setOnClickListener(this);
    btnLogin.setText("login");

  }

  @Override public void onClick(View v) {
      validateLogin();
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
      loginWithFirebase();
    }
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
  eventDetails.setEventName("sports");
  eventDetails.setEventDescription("enjoy sports and have fun");
  eventDetails.setEventData("12-08-18");
  eventDetails.setEventLocation("hyderabad");
  eventDetails.setEventImage("http://cdn.playbuzz.com/cdn/dc684763-c546-43fa-80b8-91c5627d00b7/0e1d574c-3cc1-4008-8c80-98e900f2b76e.jpg");
  List<String> mlist = new ArrayList<>();
  mlist.add(auth.getUid());
  eventDetails.setPunchedlist(mlist);
  String uid = mDatabase.push().getKey();
   mDatabase.child(uid).setValue(eventDetails);
  }


}
