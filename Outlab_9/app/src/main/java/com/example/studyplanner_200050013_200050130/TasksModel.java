package com.example.studyplanner_200050013_200050130;

public class TasksModel {

    // variables for our tasks,

    private String taskTitle;
    private String taskDesc;
    private String taskDate;
    private String taskTime;
    private String taskType;
    private int id;

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public TasksModel(String taskTitle, String taskDesc, String taskDate, String taskTime, String taskType) {
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.taskType = taskType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
