package com.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.boxuegu.data.TravelerContract;


import static com.boxuegu.data.TravelerContract.TravelerEntry.TABLE_NAME;

/**
 * Created by wangmengyun on 2018/3/2.
 *
 * Database for travelers
 *
 */
public class TravelerDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = TravelerDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "traveler.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;


    public TravelerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     * 旅客表有字段：
     姓名，性别，出生日期，身份证号码，护照号码，是否常旅客，国籍，住址，etc
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                +TravelerContract.TravelerEntry.COLUMN_NAME+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + TravelerEditor.COLUMN_PET_NAME + " TEXT NOT NULL, "
//                + TravelerEditor.COLUMN_PET_BREED + " TEXT, "
//                + TravelerEditor.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
//                + TravelerEditor.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);"
           //TODO     +TravelerEditorActivity.

                ;

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's noth
    }
}