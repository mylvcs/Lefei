//package com.example.wangmengyun.activity;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//
//
///**
// * Created by wangmengyun on 2018/3/8.
// * 获得当地机场的天气情况
// */
//
//
//public class WeatherActivity extends AppCompatActivity {
//
//
//    /** Database helper that will provide us access to the database */
//    private WeatherDbHelper mDbHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_weather);
//
//
//        // To access our database, we instantiate our subclass of SQLiteOpenHelper
//        // and pass the context, which is the current activity.
//        mDbHelper = new WeatherDbHelper(this);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        displayDatabaseInfo();
//    }
//
//    /**
//     * Temporary helper method to display information in the onscreen TextView about the state of
//     * the pets database.
//     */
//    private void displayDatabaseInfo() {
//        // Create and/or open a database to read from it
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                //TODO
//               WeatherEntry._ID,
//                WeatherEntry.COLUMN_DATE,
//                WeatherEntry.COLUMN_WEATHER_ID,
//       };
//
//        // Perform a query on the pets table
//        Cursor cursor = db.query(
//                WeatherEntry.TABLE_NAME,   // The table to query
//                projection,            // The columns to return
//                null,                  // The columns for the WHERE clause
//                null,                  // The values for the WHERE clause
//                null,                  // Don't group the rows
//                null,                  // Don't filter by row groups
//                null);                   // The sort order
//        //TODOTextView displayView = (TextView) findViewById(R.id.text_view_weather);
//
//        try {
//            // Create a header in the Text View that looks like this:
//            //
//            // The pets table contains <number of rows in Cursor> pets.
//            // _id - name -breed - gender - weight
//            //
//            // In the while loop below, iterate through the rows of the cursor and display
//            // the information from each column in this order.
//            //TODO    displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
//
////            displayView.append(WeatherEntry._ID + " - " +
////                   WeatherEntry .COLUMN_DEGREES + " - " +
////                    "\n");
//
//            // Figure out the index of each column
//            int idColumnIndex = cursor.getColumnIndex(WeatherEntry._ID);
//
//
//            // Iterate through all the returned rows in the cursor
//            while (cursor.moveToNext()) {
//                // Use that index to extract the String or Int value of the word
//                // at the current row the cursor is on.
//                int currentID = cursor.getInt(idColumnIndex);
////                String currentWeather = cursor.getString(DegreeColumnIndex);
////                String currentName = cursor.getString(nameColumnIndex);
////                String currentBreed = cursor.getString(breedColumnIndex);
////                int currentGender = cursor.getInt(genderColumnIndex);
////                int currentWeight = cursor.getInt(weightColumnIndex);
//
//
//                // Display the values from each column of the current row in the cursor in the TextView
////                displayView.append(("\n" + currentID + " - " +
////                        currentName + " - " +
////                        currentBreed + " - " +
////                        currentGender + " - " +
////                        currentWeight));
//            }
//        } finally {
//            // Always close the cursor when you're done reading from it. This releases all its
//            // resources and makes it invalid.
//            cursor.close();
//        }
//    }
//
////       /**
//     //     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
//     //     */
////    private void insertPet() {
////        // Gets the database in write mode
////        SQLiteDatabase db = mDbHelper.getWritableDatabase();
////
////        // Create a ContentValues object where column names are the keys,
////        // and Toto's pet attributes are the values.
////        ContentValues values = new ContentValues();
////        values.put(PetEntry.COLUMN_PET_NAME, "Toto");
////        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
////        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
////        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);
////
////        // Insert a new row for Toto in the database, returning the ID of that new row.
////        // The first argument for db.insert() is the pets table name.
////        // The second argument provides the name of a column in which the framework
////        // can insert NULL in the event that the ContentValues is empty (if
////        // this is set to "null", then the framework will not insert a row when
////        // there are no values).
////        // The third argument is the ContentValues object containing the info for Toto.
////        long newRowId = db.insert(PetEntry.TABLE_NAME, null, values);
////    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        // Inflate the menu options from the res/menu/menu_catalog.xml file.
////        // This adds menu items to the app bar.
////        getMenuInflater().inflate(R.menu.menu_catalog, menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        // User clicked on a menu option in the app bar overflow menu
////        switch (item.getItemId()) {
////            // Respond to a click on the "Insert dummy data" menu option
////            case R.id.action_insert_dummy_data:
////                insertPet();
////                displayDatabaseInfo();
////                return true;
////            // Respond to a click on the "Delete all entries" menu option
////            case R.id.action_delete_all_entries:
////                // Do nothing for now
////                return true;
////        }
////        return super.onOptionsItemSelected(item);
////    }
//}
//
