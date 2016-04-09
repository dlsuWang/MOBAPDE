package com.example.clone.mobapdemp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContentActivity extends AppCompatActivity {

    Button bt_save, bt_back;
    EditText et_title, et_combination, et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "AddContentActivity", Toast.LENGTH_LONG).show();

        bt_back = (Button) findViewById(R.id.bt_back);
        bt_save = (Button) findViewById(R.id.bt_save);

        et_title = (EditText) findViewById(R.id.et_title);
        et_combination = (EditText) findViewById(R.id.et_combination);
        et_content = (EditText) findViewById(R.id.et_content);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String combination = et_combination.getText().toString();
                String contentIn = et_content.getText().toString();

                // check if it's not empty
                if(!title.isEmpty() && !combination.isEmpty() && !contentIn.isEmpty()){

                    // add to db
                    DatabaseOpenHelper db = new DatabaseOpenHelper(getBaseContext());
                    Content content = new Content(title, combination, contentIn);
                    db.insertContent(content);

                    Toast.makeText(getApplication(), "SAVED", Toast.LENGTH_SHORT).show();

                    // finish
                    finish();
                }

            }
        });


    }

}
