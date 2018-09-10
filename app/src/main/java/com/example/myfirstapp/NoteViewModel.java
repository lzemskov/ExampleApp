package com.example.myfirstapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private AppRepository mAppRepository;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel (Application application) {
        super(application);
        mAppRepository = new AppRepository(application);
        mAllNotes = mAppRepository.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(Note note) {
        mAppRepository.insert(note);
    }
}