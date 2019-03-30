package com.example.myfirstapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.myfirstapp.repositories.Note;
import com.example.myfirstapp.repositories.AppRepository;

import java.util.List;

/**
 * This class represents the model for a Note
 */
public class NoteDetailsViewModel extends AndroidViewModel {
    private AppRepository mAppRepository;
    private Note note;

    public NoteDetailsViewModel(Application application) {
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
