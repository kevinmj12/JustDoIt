package com.example.justdoit;

public class Todo {
    int todoID;
    String name;
    int deadlineMonth, deadlineDate;
    int totalProgress, presentProgress;
    boolean isDailyTodo;

    public Todo(int todoID, String name, int deadlineMonth, int deadlineDate, int totalProgress, int presentProgress, boolean isDailyTodo) {
        this.todoID = todoID;
        this.name = name;
        this.deadlineMonth = deadlineMonth;
        this.deadlineDate = deadlineDate;
        this.totalProgress = totalProgress;
        this.presentProgress = presentProgress;
        this.isDailyTodo = isDailyTodo;
    }

    public int getTodoID() {
        return todoID;
    }
    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDeadlineMonth() {
        return deadlineMonth;
    }
    public void setDeadlineMonth(int deadlineMonth) {
        this.deadlineMonth = deadlineMonth;
    }
    public int getDeadlineDate() {
        return deadlineDate;
    }
    public void setDeadlineDate(int deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public int getTotalProgress() {
        return totalProgress;
    }
    public void setTotalProgress(int totalProgress) {
        this.totalProgress = totalProgress;
    }
    public int getPresentProgress() {
        return presentProgress;
    }
    public void setPresentProgress(int presentProgress) {
        this.presentProgress = presentProgress;
    }
    public int getPercentProgress(){
        return (presentProgress / totalProgress);
    }

    public boolean isDailyTodo() {
        return isDailyTodo;
    }
    public void setDailyTodo(boolean dailyTodo) {
        isDailyTodo = dailyTodo;
    }


}
