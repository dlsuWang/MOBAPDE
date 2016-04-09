package com.example.clone.mobapdemp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContactSettingActivity extends AppCompatActivity {

    Button bt_back, bt_addNumber;
    RecyclerView rv_contact;

    DatabaseOpenHelper dbHelper;
    ContactCursorAdapter contactCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bt_back = (Button) findViewById(R.id.bt_back);
        bt_addNumber = (Button) findViewById(R.id.bt_addNumber);
        rv_contact = (RecyclerView) findViewById(R.id.recyclerview_contact);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), AddContactActivity.class);
                startActivity(i);
            }
        });


        dbHelper = new DatabaseOpenHelper(getBaseContext());

        contactCursorAdapter = new ContactCursorAdapter(getBaseContext(), null);
        contactCursorAdapter.setmOnItemClickListener(new ContactCursorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent viewIntent = new Intent();
                viewIntent.setClass(getBaseContext(), ViewContactActivity.class);
                viewIntent.putExtra(Contact.COLUMN_ID, id);
                startActivity(viewIntent);
                Toast.makeText(getApplicationContext(), "Contact clicked", Toast.LENGTH_LONG).show();
            }
        });

        rv_contact.setAdapter(contactCursorAdapter);
        rv_contact.setLayoutManager(new LinearLayoutManager(getBaseContext()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = dbHelper.queryAllContact();
        contactCursorAdapter.swapCursor(cursor);
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_LONG).show();

    }
}
