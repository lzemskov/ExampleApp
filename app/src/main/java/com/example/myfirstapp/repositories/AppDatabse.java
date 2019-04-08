package com.example.myfirstapp.repositories;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import io.reactivex.annotations.NonNull;

/**
 * This class instantiates Database for the Application.
 * For test purpose it initalizes DB with 2 Notes to have something for the view.
 */
@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabse extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static AppDatabse INSTANCE;

    public static AppDatabse getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabse.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabse.class, "app_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@android.support.annotation.NonNull @NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final NoteDao nDao;

        PopulateDbAsync(AppDatabse db) {
            nDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            nDao.deleteAll();
            Note note = new Note();
            note.setSubject("TestNote1");
            note.setDetails("This is my test note1");
            nDao.insert(note);
            note.setSubject("TestNote2");
            note.setDetails("This is my test note2");
            nDao.insert(note);
            return null;
        }
    }
}
