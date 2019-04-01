package com.example.myfirstapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.myfirstapp.repositories.Note;
import com.example.myfirstapp.repositories.AppRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * This class represents a ViewModel associated with {@link com.example.myfirstapp.activities.AllNotesActivity}
 */
public class AllNotesViewModel extends AndroidViewModel {
    //TODO: Need to figure out how to use Dagger2 library to inject repository view.
    private AppRepository mAppRepository;
    private LiveData<List<Note>> mAllNotes;

    public AllNotesViewModel(Application application) {
        super(application);
        mAppRepository = AppRepository.getRepository(application);
        mAllNotes = mAppRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(Note note) {
        mAppRepository.insert(note);
    }
}