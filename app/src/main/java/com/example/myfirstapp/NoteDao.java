package com.example.myfirstapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * This class contains query definitions for requests issued against 'Note' entity.
 */
@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note")
    void deleteAll();
}
