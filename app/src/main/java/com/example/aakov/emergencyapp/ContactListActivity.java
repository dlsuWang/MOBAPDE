package com.example.aakov.emergencyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactListActivity extends AppCompatActivity {

    Button buttonAdd, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        buttonAdd = (Button) findViewById(R.id.button_contact_list_add);
        buttonBack = (Button) findViewById(R.id.button_contact_list_back);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), AddContactActivity.class);
                startActivity(i);
            }
        });

    }
}
