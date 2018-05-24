package com.example.cair0806.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    DataBaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editAddress = findViewById(R.id.editText_address);
        editNumber = findViewById(R.id.editText_number);

        myDb = new DataBaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiated DataBasehelper");
    }

    public void addData(View view){
        Log.d("MyContactApp", "MainActivity: add contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editNumber.getText().toString());
        if(isInserted == true){
            Toast.makeText(MainActivity.this, "success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "failed - contact not inserted", Toast.LENGTH_LONG).show();
        }
    }
}

