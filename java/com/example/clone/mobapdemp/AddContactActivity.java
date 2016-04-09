package com.example.clone.mobapdemp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    Button bt_save, bt_back;
    EditText et_name, et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "AddContactActivity", Toast.LENGTH_LONG).show();

        bt_back = (Button) findViewById(R.id.bt_back);
        bt_save = (Button) findViewById(R.id.bt_save);

        et_name = (EditText) findViewById(R.id.et_title);
        et_number = (EditText) findViewById(R.id.et_combination);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String number = et_number.getText().toString();

                // check if it's not empty
                if(!name.isEmpty() && !number.isEmpty()){

                    // add to db
                    DatabaseOpenHelper db = new DatabaseOpenHelper(getBaseContext());
                    Contact contact = new Contact(name, number);
                    db.insertContact(contact);

                    // finish
                    finish();
                }
                Toast.makeText(getApplication(), "SAVED", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
