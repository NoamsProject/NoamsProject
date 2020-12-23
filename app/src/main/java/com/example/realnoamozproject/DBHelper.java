package com.example.realnoamozproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.core.app.NavUtils;

import java.util.jar.Attributes;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "all_students";
    public static final String NAME = "Name";
    public static final String PASS = "Pass";
    public static final String GRADE = "Grade";
    public static final String GRADE_NUMBER = "Grade_Number";

    String SQL_Create="";
    String SQL_Delete="";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DBHelper", "DBHelper constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        Log.d("DBHelper", "onCreate");
        //SQL_Create="CREATE TABLE all_users (Nickname TEXT, Pass TEXT, Email TEXT, Phone TEXT);"
        SQL_Create="CREATE TABLE "+TABLE_NAME+" (";
        SQL_Create+= NAME +" TEXT, ";
        SQL_Create+= PASS+" TEXT, ";
        SQL_Create+= GRADE+" TEXT, ";
        SQL_Create+= GRADE_NUMBER+" TEXT);";
        sqLiteDatabase.execSQL(SQL_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        SQL_Delete = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_Delete);
        onCreate(sqLiteDatabase);
    }

}
