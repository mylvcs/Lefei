package com.example.wangmengyun.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.wangmengyun.Bean.Flight;
import com.example.wangmengyun.adapter.FlightAdapter;
import com.example.wangmengyun.lefei.R;

import java.util.Arrays;

/**
 * A fragment containing the list view of Android versions.
 */
public class FlightActivityFragment extends Fragment {

    private FlightAdapter flightAdapter;

    Flight[] flight = {
            new Flight("Shanghai", "Beijing","2017/12/21","2017/12/22"),
            new Flight("Shanghai", "Seattle","2018/03/23","20180324"),
            new Flight("NewYork", "Shanghai","2018/03/03","2018/03/04"),

    };

    public FlightActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flight, container, false);

        flightAdapter = new FlightAdapter(getActivity(), Arrays.asList(flight));

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.list_flight);
        listView.setAdapter(flightAdapter);

        return rootView;
    }
}