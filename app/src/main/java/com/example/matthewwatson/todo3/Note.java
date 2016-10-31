package com.example.matthewwatson.todo3;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Matthew.Watson on 10/24/16.
 */
//"Title","Text","Date","Due Date"
public class Note {
    private String title;
    private String text;
    private Date date;
    private Date dueDate;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Note(String title, String text, Date dueDate) {

        this.title = title;
        this.date = new Date();
        this.text = text;
        this.dueDate = dueDate;
        this.key = UUID.randomUUID().toString();
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
