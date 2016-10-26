package com.example.matthewwatson.todo3;

import java.util.Date;

/**
 * Created by Matthew.Watson on 10/24/16.
 */
//"Title","Text","Date","Due Date"
public class Note {
    private String title; //pass with intent
    private String text;
    private Date date;
    private Date dueDate;
//    public int imageId; //pass with intent

    public Note(String title, String text, Date date, Date dueDate) {
        this.title = title;
        this.date = date;
        this.text = text;
        this.dueDate = dueDate;
    }

    public Note() { //You have to have a constructor

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

//    public int getImageId() {
//        return imageId;
//    }
//
//    public void setImageId(int imageId) {
//        this.imageId = imageId;
//    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
