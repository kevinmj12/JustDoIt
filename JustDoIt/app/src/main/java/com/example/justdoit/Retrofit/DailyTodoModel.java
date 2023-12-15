package com.example.justdoit.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyTodoModel {
    @SerializedName("user_id")
    @Expose
    private int user_id;

    @SerializedName("todo_name")
    @Expose
    private String todo_name;

    @SerializedName("present_progress")
    @Expose
    private int present_progress;

    @SerializedName("streak")
    @Expose
    private String streak;

    @SerializedName("start_date")
    @Expose
    private String start_date;

    public int getUserId() {
        return user_id;
    }

    public String getTodoName(){
        return todo_name;
    }

    public int getPresentProgress(){ return present_progress; }

    public String getStreak(){ return streak; }

    public String getStartDate(){ return start_date;}

}
