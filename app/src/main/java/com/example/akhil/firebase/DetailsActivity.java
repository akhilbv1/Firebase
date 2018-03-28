package com.example.akhil.firebase;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**Created by akhil on 8/3/18.
 */

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
  EventsDetailsBodyModel eventDetails;
  String uid;
  ImageView logo,punchIt;
  AppBarLayout appbar;
  Toolbar toolbar;
  CollapsingToolbarLayout collapsingToolbar;
  TextView txteventName;
  ImageButton btn_back;
  boolean isPunched;
  private AlertDialog dialog;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
      setContentView(R.layout.collapsing_layout_sample);
      super.onCreate(savedInstanceState);

      txteventName = findViewById(R.id.text_eventname);

      toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);


      logo = findViewById(R.id.image_collapsing_toolbar);
    TextView des = findViewById(R.id.text_eventDes);
    TextView loc = findViewById(R.id.text_eventlocation);

     eventDetails=getIntent().getParcelableExtra("details");
     uid = getIntent().getStringExtra("uid");

    des.setText("EventName: "+eventDetails.getEventDescription());
    loc.setText("EventDescription: "+eventDetails.getEventLocation());

     collapsingToolbar = findViewById(R.id.collapsing_toolbar);

     appbar = findViewById(R.id.appbar);

     punchIt = findViewById(R.id.punchit);
     punchIt.setOnClickListener(this);
     btn_back = findViewById(R.id.btn_back);

    collapsingToolbar.setExpandedTitleColor(Color.WHITE);

    setUpCollapsingToolbarImageLogo();
    setUpCollapsingToolbar();
      checkPunched();
    toolbar.setNavigationIcon(R.drawable.ic_back);
    toolbar.setNavigationOnClickListener(this);
    btn_back.setOnClickListener(this);


  }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setUpCollapsingToolbarImageLogo(){
    Glide.with(DetailsActivity.this)
        .load(eventDetails.getEventImage())
        .into(logo);
  }

  private void setUpCollapsingToolbar(){
    appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
      @Override public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0){
          toolbar.setVisibility(View.VISIBLE);
          collapsingToolbar.setTitle("Events Details");
          collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);

        }
        else {
            txteventName.setText(eventDetails.getEventName());
          collapsingToolbar.setTitle("");
          toolbar.setVisibility(View.INVISIBLE);
          btn_back.setVisibility(View.VISIBLE);
            toolbar.setTitleTextColor(Color.WHITE);

        }
      }
    });
  }
  @SuppressLint("NewApi")
  private void checkPunched(){
   if(eventDetails.getPunchedList().contains(uid) ){
     Toasty.info(this,"punched").show();
     punchIt.setImageDrawable(getDrawable(R.drawable.icons_punch));
     punchIt.setClickable(false);
   }
   else {
       punchIt.setImageDrawable(getDrawable(R.drawable.icons_didnotpunch));
   }

  }

  private void alertDialog(){
      AlertDialog.Builder builder = new AlertDialog.Builder(this);

      builder.setTitle("PunchIt")
              .setMessage("Do you want to punch this event")
              .setCancelable(false)
              .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                      punchIt();
                      Log.i("eventid",""+eventDetails.getEventId());
                  }
              })
              .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                      Toasty.info(DetailsActivity.this,"cancelled").show();
                  }
              });

      dialog = builder.create();
      dialog.show();

  }

    @Override
    public void onClick(View v) {
      if(v.getId()==R.id.btn_back){
          finish();
      }
      else if(v.getId()==R.id.punchit){
           alertDialog();
      }
      else {
          finish();
      }
    }


    private void punchIt(){
      RestClient restClient = new RestClient();

      List<String> punchedlist = new ArrayList<>();
      punchedlist.add(uid);

      AddpunchedListModel addpunchedList = new AddpunchedListModel();
      addpunchedList.setPunchedList(punchedlist);
      String eventId = eventDetails.getEventId()+".json";

        Call<Void> call = restClient.getEventsService().punchit(eventId,addpunchedList);

        call.enqueue(new Callback<Void>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200)
                {
                    Toasty.success(DetailsActivity.this,"Success").show();
                    punchIt.setImageDrawable(getDrawable(R.drawable.icons_punch));
                    punchIt.setClickable(false);                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toasty.error(DetailsActivity.this,"failed").show();

            }
        });
    }
}
