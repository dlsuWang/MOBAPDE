package com.example.clone.mobapdemp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewContactActivity extends AppCompatActivity {

    Button bt_back, bt_chagne, bt_delete;
    EditText et_name, et_number;
    DatabaseOpenHelper databaseOpenHelper;
    Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "ViewContactActivity", Toast.LENGTH_LONG).show();

        bt_back = (Button)findViewById(R.id.bt_back);
        bt_chagne = (Button)findViewById(R.id.bt_change);
        bt_delete = (Button)findViewById(R.id.bt_delete);

        et_name = (EditText)findViewById(R.id.et_title);
        et_number = (EditText)findViewById(R.id.et_combination);

        int id = getIntent().getExtras().getInt(Contact.COLUMN_ID);

        // get note from db given id
        databaseOpenHelper = new DatabaseOpenHelper(getBaseContext());
        currentContact = databaseOpenHelper.queryContact(id);

        // set text fo etTitle and etNote to the note's content
        et_name.setText(currentContact.getName());
        et_number.setText(currentContact.getNumber());

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseOpenHelper.deleteContact(currentContact.getId());
                finish();
            }
        });

        bt_chagne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact updateContact = new Contact();
                updateContact.setId(currentContact.getId());
                updateContact.setName(et_name.getText().toString());
                updateContact.setNumber(et_number.getText().toString());

                databaseOpenHelper.updateContact(updateContact);
                finish();
            }
        });



    }

}
