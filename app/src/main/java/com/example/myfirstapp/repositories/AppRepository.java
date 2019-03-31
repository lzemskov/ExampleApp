package com.example.myfirstapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import javax.inject.Singleton;

import static com.example.myfirstapp.repositories.AppDatabse.*;

/**
 * Connects application context with the database.
 * Kicks off asynchronous insert request using AsyncTask instance.
 **/
@Singleton
public class AppRepository {
    static int count = 0;
    private final NoteDao mNoteDao;
    private final LiveData<List<Note>> mAllNotes;

    public AppRepository(Application application) {
        AppDatabse db = getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
        count++;
        if(mAllNotes.getValue() != null) {
            List<Note> notes = mAllNotes.getValue();
            for(Note n: notes) {
                Log.d("DEBUG", "Note: " + n.getSubject() + " uid" + n.getUid());
            }
        }
        assert(mAllNotes.getValue() != null);
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert (Note note) {
        new insertAsyncTask(mNoteDao).execute(note);
    }

    public LiveData<Note> getNoteById(String id) {
        return mNoteDao.load(id);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

    }
}
