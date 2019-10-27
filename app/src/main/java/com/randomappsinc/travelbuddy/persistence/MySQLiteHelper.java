package com.randomappsinc.travelbuddy.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String CREATE_NOTE_TABLE_QUERY = "CREATE TABLE Note(" +
            "id SERIAL," +
            "title VARCHAR(256)," +
            "taken_time INTEGER(256)," +
            "time_zone VARCHAR(64)," +
            "description varchar(1024)";

    private static final String DATABASE_NAME = "travelbuddy.db";
    private static final int DATABASE_VERSION = 1;

    MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_NOTE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {}
}
