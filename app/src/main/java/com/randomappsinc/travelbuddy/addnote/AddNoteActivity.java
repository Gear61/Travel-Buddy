package com.randomappsinc.travelbuddy.addnote;

import android.os.Bundle;

import com.randomappsinc.travelbuddy.R;
import com.randomappsinc.travelbuddy.activity.StandardActivity;

import butterknife.ButterKnife;

public class AddNoteActivity extends StandardActivity implements DateTimeAdder.Listener {

    private DateTimeAdder dateTimeAdder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateTimeAdder = new DateTimeAdder(getFragmentManager(), this);
    }

    @Override
    public void onDateTimeChosen(long timeChosen) {

    }
}
