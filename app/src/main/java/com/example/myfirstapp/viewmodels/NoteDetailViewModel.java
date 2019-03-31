package com.example.myfirstapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.util.Log;

import com.example.myfirstapp.repositories.Note;
import com.example.myfirstapp.repositories.AppRepository;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * This class represents the model for a Note
 */
public class NoteDetailViewModel extends AndroidViewModel {
    private AppRepository mAppRepository;
    private Note note;

    public NoteDetailViewModel(Application application) {
        super(application);
        mAppRepository = new AppRepository(application);
    }

    public void init(String noteId) {
        Log.d("DEBUG", "Init: " + this.getClass().getName());
        if (this.note != null) {
            Log.d("DEBUG", "Note: " + this.note.getSubject() + this.note.getUid());
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

        //List<Note> liveNotes = mAppRepository.getAllNotes().getValue();
        if(liveNotes != null){
            for (Note note : liveNotes) {
                String noteId = Integer.toString(note.getUid());
                if (noteId.equals(id)) {
                    return note;
                }
            }
        }
        return null;
    }
}
