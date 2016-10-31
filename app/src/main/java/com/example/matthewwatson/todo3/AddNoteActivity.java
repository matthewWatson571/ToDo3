package com.example.matthewwatson.todo3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.matthewwatson.todo3.MainActivity.NOTE_INDEX;
import static com.example.matthewwatson.todo3.MainActivity.NOTE_TEXT;
import static com.example.matthewwatson.todo3.MainActivity.NOTE_TITLE;

public class AddNoteActivity extends AppCompatActivity {
    private EditText noteTitle;
    private EditText noteText;
    private Button datePicker;
    private Spinner spinner;
    private int index;

    private ListView notesList;
    private  static ArrayList<Note> notesArray;
    private SharedPreferences notesPrefs;
    private Gson gson;
    Button saveButton;
//    List<Note> noteList = new ArrayList<>(); noteList:size=0
    String fileName = "toDoItemsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteTitle = (EditText) findViewById(R.id.note_title);
        noteText = (EditText) findViewById(R.id.note_text);

        Intent intent = getIntent();
        index = intent.getIntExtra(NOTE_INDEX, -1);
        noteTitle.setText(intent.getStringExtra(NOTE_TITLE));
        noteText.setText(intent.getStringExtra(NOTE_TEXT));

        notesPrefs = getPreferences(Context.MODE_PRIVATE);
        gson = new Gson();
        setupNotes();

        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra(MainActivity.NOTE_INDEX, index);
                intent.putExtra(MainActivity.NOTE_TITLE, noteTitle.getText().toString());
                intent.putExtra(MainActivity.NOTE_TEXT, noteText.getText().toString());
                setResult(RESULT_OK, intent);
                Toast.makeText(AddNoteActivity.this, "Note Saved...Maybe", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setupNotes() {
        notesArray = new ArrayList<>();
        if (notesPrefs.getBoolean("firstRun", true)) {
            SharedPreferences.Editor editor = notesPrefs.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();

            Note note1 = new Note("Note 1", "This is a note", new Date());
            notesArray.add(note1);
            notesArray.add(new Note("Note 2", "This is another note", new Date()));
            notesArray.add(new Note("Note 3", "This is another note", new Date()));

            for (Note note : notesArray) {
                writeFile(note);
            }
        } else {
            File[] filesDir = this.getFilesDir().listFiles();
            for (File file : filesDir) {
                FileInputStream inputStream = null;
                String title = file.getName();
                Date date = new Date(file.lastModified());
                String text = "";
                try {
                    inputStream = openFileInput(title);
                    byte[] input = new byte[inputStream.available()];
                    while (inputStream.read(input) != -1) {
                    }
                    text += new String(input);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Note note = gson.fromJson(text, Note.class);
                    note.setDate(date);
                    notesArray.add(note);
                    Log.e("Title", title);
                    try {
                        inputStream.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int index = data.getIntExtra(NOTE_INDEX, -1);
            Note note = new Note(data.getStringExtra(NOTE_TITLE),
                    data.getStringExtra(NOTE_TEXT),
                    new Date());
            if (index < 0 || index > notesArray.size() - 1) {
                notesArray.add(note);
            } else {
                Note oldNote = notesArray.get(index);
                note.setKey(oldNote.getKey());
                notesArray.set(index, note);
            }
            writeFile(note);
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    private void writeFile(Note note) {
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(note.getKey(), Context.MODE_PRIVATE);
            outputStream.write(gson.toJson(note).getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Exception ignored) {
            }
        }
    }

    public static ArrayList<ThumbNail> getData() {

       ArrayList<ThumbNail> thumbNail = new ArrayList<>();
        int[] images = {
                R.drawable.ani_cat_one,
                R.drawable.ani_cat_two,
                R.drawable.ani_cat_three,
        };
        String[] thumbNotes = {"Note 1", "Note 2", "Note 3"};//make this note title
        for (int i = 0; i < images.length; i++) {
            ThumbNail current = new ThumbNail();
            current.thumbTitle = thumbNotes[i];
//                current.thumbTitle = categories.get(i).notes.get(i).getTitle();
            current.imageId = images[i];
            thumbNail.add(current);

//        if (notesArray.get(0)==null) {
//            for (int i = 0; i < images.length; i++) {
//
//                ThumbNail current = new ThumbNail();
//                current.thumbTitle = thumbNotes[i];
//                current.imageId = images[i];
//
//                thumbNail.add(current);
//            }
//        } else {
//            for (int i = 0; i < images.length; i++) {
//
//                ThumbNail current = new ThumbNail();
//
//                current.imageId = images[i];
//                current.thumbTitle = notesArray.get(i - 1).getTitle();
//                thumbNail.add(current);
//            }
        }
        return thumbNail;
    }
}