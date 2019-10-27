package com.randomappsinc.travelbuddy.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.IoniconsIcons;
import com.randomappsinc.travelbuddy.R;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends StandardActivity {

    @BindView(R.id.add_note) FloatingActionButton addNote;
    @BindView(R.id.notes) RecyclerView notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote.setImageDrawable(
                new IconDrawable(this, IoniconsIcons.ion_android_add)
                        .colorRes(R.color.white));
    }

    @OnClick(R.id.add_note)
    public void addNote() {

    }
}
