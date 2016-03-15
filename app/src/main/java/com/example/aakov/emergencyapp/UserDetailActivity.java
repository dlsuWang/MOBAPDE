package com.example.aakov.emergencyapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserDetailActivity extends AppCompatActivity {

    Button buttonBack, buttonSave;
    EditText etFirstName, etLastName, etMobileNumber, etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        buttonBack = (Button) findViewById(R.id.button_user_detail_back);
        buttonSave = (Button) findViewById(R.id.button_user_detail_save);

        etFirstName = (EditText) findViewById(R.id.et_user_detail_first_name);
        etLastName = (EditText) findViewById(R.id.et_user_detail_last_name);
        etMobileNumber = (EditText) findViewById(R.id.et_user_detail_mobile_number);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        etFirstName.setText(sp.getString(MainActivity.SP_KEY_FIRSTNAME, null));
        etLastName.setText(sp.getString(MainActivity.SP_KEY_LASTNAME, null));
        etMobileNumber.setText(sp.getString(MainActivity.SP_KEY_MOBILENUMBER, null));

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
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String mobileNumber = etMobileNumber.getText().toString();

                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString(MainActivity.SP_KEY_FIRSTNAME, firstName);
                spEditor.putString(MainActivity.SP_KEY_LASTNAME, lastName);
                spEditor.putString(MainActivity.SP_KEY_MOBILENUMBER, mobileNumber);
                spEditor.commit();

                finish();
                Toast.makeText(getApplication(), "User Detail Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
