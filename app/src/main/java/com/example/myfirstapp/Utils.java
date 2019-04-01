package com.example.myfirstapp;

import android.arch.lifecycle.LiveData;

import com.example.myfirstapp.repositories.Note;

import java.util.List;

/**
 * This class contains different utils which can be used by the code and debugging.
 * TODO: should separate those out eventually
 */
public class Utils {
    /**
     * This is a helper which prints out the content of LiveData<List<Note>>
     *
     * @param notes
     */
    public static void printNotesList(LiveData<List<Note>>notes) {
        if(notes != null && notes.getValue() != null) {
            List<Note>ns = notes.getValue();
            if(ns.isEmpty()) {
                System.err.println("DEBUG: LiveData is empty..." );
            } else {
                System.err.println("DEBUG: LiveData is not empty..." );
                int count = 0;
                for(Note n : ns){
                    count++;
                    System.err.println("Note " + count + ":");
                    System.err.println(n.toStringJson());
                }
            }
        } else if(notes == null){
            System.err.println("DEBUG: notes is null...");
        } else if (notes.getValue() == null) {
            System.err.println("DEBUG: notes.getValue() is null...");
        } else {
            System.err.println("DEBUG: WTF !!!!");
        }
    }
}
