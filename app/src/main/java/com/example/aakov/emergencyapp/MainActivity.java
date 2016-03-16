package com.example.aakov.emergencyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonUserDetail, buttonEmergencyList, buttonContactList;

    final static String SP_KEY_FIRSTNAME = "firstname";
    final static String SP_KEY_LASTNAME = "lastname";
    final static String SP_KEY_MOBILENUMBER = "mobilenumber";
    final static String SP_KEY_ISREGISTERED = "isregistered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUserDetail = (Button) findViewById(R.id.button_main_user_detail);
        buttonEmergencyList = (Button) findViewById(R.id.button_main_emergency_list);
        buttonContactList = (Button) findViewById(R.id.button_main_contact_list);

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String firstname = sp.getString(SP_KEY_FIRSTNAME, null);
        final String lastname = sp.getString(SP_KEY_LASTNAME, null);
        final String mobilenumber = sp.getString(SP_KEY_MOBILENUMBER, null);
        final boolean isregistered = sp.getBoolean(SP_KEY_ISREGISTERED, false);

        if ((firstname != null || lastname != null) && mobilenumber != null){
            // Put contact details here
        } else {
            Intent i = new Intent();
            i.setClass(getBaseContext(), UserDetailActivity.class);
            startActivity(i);
        }

        buttonUserDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), UserDetailActivity.class);
                startActivity(i);
            }
        });

        buttonEmergencyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), EmergencyListActivity.class);
                startActivity(i);
            }
        });

        buttonContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), ContactListActivity.class);
                startActivity(i);
            }
        });

    }
}
