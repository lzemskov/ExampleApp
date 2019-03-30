package com.example.myfirstapp.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myfirstapp.viewmodels.NoteListAdapter;
import com.example.myfirstapp.viewmodels.AllNotesViewModel;
import com.example.myfirstapp.repositories.Note;

import java.util.List;

/**
 * This Activity is currently the first screen in the application.
 * Defined inside the manifest file and loaded when application is launched.
 *
 * Sets the view and layout based on '.xml' from layout folder.
 * Loads a toolbar and adds action support.
 * Loads the floating button and adds a listener for it.
 * Loads ReciclerView and populates it with data with help from Adapter.
 * Provides on activity callback to handle activity on this view.
 */
public class AllNotesActivity extends AppCompatActivity {
    private AllNotesViewModel mNoteViewModel;
    public static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;
    public static final int VIEW_NOTE_ACTIVITY_REQUEST_CODE = 2;
    public static final String ID_KEY = "ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_notes_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllNotesActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final NoteListAdapter adapter = new NoteListAdapter(this);
        adapter.setOnItemClickListener(new NoteListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(AllNotesActivity.this, NoteDetailActivity.class);
                String noteId = Integer.toString(adapter.getNoteIdAtPosition(position));
                intent.putExtra(ID_KEY,  noteId);
                Log.d("DEBUG", "onItemClick position: " + position + " noteId: " + noteId);
                startActivityForResult(intent, VIEW_NOTE_ACTIVITY_REQUEST_CODE);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d("DEBUG", "onItemLongClick position: " + position);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNoteViewModel = ViewModelProviders.of(this).get(AllNotesViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                // Update the cached copy of the notes in the adapter.
                adapter.setNotes(notes);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE &&
                resultCode == RESULT_OK ) {
            Note note = (Note)data.getSerializableExtra(NewNoteActivity.SER_KEY);
            mNoteViewModel.insert(note);
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }
}