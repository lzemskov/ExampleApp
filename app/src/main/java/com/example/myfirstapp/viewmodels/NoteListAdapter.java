package com.example.myfirstapp.viewmodels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfirstapp.activities.R;
import com.example.myfirstapp.repositories.Note;

import java.util.List;

/**
 * This class provides binding between an app specific dataset to a given view displayed inside the RecyclerView.
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
    //adding click-ability to items inside the list which uses this adapter
    private static ClickListener clickListener;

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final TextView noteItemView;

        private NoteViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            noteItemView = itemView.findViewById(R.id.textView);
        }

        @Override public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return true;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        NoteListAdapter.clickListener = clickListener;
    }

    private final LayoutInflater mInflater;
    private List<Note> mNotes; //cached copy of notes

    public NoteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
       return new NoteViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);
            holder.noteItemView.setText(current.getSubject());
        }
        else {
            holder.noteItemView.setText("No Notes found");
        }
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNotes != null) {
            return mNotes.size();
        } else {
            return 0;
        }
    }

    public int getNoteIdAtPosition(int position) {
        return mNotes.get(position).getUid();
    }
}