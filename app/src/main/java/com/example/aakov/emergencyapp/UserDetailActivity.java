package com.example.aakov.emergencyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserDetailActivity extends AppCompatActivity {

    Button buttonBack, buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        buttonBack = (Button) findViewById(R.id.button_user_detail_back);
        buttonSave = (Button) findViewById(R.id.button_user_detail_save);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save User Detail

                finish();
                Toast.makeText(getApplication(), "User Detail Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
