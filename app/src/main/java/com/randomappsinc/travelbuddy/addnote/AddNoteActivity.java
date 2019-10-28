package com.randomappsinc.travelbuddy.addnote;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.randomappsinc.travelbuddy.R;
import com.randomappsinc.travelbuddy.common.Note;
import com.randomappsinc.travelbuddy.common.PhotoTakerManager;
import com.randomappsinc.travelbuddy.common.StandardActivity;
import com.randomappsinc.travelbuddy.location.LocationPickerActivity;
import com.randomappsinc.travelbuddy.persistence.DataSource;
import com.randomappsinc.travelbuddy.util.PermissionUtil;
import com.randomappsinc.travelbuddy.util.TimeUtil;
import com.randomappsinc.travelbuddy.util.UIUtil;

import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNoteActivity extends StandardActivity
        implements DateTimeAdder.Listener, PhotoTakerManager.Listener {

    private static final int CAMERA_CODE = 1;

    public static final String LATITUDE_KEY = "latitude";
    public static final String LONGITUDE_KEY = "longitude";

    @BindView(R.id.note_title_input) TextView titleInput;
    @BindView(R.id.date_text) TextView dateTimeText;
    @BindView(R.id.location_text) TextView locationText;
    @BindView(R.id.note_description_input) TextView descriptionInput;

    private final TimeZone timeZone = TimeZone.getDefault();

    private DateTimeAdder dateTimeAdder;
    private long chosenTime;
    private LatLng chosenLocation;
    private PhotoTakerManager photoTakerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateTimeAdder = new DateTimeAdder(getFragmentManager(), this);
        chosenTime = System.currentTimeMillis();
        dateTimeText.setText(TimeUtil.getDefaultTimeText(chosenTime, timeZone));

        photoTakerManager = new PhotoTakerManager(this);
    }

    @OnClick(R.id.take_picture)
    public void takePicture() {
        maybeStartCameraPage();
    }

    private void maybeStartCameraPage() {
        if (PermissionUtil.isPermissionGranted(Manifest.permission.CAMERA, this)) {
            Intent takePhotoIntent = photoTakerManager.getPhotoTakingIntent(this);
            if (takePhotoIntent == null) {
                UIUtil.showLongToast(
                        R.string.take_photo_with_camera_failed, this);
            } else {
                startActivityForResult(takePhotoIntent, CAMERA_CODE);
            }
        } else {
            PermissionUtil.requestPermission(this, Manifest.permission.CAMERA, CAMERA_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && requestCode == CAMERA_CODE) {
            maybeStartCameraPage();
        }
    }

    @Override
    public void onTakePhotoFailure() {

    }

    @Override
    public void onTakePhotoSuccess(Bitmap bitmap) {

    }

    @OnClick(R.id.add_location_section)
    public void selectLocation() {
        startActivityForResult(
                new Intent(this, LocationPickerActivity.class), 1);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            double latitude = data.getDoubleExtra(LATITUDE_KEY, 0);
            double longitude = data.getDoubleExtra(LONGITUDE_KEY, 0);
            chosenLocation = new LatLng(latitude, longitude);
            String locationString = latitude + ", " + longitude;
            locationText.setText(locationString);
        }
    }

    @OnClick(R.id.save)
    public void save() {
        String title = titleInput.getText().toString().trim();
        if (title.isEmpty()) {
            UIUtil.showLongToast(R.string.empty_title_error, this);
            return;
        }
        if (chosenLocation == null) {
            UIUtil.showLongToast(R.string.empty_location_error, this);
            return;
        }
        String description = descriptionInput.getText().toString().trim();
        Note note = new Note(title, chosenTime, timeZone, chosenLocation, description);
        DataSource dataSource = new DataSource(this);
        dataSource.addNote(note);
        UIUtil.showShortToast(R.string.note_add_success, this);
        finish();
    }
}
