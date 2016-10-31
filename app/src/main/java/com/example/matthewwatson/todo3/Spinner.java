package com.example.matthewwatson.todo3;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Matthew.Watson on 10/25/16.
 */

public class Spinner extends Activity implements AdapterView.OnItemSelectedListener {


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Intent intent = new Intent();

        switch(pos){
            case 1:
                parent.getItemAtPosition(1);
                intent.putExtra("Category", "work");
            case 2:
                parent.getItemAtPosition(2);
                intent.putExtra("Category", "personal");
            case 3:
                parent.getItemAtPosition(3);
                intent.putExtra("Category", "grocery");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        //Uncategorized
    }
}
