package com.example.clone.mobapdemp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewContentActivity extends AppCompatActivity {

    Button bt_back, bt_chagne, bt_delete;
    EditText et_title, et_combination, et_content;
    DatabaseOpenHelper databaseOpenHelper;
    Content currentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "ViewContentActivity", Toast.LENGTH_LONG).show();

        bt_back = (Button)findViewById(R.id.bt_back);
        bt_chagne = (Button)findViewById(R.id.bt_change);
        bt_delete = (Button)findViewById(R.id.bt_delete);

        et_title = (EditText)findViewById(R.id.et_title);
        et_combination = (EditText)findViewById(R.id.et_combination);
        et_content = (EditText)findViewById(R.id.et_content);

        int id = getIntent().getExtras().getInt(Content.COLUMN_ID);

        // get note from db given id
        databaseOpenHelper = new DatabaseOpenHelper(getBaseContext());
        currentContent = databaseOpenHelper.queryContent(id);

        // set text fo etTitle and etNote to the note's content
        et_title.setText(currentContent.getTitle());
        et_combination.setText(currentContent.getCombination());
        et_content.setText(currentContent.getContent());

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseOpenHelper.deleteContent(currentContent.getId());
                finish();
            }
        });

        bt_chagne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content updateContent = new Content();
                updateContent.setId(currentContent.getId());
                updateContent.setTitle(et_title.getText().toString());
                updateContent.setCombination(et_combination.getText().toString());
                updateContent.setContent(et_content.getText().toString());

                databaseOpenHelper.updateContent(updateContent);
                finish();
            }
        });


    }

}
