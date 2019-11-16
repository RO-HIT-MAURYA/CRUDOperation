package com.example.crudoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Functions {
    String name,email,cname;

    private DBHelper dbHelper;
    //constructor to create object instantly
    public Functions(Context context){
        dbHelper = new DBHelper(context);
    }

    public long insertdata(String name, String email, String cname){
        this.name = name;
        this.email = email;
        this.cname = cname;

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.COL_NAME, name);
        values.put(Constants.COL_EMAIL, email);
        values.put(Constants.COL_CNAME, cname);
        //insert into table using built in method 'insert' in SQLitedatabase.class
        long retid = db.insert(Constants.TABLE, null, values);
        return retid;
    }

    public String viewdata(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String [] columns = {Constants.COL_ID,Constants.COL_NAME, Constants.COL_EMAIL, Constants.COL_CNAME};
        Cursor cursor = db.query(Constants.TABLE,columns,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(Constants.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(Constants.COL_NAME));
            String email = cursor.getString(cursor.getColumnIndex(Constants.COL_EMAIL));
            String cname = cursor.getString(cursor.getColumnIndex(Constants.COL_CNAME));
            buffer.append(cid+"  "+name+"  "+email+"  "+cname+"\n");
        }
        return buffer.toString();
    }

    public long edit(String id, String name){
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.COL_NAME, name);

        String [] whereArgs = {id};
        long retid = db.update(Constants.TABLE,values,Constants.COL_ID+" = ?",whereArgs);
        return retid;
    }

    public int delete(String id){
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs ={id};
        int retid = db.delete(Constants.TABLE,Constants.COL_ID + " = ?",whereArgs);
        return retid;
    }
}
