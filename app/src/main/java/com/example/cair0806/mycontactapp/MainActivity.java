package com.example.cair0806.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {



    com.example.cair0806.mycontactsappp1.DataBaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new com.example.cair0806.mycontactsappp1.DataBaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiated DataBasehelper");
    }
}

