package com.leeves.h.geekbank1;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.leeves.h.geekbank1.database.DatabaseHelper;

/**
 * Function：
 * Created by h on 2016/8/16.
 *
 * @author Leeves
 */
public class DatabaseButtonActivity extends AppCompatActivity {

    private SQLiteDatabase mSqLiteDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        mSqLiteDatabase = databaseHelper.getReadableDatabase();

        //insert
        findViewById(R.id.database_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IO操作 建议后台操作
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.USERNAME,"极客班");
                contentValues.put(DatabaseHelper.AGE,"8888");

                long rowNumber = mSqLiteDatabase.insert(DatabaseHelper.USER_TABLE_NAME,null,contentValues);
                if (rowNumber != -1) {
                    Toast.makeText(DatabaseButtonActivity.this,"插入成功",Toast.LENGTH_LONG).show();
                }
                // query :
                Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.USER_TABLE_NAME,null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    int count = cursor.getCount();
                    for (int i = 0; i < count; i++) {
                        String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USERNAME));
                        String age = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.AGE));
                        Log.i(MainActivity.class.getSimpleName(),i+":"+username+"."+age);
                    }
                }
            }
        });

        findViewById(R.id.database_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete:
                String whereClauseString = "username = ?"; //and age<?
                String[] wherArgs = {"极客班"};
                mSqLiteDatabase.delete(DatabaseHelper.USER_TABLE_NAME,whereClauseString,wherArgs);
            }
        });

        findViewById(R.id.database_modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.AGE,"100");
                String whereClauseString = "username = ?"; //and age<?
                String[] wherArgs = {"极客班"};
                mSqLiteDatabase.update(DatabaseHelper.USER_TABLE_NAME,contentValues,whereClauseString,wherArgs);
            }
        });

        mSqLiteDatabase.execSQL("insert into user(username, age) valus ('小x8','999')");

        //开始事务，此时数据库会被锁定
        mSqLiteDatabase.beginTransaction();
        try {
            //做操作
            mSqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭事务
            mSqLiteDatabase.endTransaction();
        }

        ContentResolver contentResolver = getContentResolver();

        Uri uri = Uri.parse("content://com.android.contacts/contacts");
//        Uri uri = Uri.parse("content://com.leeves.h.geekbank1/table_name/10/user"); 表示id为10数据中 user的数据
//        Uri uri = Uri.parse("content://package_name/table_name/id/row_name"); 表示id为10数据中 user的数据
        Cursor cursor = contentResolver.query(uri,null,null,null,null);

        if (cursor.moveToFirst() && cursor != null){

        }

    }
}
