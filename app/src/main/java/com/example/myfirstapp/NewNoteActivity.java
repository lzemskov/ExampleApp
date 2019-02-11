package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity called when we try creating a New Note.
 */
public class NewNoteActivity extends AppCompatActivity {
    public static final String SER_KEY = "notemarker";
    private EditText mEditNoteSubject;
    private EditText mEditNoteDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note_view);
        mEditNoteSubject = findViewById(R.id.edit_subject);
        mEditNoteDescription = findViewById(R.id.edit_description);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNoteSubject.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    Note note = new Note();
                    note.setSubject(mEditNoteSubject.getText().toString());
                    if (!TextUtils.isEmpty(mEditNoteDescription.getText())) {
                        note.setDetails(mEditNoteDescription.getText().toString());
                    }
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(SER_KEY, note);
                    replyIntent.putExtras(mBundle);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
