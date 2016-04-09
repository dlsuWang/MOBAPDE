package com.example.clone.mobapdemp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContentsSettingActivity extends AppCompatActivity {

    Button bt_back, bt_add;
    RecyclerView rv_content;

    DatabaseOpenHelper dbHelper;
    ContentCursorAdapter contentCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bt_back = (Button) findViewById(R.id.bt_back);
        bt_add = (Button) findViewById(R.id.bt_add);

        rv_content = (RecyclerView) findViewById(R.id.recyclerview_content);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), AddContentActivity.class);
                startActivity(i);
            }
        });

        dbHelper = new DatabaseOpenHelper(getBaseContext());

        contentCursorAdapter = new ContentCursorAdapter(getBaseContext(), null);
        contentCursorAdapter.setmOnItemClickListener(new ContentCursorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent viewIntent = new Intent();
                viewIntent.setClass(getBaseContext(), ViewContentActivity.class);
                viewIntent.putExtra(Content.COLUMN_ID, id);
                startActivity(viewIntent);
                Toast.makeText(getApplicationContext(), "Content clicked", Toast.LENGTH_LONG).show();
            }
        });

        rv_content.setAdapter(contentCursorAdapter);
        rv_content.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Cursor cursor = dbHelper.queryAllContent();
        contentCursorAdapter.swapCursor(cursor);
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_LONG).show();
    }
}
