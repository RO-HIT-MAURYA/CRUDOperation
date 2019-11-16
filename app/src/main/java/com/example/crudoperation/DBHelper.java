package com.example.crudoperation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Glad";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //All necessary tables you like to create will create here
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE + "("
                + Constants.COL_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constants.COL_NAME + " VARCHAR(255),"
                + Constants.COL_EMAIL + " VARCHAR(255),"
                + Constants.COL_CNAME + " VARCHAR(255));";
        //Execute query to create table
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed, all data will be gone!!!
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE);
        // Create tables again
        onCreate(sqLiteDatabase);
    }
}
