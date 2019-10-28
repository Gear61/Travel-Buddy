package com.randomappsinc.travelbuddy.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;
import com.randomappsinc.travelbuddy.common.Note;
import com.randomappsinc.travelbuddy.persistence.MySQLiteHelper.NoteTable;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class DataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    // Constructor
    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    // Open connection to database
    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Terminate connection to database
    private void close() {
        if (database != null) {
            database.close();
        }
    }

    public void addNote(Note note) {
        open();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(NoteTable.COLUMN_NAME_TITLE, note.getTitle());
        values.put(NoteTable.COLUMN_NAME_TIME, note.getTime());
        values.put(NoteTable.COLUMN_NAME_TIMEZONE, note.getTimeZone().getID());
        values.put(NoteTable.COLUMN_NAME_LATITUDE, note.getLocation().latitude);
        values.put(NoteTable.COLUMN_NAME_LONGITUDE, note.getLocation().longitude);
        values.put(NoteTable.COLUMN_NAME_DESCRIPTION, note.getDescription());
        values.put(NoteTable.COLUMN_NAME_IMAGE_PATH, note.getImagePath());

        database.insert(MySQLiteHelper.NoteTable.TABLE_NAME, null, values);

        close();
    }

    public List<Note> getAllNotes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                NoteTable.COLUMN_NAME_TITLE,
                NoteTable.COLUMN_NAME_TIME,
                NoteTable.COLUMN_NAME_TIMEZONE,
                NoteTable.COLUMN_NAME_LATITUDE,
                NoteTable.COLUMN_NAME_LONGITUDE,
                NoteTable.COLUMN_NAME_DESCRIPTION,
                NoteTable.COLUMN_NAME_IMAGE_PATH
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                NoteTable.COLUMN_NAME_TIME + " DESC";

        Cursor cursor = db.query(
                NoteTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(NoteTable.COLUMN_NAME_TITLE));
            long time = cursor.getLong(cursor.getColumnIndexOrThrow(NoteTable.COLUMN_NAME_TIME));

            String timeZoneId = cursor.getString(cursor
                    .getColumnIndexOrThrow(NoteTable.COLUMN_NAME_TIMEZONE));
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);

            double latitude = cursor.getDouble(cursor
                    .getColumnIndexOrThrow(NoteTable.COLUMN_NAME_LATITUDE));
            double longitude = cursor.getDouble(cursor
                    .getColumnIndexOrThrow(NoteTable.COLUMN_NAME_LONGITUDE));
            LatLng location = new LatLng(latitude, longitude);

            String description = cursor.getString(cursor
                    .getColumnIndexOrThrow(NoteTable.COLUMN_NAME_DESCRIPTION));
            String imagePath = cursor.getString(cursor
                    .getColumnIndexOrThrow(NoteTable.COLUMN_NAME_IMAGE_PATH));
            notes.add(new Note(title, time, timeZone, location, description, imagePath));
        }
        cursor.close();
        close();

        return notes;
    }
}
