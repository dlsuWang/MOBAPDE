package com.example.aakov.emergencyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddEmergencyActivity extends AppCompatActivity {

    Button buttonSave, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency);

        buttonSave = (Button) findViewById(R.id.button_add_emergency_save);
        buttonBack = (Button) findViewById(R.id.button_add_emergency_back);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save Emergency

                finish();
                Toast.makeText(getApplication(), "Emergency Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
