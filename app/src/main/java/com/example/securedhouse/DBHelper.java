package com.example.securedhouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME="stud.db";
    public static final String TABLE_NAME="student";
    public static final String COL_1="Phone Number";
    public static final String COL_2="FIRST_NAME";
    public static final String COL_3="LAST_NAME";
    public static final String COL_4="Location";

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT,LAST_NAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String fn, String ln, String mks)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,fn);
        contentValues.put(COL_3,ln);
        contentValues.put(COL_4,mks);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }

    public boolean updateData(String id, String fn, String ln, String mks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,fn);
        contentValues.put(COL_3,ln);
        contentValues.put(COL_4,mks);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from " +TABLE_NAME,null);
        return cursor;
    }


}
