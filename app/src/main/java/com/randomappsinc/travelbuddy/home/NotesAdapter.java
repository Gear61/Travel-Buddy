package com.randomappsinc.travelbuddy.home;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.randomappsinc.travelbuddy.R;
import com.randomappsinc.travelbuddy.common.Note;
import com.randomappsinc.travelbuddy.util.TimeUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesAdapter
        extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{

    public interface Listener {
        void onNoteClicked(Note note);
    }

    private Listener listener;
    private List<Note> notes = new ArrayList<>();

    NotesAdapter(Listener listener) {
        this.listener = listener;
    }

    void setNotes(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
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

        @BindView(R.id.note_title) TextView title;
        @BindView(R.id.note_description) TextView description;
        @BindView(R.id.note_location) TextView location;
        @BindView(R.id.note_time) TextView time;

        NoteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void loadNote(int position) {
            Note note = notes.get(position);
            title.setText(note.getTitle());

            String descriptionText = note.getDescription();
            if (TextUtils.isEmpty(descriptionText)) {
                description.setVisibility(View.GONE);
            } else {
                description.setVisibility(View.VISIBLE);
                description.setText(note.getDescription());
            }

            DecimalFormat formatter = new DecimalFormat("#.00000");
            String cleanLat = formatter.format(note.getLocation().latitude);
            String cleanLong = formatter.format(note.getLocation().longitude);
            String finalLocationText = cleanLat + ", " + cleanLong;
            location.setText(finalLocationText);
            time.setText(TimeUtil.getDefaultTimeText(
                    note.getNoteTakenTime(), note.getNoteTakenTimeZone()));
        }

        @OnClick(R.id.parent)
        void onCellClicked() {
            listener.onNoteClicked(notes.get(getAdapterPosition()));
        }
    }
}
