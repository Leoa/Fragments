/* $Id: $
 */
package com.oreilly.demo.android.demo.simplefragment1;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oreilly.demo.android.demo.simplefragment1.R;


/**
 * DateTime
 */
public class DateTime extends Fragment {
    private static String getDateTimeString(Date time) {
        return new SimpleDateFormat("d MMM yyyy HH:mm:ss")
            .format(time);
    }

    private final String time = getDateTimeString(new Date());

    /** @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle) */
    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle b)
    {
        View view = inflater.inflate(
            R.layout.date_time,
            container,
            false);  //!!! this is important

        ((TextView) view.findViewById(R.id.last_view_time))
            .setText(time);

        return view;
    }
}