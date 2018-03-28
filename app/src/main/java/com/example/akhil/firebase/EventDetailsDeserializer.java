package com.example.akhil.firebase;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 Created by akhil on 6/3/18.
 */

public class EventDetailsDeserializer implements JsonDeserializer<EventKeyModel> {
  /*
    bebebejunskjd:{
      "email": "akhilbv1@gmail.com",
          "mobileNum": "8341770556",
          "password": "1234567",
          "username": "akhil"}*/
  @Override public EventKeyModel deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {

    final JsonObject jsonObject = json.getAsJsonObject();

    Gson gson = new Gson();

    Type eventsDetailsBodyModel =
        new TypeToken<HashMap<String, EventsDetailsBodyModel>>(){}.getType();

    HashMap<String, EventsDetailsBodyModel> user =
        gson.fromJson(jsonObject, eventsDetailsBodyModel);
    EventKeyModel result = new EventKeyModel();
    result.setResult(user);
    return result;
  }


}
