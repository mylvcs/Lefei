package com.example.wangmengyun.AsyncTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wangmengyun.lefei.R;

import java.io.IOException;
import java.net.URL;

/**城市对的输入为两个TextView里的string
 * 作为参数传入到URL里。
   现在只传入了一个departCity参数
*/


public class Asynctask extends AppCompatActivity {

    private EditText et_departureCity;
    private EditText et_arriveCity;


//        private TextView mUrlDisplayTextView;
//
       private TextView mFlightResultsTextView ;
//
//        private Button button;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
//
//            departureCity = (EditText) findViewById(R.id.departure);
//            arriveCity = findViewById(R.id.arrive);
//
//
//            button = findViewById(R.id.search_button);
//        //    mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
//           mFlightResultsTextView = (TextView) findViewById(R.id.tv_flight_search_results_json);
//
//
//           button.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   makeFlightSearchQuery();
//               }
//           });
//        }
        et_departureCity= findViewById(R.id.et_departCity);
        et_arriveCity= findViewById(R.id.et_arriveCity);
        Intent getIntent = getIntent();

        Bundle bd = getIntent.getExtras();
        String departCity = bd.getString("departCity");
        String arriveCity = bd.getString("arriveCity");
     //       String textEntered = departureCityintent.getStringExtra(Intent.EXTRA_TEXT);

        et_departureCity.setText(departCity);
        et_arriveCity.setText(arriveCity);
      //      makeFlightSearchQuery();

    }
        /**
         * This method retrieves the search text from the EditText, constructs the
         * URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
         * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
         * our {@link FlightQueryTask}
         */


//    public void makeFlightSearchQuery() {
//        String departCityQuery = departureCity.getText().toString();
//        String arriveCityQuery = arriveCity.getText().toString();
////TODO
//        /**
//         * 这里的问题是如何传入两个参数呢？
//         * our {@link FlightQueryTask}
//         */
//
//        URL flightSearchUrl = NetworkUtils.buildUrl(departCityQuery);
//        // mUrlDisplayTextView.setText(flightSearchUrl.toString());
//        // COMPLETED (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
//        new FlightQueryTask().execute(flightSearchUrl);
//        Log.i("the FlightSearchQuery","departureCity");
//    }
//
//    // COMPLETED (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
//    public class FlightQueryTask extends AsyncTask<URL, Void, String> {
//
//        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
//        @Override
//        protected String doInBackground(URL... params) {
//            URL searchUrl = params[0];
//            String flightSearchResults = null;
//            try {
//                flightSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return flightSearchResults;
//        }

        // COMPLETED (3) Override onPostExecute to display the results in the TextView
//        @Override
//        protected void onPostExecute(String flightSearchResults) {
//            if (flightSearchResults != null && !flightSearchResults.equals("")) {
//                mFlightResultsTextView.setText(flightSearchResults);
//            }
//        }
//    }

//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            getMenuInflater().inflate(R.menu.main, menu);
//            return true;
//        }


//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            int itemThatWasClickedId = item.getItemId();
//            if (itemThatWasClickedId == R.id.action_search) {
//                makeGithubSearchQuery();
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
    }


