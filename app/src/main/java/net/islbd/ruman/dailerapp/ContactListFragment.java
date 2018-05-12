package net.islbd.ruman.dailerapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static net.islbd.ruman.dailerapp.RequestCode.REQUEST_READ_CONTACTS;


public class ContactListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
ListView contactListView;
OnPassPhoneNumberListner mListner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        contactListView = view.findViewById(R.id.contactListView);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            // ask user about permision to read contacts
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE}, REQUEST_READ_CONTACTS);

        }else {
            showContactsToListView();
        }
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Contact contact = (Contact) adapterView.getItemAtPosition(position);
                mListner.passPhoneNumberListner(contact);
            }
        });

        return view;
    }

    public void showContactsToListView() {
        ArrayList<Contact> contacts = getAllContact();
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(getContext(), android.R.layout.simple_list_item_1, contacts);
        contactListView.setAdapter(adapter);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPassPhoneNumberListner) {
            mListner = (OnPassPhoneNumberListner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        if(cursor.moveToFirst()){
            String name="";
            String mobileNumber="";
            do{
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                mobileNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Contact contact = new Contact();
                contact.setName(name);
                contact.setPhoneNumber(mobileNumber);
                contactArrayList.add(contact);
            }while (cursor.moveToNext());
        }

        return contactArrayList;
    }
}
