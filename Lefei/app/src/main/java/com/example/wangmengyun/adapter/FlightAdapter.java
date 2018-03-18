package com.example.wangmengyun.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.lefei.R;

import java.util.List;


public class FlightAdapter extends ArrayAdapter<Flight> {
    private static final String LOG_TAG = FlightAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param context The current context. Used to inflate the layout file.
     * @param flights A List of AndroidFlavor objects to display in a list
     */
    public FlightAdapter(Activity context, List<Flight> flights) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, flights);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        Flight flight = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_flight, parent, false);
        }

        TextView departure_city = (TextView) convertView.findViewById(R.id.departure_city);
        departure_city.setText(flight.departure_City);

        TextView arrive_city = (TextView) convertView.findViewById(R.id.arrive_city);
        arrive_city.setText(flight.arrive_City);

        TextView departure_date = (TextView) convertView.findViewById(R.id.departure_date);
        departure_date.setText(flight.departure_Date);

        TextView arrive_date = (TextView) convertView.findViewById(R.id.arrive_date);
        arrive_date.setText(flight.arrive_Date);
        return convertView;
    }
}
