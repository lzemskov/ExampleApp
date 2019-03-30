package com.example.myfirstapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.myfirstapp.repositories.Note;
import com.example.myfirstapp.repositories.AppRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * This class builds view to be consumed by the UI.
 * Delegates to Repository to Gets all Notes from the DB.
 * Delegates to Repository to Inserts new Note info the DB.
 */
public class AllNotesViewModel extends AndroidViewModel {
    //TODO: Need to figure out how to use Dagger2 library to inject repository view.
    private AppRepository mAppRepository;
    private LiveData<List<Note>> mAllNotes;

    @Inject
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