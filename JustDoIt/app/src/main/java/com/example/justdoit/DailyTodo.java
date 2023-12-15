package com.example.justdoit;

public class DailyTodo {
    String name;
    int presentProgress;
    String streak;
    String startDate;

    public DailyTodo(String name, int presentProgress, String streak, String startDate) {
        this.name = name;
        this.presentProgress = presentProgress;
        this.streak = streak;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPresentProgress(){ return presentProgress;}
    public void setPresentProgress(int progress) {this.presentProgress = progress;}

    public String getStreak() { return streak; }
    public void setStreak(String streak){this.streak = streak;}

    public String getStartDate(){ return startDate;}
    public void setStartDate(String startDate){this.startDate = startDate;}

}
