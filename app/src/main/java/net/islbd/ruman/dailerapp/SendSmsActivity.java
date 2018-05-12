package net.islbd.ruman.dailerapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static net.islbd.ruman.dailerapp.RequestCode.REQUEST_SEND_SMS;

public class SendSmsActivity extends AppCompatActivity {

    EditText phoneNumberEdittext;
    String phonerNumberVal;
    Button sendSmsbutton;
    EditText textAreaofSms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        phoneNumberEdittext = findViewById(R.id.editTextphonNumber);
        Intent getNumberIntent =  getIntent();
        phonerNumberVal = getNumberIntent.getStringExtra("phoneNumber");
        phoneNumberEdittext.setText(phonerNumberVal);
        sendSmsbutton = findViewById(R.id.btnSendSmsByText);
        sendSmsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textAreaofSms = findViewById(R.id.textAreaSms);
                sendSMSMessage();
            }
        });

    }

    protected void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phonerNumberVal, null, textAreaofSms.toString(), null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}
