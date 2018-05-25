package com.example.cair0806.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2018.db";
    public static final String TABLE_NAME = "Contact2018_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_NAME_ADDRESS = "address";
    public static final String COLUMN_NAME_NUMBER = "phone";


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_NAME_CONTACT + " TEXT," + COLUMN_NAME_ADDRESS + " TEXT," + COLUMN_NAME_NUMBER + " TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("MyContactApp", "Databasehelper: constructed DataBasehelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp", "Databasehelper: creating DataBasehelper");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp", "Databasehelper: upgrading DataBasehelper");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public boolean insertData(String name, String address, String  number){
        Log.d("MyContactApp", "Databasehelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(COLUMN_NAME_CONTACT, name);
        contentValue.put(COLUMN_NAME_ADDRESS, address);
        contentValue.put(COLUMN_NAME_NUMBER, number);
        long result = db.insert(TABLE_NAME, null, contentValue);

        if(result == -1){
            Log.d("MyContactApp", "Databasehelper: contact insert failed");
            return false;
        }
        else{
            Log.d("MyContactApp", "Databasehelper: contact insert passed");
            return true;
        }
    }
    public Cursor getAllData(){
        Log.d("MyContactApp", "DatabaseHelper: calling getAllData method");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
