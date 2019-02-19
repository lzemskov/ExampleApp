package com.example.myfirstapp.execution;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.myfirstapp.database.Note;
import com.example.myfirstapp.database.AppDatabse;
import com.example.myfirstapp.database.NoteDao;

import java.util.List;

import javax.inject.Singleton;

import static com.example.myfirstapp.database.AppDatabse.*;

/**
 * Connects application context with the database.
 * Kicks off asynchronous insert request using AsyncTask instance.
 */
public class AppRepository {
    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public AppRepository(Application application) {
        AppDatabse db = getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert (Note note) {
        new insertAsyncTask(mNoteDao).execute(note);
    }

    public Note getNoteById(String id) {
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
