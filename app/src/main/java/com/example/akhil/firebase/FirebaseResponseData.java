package com.example.akhil.firebase;

import java.util.Map;

/**
 Created by akhil on 7/3/18.
 */

public class FirebaseResponseData {

  private Map<String,FirebaseDatabaseTableEventDetails> eventdetails;

  public Map<String, FirebaseDatabaseTableEventDetails> getEventdetails() {
    return eventdetails;
  }

  public void setEventdetails(Map<String, FirebaseDatabaseTableEventDetails> eventdetails) {
    this.eventdetails = eventdetails;
  }
}
