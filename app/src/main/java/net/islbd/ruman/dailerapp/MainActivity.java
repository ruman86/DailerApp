package net.islbd.ruman.dailerapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static net.islbd.ruman.dailerapp.RequestCode.REQUEST_PHONE_CALL;
import static net.islbd.ruman.dailerapp.RequestCode.REQUEST_READ_CONTACTS;
import static net.islbd.ruman.dailerapp.RequestCode.REQUEST_SEND_SMS;

public class MainActivity extends AppCompatActivity implements OnPassPhoneNumberListner {

    EditText editPhoneNumber;
    Intent i;
    String phoneNumber;
    Intent sendSmsIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        Button callButton = findViewById(R.id.btnCall);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + editPhoneNumber.getText().toString()));
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                } else {
                    startActivity(i);
                }

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.SEND_SMS)) {

                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
                    }
                }
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),"Permission Denied to phone call", Toast.LENGTH_LONG).show();
                }

            }
            break;
            case REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // show contacct to lsit view
                    ContactListFragment contactListFragment = (ContactListFragment) getSupportFragmentManager().findFragmentById(R.id.contactListFragment);
                    contactListFragment.showContactsToListView();
                } else {
                    Toast.makeText(getApplicationContext(),"Permission Denied to access Contact", Toast.LENGTH_LONG).show();
                }

            }
            break;
            case REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getApplicationContext(),"Permission Denied to Send SMS", Toast.LENGTH_LONG).show();
                }

            }
        }
    }
    @Override
    public void passPhoneNumberListner(Contact contact) {
        editPhoneNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public void keyboardEvent(String getVal) {

        if(getVal =="call"){
            startActivity(i);
        }else if(getVal =="sms"){
            sendSmsIntent = new Intent(this, SendSmsActivity.class);
            sendSmsIntent.putExtra("phoneNumber", phoneNumber);
            startActivity(sendSmsIntent);
        }else{
            editPhoneNumber.setText(editPhoneNumber.getText()+getVal);
            editPhoneNumber.setSelection(editPhoneNumber.getText().length());
            phoneNumber = editPhoneNumber.getText().toString();
        }
    }



}
