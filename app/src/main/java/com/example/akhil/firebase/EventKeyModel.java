package com.example.akhil.firebase;

import java.util.HashMap;

/**
 * Created by akhil on 28/3/18.
 */

public class EventKeyModel {
    public HashMap<String,EventsDetailsBodyModel> result;

    public HashMap<String, EventsDetailsBodyModel> getResult() {
        return result;
    }

    public void setResult(HashMap<String, EventsDetailsBodyModel> result) {
        this.result = result;
    }
}
