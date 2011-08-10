/* $Id: $
 */
package com.oreilly.demo.android.demo.simplefragment3;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oreilly.demo.android.demo.simplefragment3.R;


/**
 * DateTime
 */
public class DateTime extends Fragment {
    /** Bundle tag for Date/Time */
    public static final String TAG_DATE_TIME = "DateTime";
    

    private static String getDateTimeString(Date time) {
        return new SimpleDateFormat("d MMM yyyy HH:mm:ss")
            .format(time);
    }

    private String time;

    /** @see android.app.Fragment#onCreate(android.os.Bundle) */
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        time = (null == state) 
            ? getDateTimeString(new Date())
            : state.getString(TAG_DATE_TIME);
    }

    /** @see android.app.Fragment#onSaveInstanceState(android.os.Bundle) */
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(TAG_DATE_TIME, time);
    }

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
