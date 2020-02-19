package com.example.alisha_finalassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.PropertyResourceBundle;

public class PersonDataBaseHelper extends SQLiteOpenHelper {



    private static final String DATABASE_NAME = "PersonDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "person";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FNAME = "fname";
    private static final String COLUMN_LNAME = "lname";
    private static final String COLUMN_PHONE_NUMBER = "phone_no";
    private static final String COLUMN_ADDRESS = "addr";

    public PersonDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT person_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FNAME + " varchar(200) NOT NULL, " +
                COLUMN_LNAME + " varchar(200) NOT NULL, " +
                COLUMN_PHONE_NUMBER + " INTEGER NOT NULL, " +
                COLUMN_ADDRESS + " varchar(200) NOT NULL); ";

        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    boolean addPerson(String first_name, String last_name, int phone_no, String u_address ){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FNAME, first_name);
        cv.put(COLUMN_LNAME, last_name);
        cv.put(COLUMN_PHONE_NUMBER, String.valueOf(phone_no));
        cv.put(COLUMN_ADDRESS, u_address);

        return  sqLiteDatabase.insert(TABLE_NAME,null,cv) != -1;
    }

    Cursor getAllPerson(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

    }

    boolean updatePerson(int id, String first_name, String last_name, int phone_no, String u_address){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FNAME, first_name);
        cv.put(COLUMN_LNAME, last_name);
        cv.put(COLUMN_PHONE_NUMBER, String.valueOf(phone_no));
        cv.put(COLUMN_ADDRESS, u_address);

        return sqLiteDatabase.update(TABLE_NAME,cv,COLUMN_ID + "= ?", new String[]{String.valueOf(id)}) > 0;
    }

    boolean deletePerson(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID + "=?", new String[]{String.valueOf(id)})>0;
    }
}
