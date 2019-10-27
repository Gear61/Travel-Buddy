package com.randomappsinc.travelbuddy.persistence;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
}
