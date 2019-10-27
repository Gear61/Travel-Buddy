package com.randomappsinc.travelbuddy.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.randomappsinc.travelbuddy.R;
import com.randomappsinc.travelbuddy.common.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class NotesAdapter
        extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{

    public interface Listener {
        void onNoteClicked();
    }

    protected Listener listener;
    protected List<Note> notes = new ArrayList<>();

    public NotesAdapter(Listener listener) {
        this.listener = listener;
    }

    public void addNote(Note note) {
        notes.add(note);
        notifyItemInserted(getItemCount() - 1);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_cell,
                parent,
                false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.loadNote(position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        NoteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void loadNote(int position) {
        }
    }
}
