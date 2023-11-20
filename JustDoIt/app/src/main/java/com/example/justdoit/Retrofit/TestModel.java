package com.example.justdoit.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestModel {
    @SerializedName("userId")
    @Expose
    private int userId;

    @SerializedName("title")
    @Expose
    private String title;

    public int getUserId() {
        return userId;
    }

    public String getTitle(){
        return title;
    }


}
