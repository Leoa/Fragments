/* $Id: $
 */
package com.oreilly.demo.android.demo.simplefragment1;

import android.app.Activity;
import android.os.Bundle;

import com.oreilly.demo.android.demo.simplefragment1.R;


/**
 * ContactViewer
 */
public class SimpleFragment1 extends Activity {
 
    /** @see android.app.Activity#onCreate(android.os.Bundle) */
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        setContentView(R.layout.main);
    }
}
