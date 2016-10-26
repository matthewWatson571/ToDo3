package com.example.matthewwatson.todo3;

import java.util.ArrayList;

public class Data {

    public static ArrayList<ThumbNail> getData() {

        ArrayList<ThumbNail> thumbNail = new ArrayList<>();

        int[] images = {
                R.drawable.ani_cat_one,
                R.drawable.ani_cat_two,
                R.drawable.ani_cat_three,

        };

        String[] thumbNotes = {"Note 1", "Note 2", "Note 3"};

        for (int i = 0; i < images.length; i++) {

            ThumbNail current = new ThumbNail();
            current.thumbTitle = thumbNotes[i];
            current.imageId = images[i];

            thumbNail.add(current);
        }

        return thumbNail;
    }

}
