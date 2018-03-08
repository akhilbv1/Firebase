package com.example.akhil.firebase;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import es.dmoral.toasty.Toasty;
import java.util.List;

/**Created by akhil on 8/3/18.
 */

public class DetailsActivity extends AppCompatActivity  {
  FirebaseDatabaseTableEventDetails eventDetails;
  String uid;
  ImageView logo;
  AppBarLayout appbar;
  Toolbar toolbar;
  CollapsingToolbarLayout collapsingToolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.collapsing_layout_sample);
      toolbar = findViewById(R.id.toolbar);
     setSupportActionBar(toolbar);
     toolbar.setBackgroundColor(Color.WHITE);

    logo = findViewById(R.id.image_collapsing_toolbar);
    TextView des = findViewById(R.id.text_eventDes);
    TextView loc = findViewById(R.id.text_eventlocation);

     eventDetails=getIntent().getParcelableExtra("details");
     uid = getIntent().getStringExtra("uid");

    des.setText("EventName: "+eventDetails.getEventDescription());
    loc.setText("EventDescription: "+eventDetails.getEventLocation());

     collapsingToolbar = findViewById(R.id.collapsing_toolbar);

     appbar = findViewById(R.id.appbar);

    collapsingToolbar.setExpandedTitleColor(Color.WHITE);

    setUpCollapsingToolbarImageLogo();
    setUpCollapsingToolbar();
    checkPunched();

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
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
          collapsingToolbar.setTitle("collapsing toolbar");
          toolbar.setVisibility(View.VISIBLE);
          
        }
        else {
          collapsingToolbar.setTitle("collapsing toolbar");
          toolbar.setVisibility(View.INVISIBLE);
        }
      }
    });
  }
  private void checkPunched(){
   if(eventDetails.getPunchedlist().contains(uid) ){
     Toasty.info(this,"punched").show();
   }

  }
}
