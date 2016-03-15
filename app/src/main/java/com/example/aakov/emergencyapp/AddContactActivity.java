package com.example.aakov.emergencyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    Button buttonSave, buttonBack, buttonAddContactImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        buttonSave = (Button) findViewById(R.id.button_add_contact_save);
        buttonBack = (Button) findViewById(R.id.button_add_contact_back);
        buttonAddContactImage = (Button) findViewById(R.id.button_add_contact_image);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save contact here

                finish();
                Toast.makeText(getApplication(), "Contact Saved", Toast.LENGTH_SHORT).show();
            }
        });

        buttonAddContactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add Contact Image
            }
        });
    }
}
