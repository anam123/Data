package com.example.anambhatia.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    TextView tv;
    Button add;
    long data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button) findViewById(R.id.button);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        tv= (TextView) findViewById(R.id.textView6);

        if (sharedPreferences.contains("total")) {
            data = sharedPreferences.getLong("total",0) ;
            tv.setText(Long.toString(data));
        }
    }

    public void review(View v)
    {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }

}