package com.example.akhil.firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import es.dmoral.toasty.Toasty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**Created by akhil on 7/3/18.
 */

public class DetailsListActivity extends AppCompatActivity implements View.OnClickListener {

  RecyclerView recyclerView;
  EventDetailsListAdapter mAdapter;
  String uid;
  private List<FirebaseDatabaseTableEventDetails> mlist = new ArrayList<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.list_activity);
    recyclerView = findViewById(R.id.recyclerviewUsersList);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setHasFixedSize(true);
    getDataFromFirebase();
    FloatingActionButton fb = findViewById(R.id.button_fab);
    fb.setOnClickListener(this);

     uid = getIntent().getStringExtra("uid");
  }

  private void getDataFromFirebase(){
    DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference("events");
    mdatabase.addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {

        Iterable<DataSnapshot> details = dataSnapshot.getChildren();
        for (DataSnapshot data : details) {
          FirebaseDatabaseTableEventDetails c = data.getValue(FirebaseDatabaseTableEventDetails.class);
          Log.d("contact:: ", c.getEventName());
          mlist.add(c);
        }
        if(mlist.size()==0){
          Toasty.info(DetailsListActivity.this,"empty").show();
        }
        else {
        mAdapter = new EventDetailsListAdapter(mlist,DetailsListActivity.this,uid);
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
        }
      }

      @Override public void onCancelled(DatabaseError databaseError) {
      Toasty.error(DetailsListActivity.this,"fetching data failed").show();
      }
    });
  }

  @Override public void onClick(View v) {
    Toasty.info(this,"clicked").show();

  }
}
