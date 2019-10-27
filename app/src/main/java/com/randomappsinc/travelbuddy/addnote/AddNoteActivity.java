package com.randomappsinc.travelbuddy.addnote;

import android.os.Bundle;
import android.widget.TextView;

import com.randomappsinc.travelbuddy.R;
import com.randomappsinc.travelbuddy.common.StandardActivity;
import com.randomappsinc.travelbuddy.persistence.DataSource;
import com.randomappsinc.travelbuddy.util.TimeUtil;
import com.randomappsinc.travelbuddy.util.UIUtil;

import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNoteActivity extends StandardActivity implements DateTimeAdder.Listener {

    @BindView(R.id.note_title_input) TextView titleInput;
    @BindView(R.id.date_text) TextView dateTimeText;
    @BindView(R.id.note_description_input) TextView descriptionInput;

    private final TimeZone timeZone = TimeZone.getDefault();

    private DateTimeAdder dateTimeAdder;
    private long chosenTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateTimeAdder = new DateTimeAdder(getFragmentManager(), this);
        chosenTime = System.currentTimeMillis();
        dateTimeText.setText(TimeUtil.getDefaultTimeText(chosenTime, timeZone));
    }

    @OnClick(R.id.add_location_section)
    public void selectLocation() {
        // TODO: Open the location picker activity here!!!
    }

    @OnClick(R.id.date_picker_section)
    public void selectDate() {
        dateTimeAdder.show(chosenTime);
    }

    @Override
    public void onDateTimeChosen(long timeChosen) {
        chosenTime = timeChosen;
        dateTimeText.setText(TimeUtil.getDefaultTimeText(chosenTime, timeZone));
        UIUtil.showShortToast(R.string.time_set_success, this);
    }

    @OnClick(R.id.save)
    public void save() {
        String title = titleInput.getText().toString().trim();
        if (title.isEmpty()) {
            UIUtil.showLongToast(R.string.empty_title_error, this);
            return;
        }
        String description = descriptionInput.getText().toString().trim();

        DataSource dataSource = new DataSource(this);
        dataSource.addNote(title, chosenTime, timeZone.getID(), description);
        finish();
    }
}
