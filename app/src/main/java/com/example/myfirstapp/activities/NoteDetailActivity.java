package com.example.myfirstapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myfirstapp.Utils;
import com.example.myfirstapp.activities.databinding.NoteDetailViewBinding;
import com.example.myfirstapp.viewmodels.NoteDetailViewModel;

import static com.example.myfirstapp.Constants.ID_KEY;

/**
 * Activity created when we click on one of the Notes inside the RecyclerView from {@link AllNotesActivity}
 */
public class NoteDetailActivity extends AppCompatActivity {
    private NoteDetailViewModel newNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newNoteViewModel = ViewModelProviders.of(this).get(NoteDetailViewModel.class);
        newNoteViewModel.init(this.getIntent().getIntExtra(ID_KEY, -1));
        NoteDetailViewBinding binding = DataBindingUtil.setContentView(this, R.layout.note_detail_view);
        binding.setNote(newNoteViewModel.getNote());
    }
}