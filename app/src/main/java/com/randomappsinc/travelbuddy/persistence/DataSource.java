package com.randomappsinc.travelbuddy.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
        dbHelper.close();
    }

    public void addNote(String title, long timeOfNote, String timeZone, String description) {
        open();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(NoteTable.COLUMN_NAME_TITLE, title);
        values.put(NoteTable.COLUMN_NAME_TIME, timeOfNote);
        values.put(NoteTable.COLUMN_NAME_TIMEZONE, timeZone);
        values.put(NoteTable.COLUMN_NAME_DESCRIPTION, description);

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
                NoteTable.COLUMN_NAME_DESCRIPTION,
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

            String description = cursor.getString(cursor
                    .getColumnIndexOrThrow(NoteTable.COLUMN_NAME_TITLE));
            notes.add(new Note(title, time, timeZone, description));
        }
        cursor.close();
        close();

        return notes;
    }
}
