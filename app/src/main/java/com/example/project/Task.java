package com.example.project;

public class Task {
    private String title,description,date,status;

    public Task(String title,String description,String date,String status){
        this.title=title;
        this.description=description;
        this.date=date;
        this.status=status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}
