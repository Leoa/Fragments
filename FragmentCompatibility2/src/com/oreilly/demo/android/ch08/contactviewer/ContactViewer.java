/* $Id: $
 */
package com.oreilly.demo.android.ch08.contactviewer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.oreilly.demo.android.ch08.contactviewer.R;


/**
 * ContactViewer
 */
public class ContactViewer extends FragmentActivity {
    private static final String FRAG_TAG
        = ContactViewer.class.getCanonicalName() + ".fragment";
    
    private static final String[] CONTACTS_PROJECTION
        = new String[] {
            BaseColumns._ID,
            Contacts.CONTACT_PRESENCE,
            Contacts.DISPLAY_NAME
        };

    private static final String CONTACTS_FILTER
        = "((" + Contacts.DISPLAY_NAME + " NOT NULL)"
            + " AND (" + Contacts.DISPLAY_NAME + " != ''))";

    private static final String CONTACTS_SORT
        = Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

    private ListView contacts;

    /** @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle) */
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        
        setContentView(R.layout.main);

        final boolean useFrag
            = null != findViewById(R.id.contact_detail);
        
        if (useFrag) { installFragment(); }

        contacts = (ListView) findViewById(R.id.contacts);
        setAdapter(contacts);
        contacts.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id)
                {
                    launchDetails(position, useFrag);
                } });
    }

    /** @see android.app.Activity#onRestart() */
    @Override
    public void onRestart() {
        super.onRestart();
        setAdapter(contacts);
    }

    void launchDetails(int pos, boolean useFrag) {
        Cursor cursor = (Cursor) contacts.getAdapter().getItem(pos);

        String id = cursor.getString(
            cursor.getColumnIndex(BaseColumns._ID));
        String name = cursor.getString(
                cursor.getColumnIndex(Contacts.DISPLAY_NAME));

        if (useFrag) { stackFragment(id, name); }
        else { stackActivity(id, name); }
    }

    private void installFragment() {
        FragmentManager fragMgr = getSupportFragmentManager();
        
        if (null != fragMgr.findFragmentByTag(FRAG_TAG)) { return; }

        FragmentTransaction xact = fragMgr.beginTransaction();
        xact.add(
            R.id.contact_detail,
            ContactDetailFragment.newInstance(null, null),
            FRAG_TAG);
        xact.commit();
    }

    private void stackFragment(String id, String name) {
        FragmentTransaction xact
            = getSupportFragmentManager().beginTransaction();

        xact.replace(
            R.id.contact_detail,
            ContactDetailFragment.newInstance(id, name),
            FRAG_TAG);

        xact.addToBackStack(null);
        xact.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        xact.commit();
    }

    private void stackActivity(String id, String name) {
        Intent intent = new Intent();
        intent.setClass(this, ContactDetailActivity.class);
        intent.putExtra(ContactDetails.TAG_ID, id);
        intent.putExtra(ContactDetails.TAG_CONTACT, name);
        startActivity(intent);
    }

    private void setAdapter(ListView view) {
        if (null == view) { return; }
        view.setAdapter(
            new ContactsCursorAdapter(
                this,
                managedQuery(
                    Contacts.CONTENT_URI,
                    CONTACTS_PROJECTION,
                    CONTACTS_FILTER,
                    null,
                    CONTACTS_SORT)));
    }
}
