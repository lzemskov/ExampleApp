package com.example.myfirstapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.myfirstapp.repositories.Note;
import com.example.myfirstapp.repositories.AppRepository;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * This class represents the View model associated with {@link com.example.myfirstapp.activities.NoteDetailActivity}
 */
public class NoteDetailViewModel extends AndroidViewModel {
    private AppRepository mAppRepository;
    private Note note;

    public NoteDetailViewModel(Application application) {
        super(application);
        mAppRepository = AppRepository.getRepository(application);
    }

    public Note getNote() {
        return this.note;
    }

    /**
     * Initializes current model with {@link Note} based on input id; if corresponding entity not found then initialized to null.
     * @param uid - uid of the Note to be loaded.
     */
    public void init(int uid) {
        if (this.note != null && this.getNote().getUid() == uid) {
            // ViewModel is created on per fragment basis so noteId oes not change
            return;
        }
        this.note = getNoteOrNullFromRepository(uid);
    }

    /**
     * This helper goes through the list of all {@link Note} inside the repository and returns one which has the same id as specified by an input argument.
     * If no record found we retun null.
     * @param uid
     * @return {@link Note} object which has the corresponding id or null if no object found.
     */
    private Note getNoteOrNullFromRepository(int uid) {
        LiveData<List<Note>> liveNotes = mAppRepository.getAllNotes();
        if(liveNotes != null && liveNotes.getValue() != null && !liveNotes.getValue().isEmpty()){
            for (Note note : liveNotes.getValue()) {
                if (note.getUid() == uid) {
                    return note;
                }
            }
        }
        return null;
    }
}
