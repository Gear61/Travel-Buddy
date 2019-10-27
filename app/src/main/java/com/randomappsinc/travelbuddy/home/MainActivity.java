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
import com.randomappsinc.travelbuddy.common.Note;
import com.randomappsinc.travelbuddy.common.StandardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends StandardActivity implements NotesAdapter.Listener {

    public static String NOTE_KEY = "note";

    @BindView(R.id.no_notes_text) TextView noNotesView;
    @BindView(R.id.notes) RecyclerView notesList;
    @BindView(R.id.add_note) FloatingActionButton addNote;

    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addNote.setImageDrawable(
                new IconDrawable(this, IoniconsIcons.ion_android_add)
                        .colorRes(R.color.white));

        notesAdapter = new NotesAdapter(this);
        notesList.setAdapter(notesAdapter);
    }

    @OnClick(R.id.add_note)
    public void addNote() {
        startActivityForResult(new Intent(this, AddNoteActivity.class), 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        noNotesView.setVisibility(notesAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (resultCode == RESULT_OK) {
            noNotesView.setVisibility(View.GONE);
            Note note = resultData.getParcelableExtra(NOTE_KEY);
            notesAdapter.addNote(note);
        }
    }

    @Override
    public void onNoteClicked() {

    }
}
