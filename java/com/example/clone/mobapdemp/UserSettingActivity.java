package com.example.clone.mobapdemp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserSettingActivity extends AppCompatActivity {

    Button buttonBack, buttonSave;
    EditText etFirstName, etLastName, etMobileNumber, etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        buttonBack = (Button) findViewById(R.id.bt_back);
        buttonSave = (Button) findViewById(R.id.bt_save);

        etFirstName = (EditText) findViewById(R.id.et_fname);
        etLastName = (EditText) findViewById(R.id.et_lname);
        etMobileNumber = (EditText) findViewById(R.id.et_unumber);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        etFirstName.setText(sp.getString(MainActivity.SP_KEY_FIRSTNAME, null));
        etLastName.setText(sp.getString(MainActivity.SP_KEY_LASTNAME, null));
        etMobileNumber.setText(sp.getString(MainActivity.SP_KEY_MOBILENUMBER, null));

        if(sp.getBoolean(MainActivity.SP_KEY_ISREGISTERED, false) == false){
            buttonBack.setVisibility(View.INVISIBLE);
        } else {
            buttonBack.setVisibility(View.VISIBLE);
        }

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

                if ((!(firstName.isEmpty()) || !(lastName.isEmpty())) && !(mobileNumber.isEmpty())) {
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor spEditor = sp.edit();
                    spEditor.putString(MainActivity.SP_KEY_FIRSTNAME, firstName);
                    spEditor.putString(MainActivity.SP_KEY_LASTNAME, lastName);
                    spEditor.putString(MainActivity.SP_KEY_MOBILENUMBER, mobileNumber);
                    spEditor.putBoolean(MainActivity.SP_KEY_ISREGISTERED, true);
                    spEditor.commit();

                    finish();
                    Toast.makeText(getApplication(), "User Detail Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Please fill out your name and mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
