package com.example.day4.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDao {
    private MyOpenHelper helper;
    private SQLiteDatabase mData;
    private Context mContext;

    public MyDao(Context context) {
        mContext = context;
        helper = new MyOpenHelper(context);
        mData = helper.getWritableDatabase();
    }


    public void insertSqlite(String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        mData.insert("liu", null, contentValues);
        Toast.makeText(mContext, "插入成功", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> selectName() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = mData.query("liu", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            list.add(name);
        }
        return list;
    }

    public void delete() {
        mData.execSQL("delete from liu");
    }

}
