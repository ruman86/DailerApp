package net.islbd.ruman.dailerapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PhoneDailerFragment extends Fragment implements View.OnClickListener {
    Button oneBtnval;
    Button valTwo, valThree, valFour, valFive, valSix, valSeven, valEight, valNine, valZero, valStar, valHash, makeCall, sendSms;
    String getKeyval;
    OnPassPhoneNumberListner keyborardListner;
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnOne:
                getKeyval = (String) oneBtnval.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnTwo:
                getKeyval = (String) valTwo.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnThree:
                getKeyval = (String) valThree.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnFour:
                getKeyval = (String) valFour.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnFive:
                getKeyval = (String) valFive.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnSix:
                getKeyval = (String) valSix.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnSeven:
                getKeyval = (String) valSeven.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnEight:
                getKeyval = (String) valEight.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnNine:
                getKeyval = (String) valNine.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnzero:
                getKeyval = (String) valZero.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnHash:
                getKeyval = (String) valHash.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnStar:
                getKeyval = (String) valStar.getText();
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btncall:
                getKeyval = "call";
                keyborardListner.keyboardEvent(getKeyval);
                break;
            case R.id.btnSendSms:
                getKeyval = "sms";
                keyborardListner.keyboardEvent(getKeyval);
                break;
            default:
                // TODO Auto-generated method stub
                break;
        }
    }


    public PhoneDailerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if (activity instanceof OnPassPhoneNumberListner) {
            keyborardListner = (OnPassPhoneNumberListner) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phone_dailer, null);
        //return inflater.inflate(R.layout.fragment_blank, container, false);
        oneBtnval = (Button) v.findViewById(R.id.btnOne);
        valTwo = (Button) v.findViewById(R.id.btnTwo);
        valThree = (Button) v.findViewById(R.id.btnThree);
        valFour = (Button) v.findViewById(R.id.btnFour);
        valFive = (Button) v.findViewById(R.id.btnFive);
        valSix = (Button) v.findViewById(R.id.btnSix);
        valSeven = (Button) v.findViewById(R.id.btnSeven);
        valEight = (Button) v.findViewById(R.id.btnEight);
        valNine = (Button) v.findViewById(R.id.btnNine);
        valZero = (Button) v.findViewById(R.id.btnzero);
        valStar = (Button) v.findViewById(R.id.btnStar);
        valHash = (Button) v.findViewById(R.id.btnHash);
        makeCall = v.findViewById(R.id.btncall);
        sendSms  = v.findViewById(R.id.btnSendSms);
        oneBtnval.setOnClickListener(this);
        valTwo.setOnClickListener(this);
        valThree.setOnClickListener(this);
        valFour.setOnClickListener(this);
        valFive.setOnClickListener(this);
        valSix.setOnClickListener(this);
        valSeven.setOnClickListener(this);
        valEight.setOnClickListener(this);
        valNine.setOnClickListener(this);
        valZero.setOnClickListener(this);
        valStar.setOnClickListener(this);
        valHash.setOnClickListener(this);
        makeCall.setOnClickListener(this);
        sendSms.setOnClickListener(this);
        return v;
    }
}
