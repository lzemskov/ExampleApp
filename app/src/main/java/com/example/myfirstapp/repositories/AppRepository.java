package com.example.myfirstapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myfirstapp.Utils;

import java.util.List;

import javax.inject.Singleton;

import static com.example.myfirstapp.repositories.AppDatabse.*;

/**
 * Connects application context with the database.
 * Kicks off asynchronous insert request using AsyncTask instance.
 * Instatiates databse.
 **/
@Singleton
public class AppRepository {
    private final NoteDao mNoteDao;
    private final LiveData<List<Note>> mAllNotes;
    private static AppRepository INSTANCE;

    public static AppRepository getRepository(Application application) {
        if (INSTANCE == null ) {
            synchronized (AppRepository.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = new AppRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    private AppRepository(Application application) {
        AppDatabse db = getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
        Utils.printNotesList(mAllNotes);
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