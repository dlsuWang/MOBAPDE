package com.example.aakov.emergencyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonUserDetail, buttonEmergencyList, buttonContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUserDetail = (Button) findViewById(R.id.button_main_user_detail);
        buttonEmergencyList = (Button) findViewById(R.id.button_main_emergency_list);
        buttonContactList = (Button) findViewById(R.id.button_main_contact_list);

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
