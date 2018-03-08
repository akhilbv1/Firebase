package com.example.akhil.firebase;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**Created by akhil on 7/3/18.
 */

public class FirebaseDatabaseTableEventDetails implements Parcelable {


  String eventName,eventDescription,eventDate,eventLocation,eventImage;
  List<String> punchedlist;

  public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public List<String> getPunchedlist() {
    return punchedlist;
  }

  public void setPunchedlist(List<String> punchedlist) {
    this.punchedlist = punchedlist;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getEventDescription() {
    return eventDescription;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
  }

  public String getEventData() {
    return eventDate;
  }

  public void setEventData(String eventData) {
    this.eventDate = eventData;
  }

  public String getEventLocation() {
    return eventLocation;
  }

  public void setEventLocation(String eventLocation) {
    this.eventLocation = eventLocation;
  }

  public String getEventImage() {
    return eventImage;
  }

  public void setEventImage(String eventImage) {
    this.eventImage = eventImage;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.eventName);
    dest.writeString(this.eventDescription);
    dest.writeString(this.eventDate);
    dest.writeString(this.eventLocation);
    dest.writeString(this.eventImage);
    dest.writeStringList(this.punchedlist);
  }

  public FirebaseDatabaseTableEventDetails() {
  }

  protected FirebaseDatabaseTableEventDetails(Parcel in) {
    this.eventName = in.readString();
    this.eventDescription = in.readString();
    this.eventDate = in.readString();
    this.eventLocation = in.readString();
    this.eventImage = in.readString();
    this.punchedlist = in.createStringArrayList();
  }

  public static final Parcelable.Creator<FirebaseDatabaseTableEventDetails> CREATOR =
      new Parcelable.Creator<FirebaseDatabaseTableEventDetails>() {
        @Override public FirebaseDatabaseTableEventDetails createFromParcel(Parcel source) {
          return new FirebaseDatabaseTableEventDetails(source);
        }

        @Override public FirebaseDatabaseTableEventDetails[] newArray(int size) {
          return new FirebaseDatabaseTableEventDetails[size];
        }
      };
}
