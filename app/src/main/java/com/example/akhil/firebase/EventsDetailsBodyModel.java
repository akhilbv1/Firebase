package com.example.akhil.firebase;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by akhil on 28/3/18.
 */

public class EventsDetailsBodyModel implements Parcelable {

   /*  "eventId": "OwkBJ7",
   "eventData": "12-08-18",
             "eventDate": "12-08-18",
             "eventDescription": "enjoy sports and have fun",
             "eventImage": "http://orientindia.com/admin//130/evt_photo/4_event_marketing.jpg",
             "eventLocation": "hyderabad",
             "eventName": "sports and fun",
             "punchedlist": ["DP74O9bNLePs3NQYIJU41SyqLO62"*/

 @SerializedName("eventId")
 public String eventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @SerializedName("eventDate")
    public String eventDate;

    @SerializedName("eventDescription")
    public String eventDescription;

    @SerializedName("eventImage")
    public String eventImage;

    @SerializedName("eventLocation")
    public String eventLocation;

    @SerializedName("eventName")
    public String eventName;

    @SerializedName("punchedlist")
    public List<String> punchedList;

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<String> getPunchedList() {
        return punchedList;
    }

    public void setPunchedList(List<String> punchedList) {
        this.punchedList = punchedList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.eventDate);
        dest.writeString(this.eventDescription);
        dest.writeString(this.eventImage);
        dest.writeString(this.eventLocation);
        dest.writeString(this.eventName);
        dest.writeStringList(this.punchedList);
        dest.writeString(this.eventId);
    }

    public EventsDetailsBodyModel() {
    }

    protected EventsDetailsBodyModel(Parcel in) {
        this.eventDate = in.readString();
        this.eventDescription = in.readString();
        this.eventImage = in.readString();
        this.eventLocation = in.readString();
        this.eventName = in.readString();
        this.punchedList = in.createStringArrayList();
        this.eventId = in.readString();

    }

    public static final Parcelable.Creator<EventsDetailsBodyModel> CREATOR = new Parcelable.Creator<EventsDetailsBodyModel>() {
        @Override
        public EventsDetailsBodyModel createFromParcel(Parcel source) {
            return new EventsDetailsBodyModel(source);
        }

        @Override
        public EventsDetailsBodyModel[] newArray(int size) {
            return new EventsDetailsBodyModel[size];
        }
    };
}
