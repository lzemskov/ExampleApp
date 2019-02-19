package com.example.myfirstapp.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.myfirstapp.database.Note;
import com.example.myfirstapp.execution.AppRepository;

import java.util.List;

/**
 * This class builds view to be consumed by the UI.
 * Delegates to Repository to Gets all Notes from the DB.
 * Delegates to Repository to Inserts new Note info the DB.
 */
public class AllNotesViewModel extends AndroidViewModel {
    private AppRepository mAppRepository;
    private LiveData<List<Note>> mAllNotes;

    public AllNotesViewModel(Application application) {
        super(application);
        mAppRepository = new AppRepository(application);
        mAllNotes = mAppRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(Note note) {
        mAppRepository.insert(note);
    }
}