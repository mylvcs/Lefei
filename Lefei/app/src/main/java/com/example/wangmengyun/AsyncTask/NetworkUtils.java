package com.example.wangmengyun.AsyncTask;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

    /**
     * These utilities will be used to communicate with the network.
     */
    public class NetworkUtils {

        final static String Flight_BASE_URL ="https://api.github.com/search/repositories";
             //TODO   "https://api.github.com/search/repositories";

        final static String PARAM_QUERY = "q";

        /*
         * The sort field. One of stars, forks, or updated.
         * Default: results are sorted by best match if no field is specified.
         */
       final static String PARAM_SORT = "sort";
        final static String sortBy = "stars";

        /**
         * Builds the URL used to query GitHub.
         *
         * @param
         * @return 比如出发城市是SHA， 到达城市是BJ，可以传到携程的那个查询的URL里。
         */
        public static URL buildUrl(String flightSearchQuery) {
            Uri builtUri = Uri.parse(Flight_BASE_URL).buildUpon()
                    .appendQueryParameter(PARAM_QUERY, flightSearchQuery)
                    .appendQueryParameter(PARAM_SORT,sortBy)
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
        }

        /**
         * This method returns the entire result from the HTTP response.
         *
         * @param url The URL to fetch the HTTP response from.
         * @return The contents of the HTTP response.
         * @throws IOException Related to network and stream reading
         */
        public static String getResponseFromHttpUrl(URL url) throws IOException {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = urlConnection.getInputStream();

                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    return scanner.next();
                } else {
                    return null;
                }
            } finally {
                urlConnection.disconnect();
            }
        }
    }

