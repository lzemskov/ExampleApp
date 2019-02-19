package com.example.myfirstapp.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.myfirstapp.database.Note;
import com.example.myfirstapp.execution.AppRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * This class represents the model for a Note
 */
public class NoteViewModel extends AndroidViewModel {
    private AppRepository mAppRepository;
    private Note note;

    public NoteViewModel(Application application) {
        super(application);
        mAppRepository = new AppRepository(application);
    }

    public void init(String noteId) {
        if (this.note != null) {
            // ViewModel is created on per fragment basis so noteId oes not change
            return;
        }
        note = getNoteFromRepo(noteId);
    }

    public Note getNote() {
        return note;
    }

    private Note getNoteFromRepo(String id) {
        List<Note> liveNotes = mAppRepository.getAllNotes().getValue();
        for(Note note : liveNotes) {
            String noteId = Integer.toString(note.getUid());
            if (noteId.equals(id)) {
                return note;
            }
        }
        return null;
    }
}
