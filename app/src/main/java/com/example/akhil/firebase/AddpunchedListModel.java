package com.example.akhil.firebase;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by akhil on 28/3/18.
 */

public class AddpunchedListModel {

     /*"punchedlist": ["DP74O9bNLePs3NQYIJU41SyqLO62"]*/

     @SerializedName("punchedlist")
    private List<String> punchedList;

    public List<String> getPunchedList() {
        return punchedList;
    }

    public void setPunchedList(List<String> punchedList) {
        this.punchedList = punchedList;
    }
}
