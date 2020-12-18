package com.example.foodman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "reportsManager";
    private static final String TABLE_REPORTS = "reports";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATA = "data";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REPORTS_TABLE = "CREATE TABLE " + TABLE_REPORTS + "("  + KEY_NAME + " TEXT,"
                + KEY_DATA + " TEXT" + ")";
        db.execSQL(CREATE_REPORTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new report
    void addReport(Report report) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, report.getName());
        values.put(KEY_DATA, report.getData());

        // Inserting Row
        db.insert(TABLE_REPORTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Report getReport(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REPORTS, new String[] {
                        KEY_NAME, KEY_DATA }, KEY_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Report report = new Report(cursor.getString(0),
                cursor.getString(1));
        // return report
        return report;
        //rep
    }

    // code to get all reports in a list view
    public List<Report> getAllReports() {
        List<Report> reportList = new ArrayList<Report>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REPORTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Report report = new Report();
                report.setName(cursor.getString(0));
                report.setData(cursor.getString(1));
                // Adding contact to list
                reportList.add(report);
            } while (cursor.moveToNext());
        }

        // return report list
        return reportList;
    }

    // code to update the single contact
    public int updateReport(Report report) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, report.getName());
        values.put(KEY_DATA, report.getData());

        // updating row
        return db.update(TABLE_REPORTS, values, KEY_NAME + " = ?",
                new String[] { report.getName() });
    }

    // Deleting single report
    public void deleteReport(Report report) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REPORTS, KEY_NAME + " = ?",
                new String[] { report.getName() });
        db.close();
    }

    // Getting reports Count
    public int getReportsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_REPORTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}