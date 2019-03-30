package com.example.myfirstapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.myfirstapp.activities.databinding.NoteDetailViewBinding;
import com.example.myfirstapp.viewmodels.NoteDetailsViewModel;

import static com.example.myfirstapp.activities.AllNotesActivity.ID_KEY;


public class NoteDetailActivity extends AppCompatActivity {
    private static final String UID_KEY = "uid";
    private NoteDetailsViewModel newNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newNoteViewModel = ViewModelProviders.of(this).get(NoteDetailsViewModel.class);
        String id = this.getIntent().getStringExtra(ID_KEY);
        newNoteViewModel.init(id);
        NoteDetailViewBinding binding = DataBindingUtil.setContentView(this, R.layout.note_detail_view);
        binding.setNote(newNoteViewModel.getNote());
    }
}
