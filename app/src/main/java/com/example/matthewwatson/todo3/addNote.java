package com.example.matthewwatson.todo3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addNote extends AppCompatActivity {
    private EditText noteTitle;
    private EditText noteText;
    private Button saveButton;
    private Button datePicker;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteTitle = (EditText) findViewById(R.id.note_title);
        noteText = (EditText) findViewById(R.id.note_text);
        saveButton = (Button) findViewById(R.id.save_button);
        datePicker = (Button) findViewById(R.id.myDatePickerButton);
        Spinner spinner = (Spinner) findViewById(R.id.categories_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        final Intent intent = getIntent();
        noteTitle.setText(intent.getStringExtra("Title"));
        noteText.setText(intent.getStringExtra("Text"));
        index = intent.getIntExtra("Index", -1);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_date_picker);

            }
        });

        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);{
            Toast.makeText(this, "You selected", Toast.LENGTH_SHORT).show();
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("Title", noteTitle.getText().toString());
                intent.putExtra("Text", noteText.getText().toString());
                intent.putExtra("Index", noteTitle.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }

        });
    }

}