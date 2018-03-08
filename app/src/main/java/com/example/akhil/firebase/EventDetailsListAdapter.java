package com.example.akhil.firebase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.zip.Inflater;

import static android.support.v4.content.res.ResourcesCompat.getDrawable;

/**
 *Created by akhil on 7/3/18.
 */

public class EventDetailsListAdapter extends RecyclerView.Adapter<EventDetailsListAdapter.MyViewHolder> {
  private List<FirebaseDatabaseTableEventDetails> mlist;
  private Context context;
  private String uid;

  public EventDetailsListAdapter(List<FirebaseDatabaseTableEventDetails> mlist,Context context,String uid){
    this.mlist = mlist;
    this.context = context;
    this.uid = uid;
    notifyDataSetChanged();
  }
  @Override
  public EventDetailsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_latest,parent,false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(EventDetailsListAdapter.MyViewHolder holder, int position) {
       holder.UpdateUI(position);
  }

  @Override public int getItemCount() {
    return mlist.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
     TextView txt_Name,txt_Date,txt_location;
     ImageView img_eventlogo;
     CardView card;
       FirebaseDatabaseTableEventDetails eventDetails;
     public MyViewHolder(View itemView) {
      super(itemView);
      txt_Name = itemView.findViewById(R.id.text_eventname);
      txt_Date = itemView.findViewById(R.id.text_eventdate);
      txt_location = itemView.findViewById(R.id.text_eventlocation);
      img_eventlogo = itemView.findViewById(R.id.image_event);
      card = itemView.findViewById(R.id.cardview);
      itemView.setOnClickListener(this);
    }

    public void UpdateUI(int position){

       eventDetails = mlist.get(position);
       txt_Name.setText(eventDetails.getEventName());
       txt_Date.setText(eventDetails.getEventData());
       txt_location.setText(eventDetails.getEventLocation());
      Glide.with(context)
           .load(eventDetails.getEventImage())
           .into(img_eventlogo);
    }

    @Override public void onClick(View v) {
      Intent intent = new Intent(context,DetailsActivity.class);
      intent.putExtra("details",eventDetails);
      intent.putExtra("uid",uid);
      context.startActivity(intent);
    }
  }
}
