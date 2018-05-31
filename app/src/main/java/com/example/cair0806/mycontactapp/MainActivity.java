package com.example.cair0806.mycontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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

    public void addData(View view) {
        Log.d("MyContactApp", "MainActivity: add contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editNumber.getText().toString());
        if (isInserted == true) {
            Toast.makeText(MainActivity.this, "success - contact inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "failed - contact not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData(View view) {
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp", "MainActivity: viewData: received cursor " + res.getCount());
        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");

        }
        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {

                buffer.append("ID: " + res.getString(0));
                buffer.append("\n");
                buffer.append("name: " + res.getString(1));
                buffer.append("\n");
                buffer.append("address: " + res.getString(2));
                buffer.append("\n");
                buffer.append("number: " + res.getString(3));
                buffer.append("\n");

            //append res column 0,1,2,3 to the string buffer, delimited by the "/n"

        }
        Log.d("MyContactApp", "MainActivity: viewData: assembled string buffer");
        showMessage("Data", buffer.toString());

    }

    public void showMessage(String title, String messages) {
        Log.d("MyContactApp", "MainActivity: showMessage: building alert dialogue");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(messages);
        builder.show();

    }
    public static final String EXTRA_MESSAGE = "com.example.cair0806.mycontactapp.MESSAGE";
    public void searchRecord(View view) {

        Log.d("MyContactApp", "MainActivity:launching searchActivity");
        Cursor res = myDb.getAllData();
        Intent intent = new Intent(this, SearchActivity.class);

        StringBuffer buffer = new StringBuffer();
        int lol = res.getCount();
        int c  = 0;
        while (res.moveToNext()) {
            if (res.getString(1).equals(editName.getText().toString())) {
                buffer.append("ID: " + res.getString(0));
                buffer.append("\n");
                buffer.append("name: " + res.getString(1));
                buffer.append("\n");
                buffer.append("address: " + res.getString(2));
                buffer.append("\n");
                buffer.append("number: " + res.getString(3));
                buffer.append("\n");
            }
            else{
                c++;
            }

        }
        if(c == lol) {
            buffer.append("no contact found");
        }
        intent.putExtra(EXTRA_MESSAGE, buffer.toString());
        startActivity(intent);
    }
}

