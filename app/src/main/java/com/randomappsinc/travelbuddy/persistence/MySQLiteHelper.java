package com.randomappsinc.travelbuddy.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class MySQLiteHelper extends SQLiteOpenHelper {

    static class NoteTable implements BaseColumns {
        static final String TABLE_NAME = "entry";
        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_TIME = "time";
        static final String COLUMN_NAME_TIMEZONE = "timezone";
        static final String COLUMN_NAME_LATITUDE = "latitude";
        static final String COLUMN_NAME_LONGITUDE = "longitude";
        static final String COLUMN_NAME_DESCRIPTION = "description";
        static final String COLUMN_NAME_IMAGE_PATH = "image_path";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NoteTable.TABLE_NAME + " (" +
                    NoteTable._ID + " INTEGER PRIMARY KEY," +
                    NoteTable.COLUMN_NAME_TITLE + " TEXT," +
                    NoteTable.COLUMN_NAME_TIME + " INTEGER," +
                    NoteTable.COLUMN_NAME_TIMEZONE + " TEXT," +
                    NoteTable.COLUMN_NAME_LATITUDE + " DOUBLE," +
                    NoteTable.COLUMN_NAME_LONGITUDE + " DOUBLE," +
                    NoteTable.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    NoteTable.COLUMN_NAME_IMAGE_PATH + " TEXT)";

    private static final String DATABASE_NAME = "travelbuddy.db";
    private static final int DATABASE_VERSION = 1;

    MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {}
}
