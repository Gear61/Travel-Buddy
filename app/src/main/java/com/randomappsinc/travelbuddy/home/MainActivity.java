package com.randomappsinc.travelbuddy.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.IoniconsIcons;
import com.randomappsinc.travelbuddy.R;
import com.randomappsinc.travelbuddy.addnote.AddNoteActivity;
import com.randomappsinc.travelbuddy.common.Constants;
import com.randomappsinc.travelbuddy.common.Note;
import com.randomappsinc.travelbuddy.common.PictureFullViewActivity;
import com.randomappsinc.travelbuddy.common.StandardActivity;
import com.randomappsinc.travelbuddy.persistence.DataSource;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends StandardActivity implements NotesAdapter.Listener {

    @BindView(R.id.no_notes_text) TextView noNotesView;
    @BindView(R.id.notes) RecyclerView notesList;
    @BindView(R.id.add_note) FloatingActionButton addNote;

    private NotesAdapter notesAdapter;
    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dataSource = new DataSource(this);
        addNote.setImageDrawable(
                new IconDrawable(this, IoniconsIcons.ion_android_add)
                        .colorRes(R.color.white));

        notesAdapter = new NotesAdapter(this);
        notesList.setAdapter(notesAdapter);
    }

    @OnClick(R.id.add_note)
    public void addNote() {
        startActivity(new Intent(this, AddNoteActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Note> notes = dataSource.getAllNotes();
        notesAdapter.setNotes(notes);
        noNotesView.setVisibility(notesAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onMediaClicked(Note note) {
        Intent intent = new Intent(this, PictureFullViewActivity.class)
                .putExtra(Constants.IMAGE_URL_KEY, note.getImagePath())
                .putExtra(Constants.CAPTION_KEY, note.getTitle());
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, 0);
    }
}
