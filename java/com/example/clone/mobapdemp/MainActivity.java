package com.example.clone.mobapdemp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseOpenHelper databaseOpenHelper;

    Button bt_user, bt_content, bt_number, bt_sms;
    EditText et_id;

    Button bt_location;
    TextView tv_location;
    LocationManager locationManager;
    LocationListener locationListener;

    ArrayList<Contact> contacts = new ArrayList<>();

    double longitude, latitude;
    final static String SP_KEY_FIRSTNAME = "firstname";
    final static String SP_KEY_LASTNAME = "lastname";
    final static String SP_KEY_MOBILENUMBER = "mobilenumber";
    final static String SP_KEY_ISREGISTERED = "isregistered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bt_user = (Button) findViewById(R.id.bt_user);
        bt_content = (Button) findViewById(R.id.bt_content);
        bt_number = (Button) findViewById(R.id.bt_number);
        bt_sms = (Button) findViewById(R.id.bt_sms);
        et_id = (EditText) findViewById(R.id.et_id);

        bt_location = (Button) findViewById(R.id.bt_location);
        tv_location = (TextView) findViewById(R.id.tv_location);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //Toast.makeText(getBaseContext(), "before append", Toast.LENGTH_LONG).show();
                tv_location.append("\n" + location.getLatitude() + " " + location.getLongitude());
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                //Toast.makeText(getBaseContext(), location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                }, 10);
                return;
            }
        }else {
            configureButton();
        }


        databaseOpenHelper = new DatabaseOpenHelper(getBaseContext());

        bt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), UserSettingActivity.class);
                startActivity(i);
            }
        });

        bt_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), ContentsSettingActivity.class);
                startActivity(i);
            }
        });

        bt_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), ContactSettingActivity.class);
                startActivity(i);
            }
        });

        bt_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();

                /*
                String number = "123456";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, "HI im here")));
                */
                /*
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.putExtra("sms_body", "default content");
                i.setType("vnd.android.dir/mms-sms");
                startActivity(i);*/
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
        }
    }

    private void configureButton() {
        bt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
                Toast.makeText(getBaseContext(), "button Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sendSMS(){
        SmsManager sm = SmsManager.getDefault();
        contacts = databaseOpenHelper.queryAllContact_array();

        for(int i = 0; i < contacts.size(); i++){
            Content msgContent = new Content();
            String msgContact = "";
            msgContact = contacts.get(i).getNumber();
            msgContent = databaseOpenHelper.queryContent(Integer.parseInt(et_id.getText().toString()));



            String msg =
                    "Hi, it's " + "\n"
                    + et_id.getText().toString() + "\n"
                    + msgContent.getContent() + "\n"
                    + "http://maps.google.com/?q="
                    + latitude + "," + longitude;
            sm.sendTextMessage(msgContact, null, msg, null, null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String firstname = sp.getString(SP_KEY_FIRSTNAME, null);
        final String lastname = sp.getString(SP_KEY_LASTNAME, null);
        final String mobilenumber = sp.getString(SP_KEY_MOBILENUMBER, null);
        final boolean isregistered = sp.getBoolean(SP_KEY_ISREGISTERED, false);


        if ((firstname != null || lastname != null) && mobilenumber != null){
            // Put contact details here
        } else {
            Intent i = new Intent();
            i.setClass(getBaseContext(), UserSettingActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
