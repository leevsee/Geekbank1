package com.leeves.h.geekbank1.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.leeves.h.geekbank1.database.DatabaseHelper;

/**
 * Function：
 * Created by h on 2016/8/18.
 *
 * @author Leeves
 */
public class TestContentProvider extends ContentProvider {

    private static UriMatcher sUriMatcher;
    public static final int URI_MATCH_USER = 1;
    public static final int URI_MATCH_BOOK = 2;

    static{
        //设定初始值 -1
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //添加URI到UriMatcher，并设置值
        //content://com.leeves.geekband1/user   1
        //content://com.leeves.geekband1/book   2
        sUriMatcher.addURI(URIList.AUTHORITY,DatabaseHelper.USER_TABLE_NAME,URI_MATCH_USER);
        sUriMatcher.addURI(URIList.AUTHORITY,DatabaseHelper.BOOK_TABLE_NAME,URI_MATCH_BOOK);
    }

    private DatabaseHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        //创建Database
        mDatabaseHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {

            return null;
        }
        Cursor cursor = mDatabaseHelper.getReadableDatabase().query(tableName,projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return null;
        }
        //the row ID of the newly inserted row, or -1 if an error occurred，返回最新那一行的id
        long id = mDatabaseHelper.getWritableDatabase().insert(tableName,null,values);
        //返回Uri，content://com.leeves.geekband1/user/id    加了 /id
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return -1;
        }
        //返回删除影响的行数
        int count = mDatabaseHelper.getWritableDatabase().delete(tableName,selection,selectionArgs);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)){
            return -1;
        }
        int count = mDatabaseHelper.getWritableDatabase().update(tableName,values,selection,selectionArgs);
        return count;
    }

    //识别传入uri，获得tableName，此方法给query、insert、delete使用
    private String getTableName(Uri uri){
        //从传入的uri中，获得UriMatcher值
        int type = sUriMatcher.match(uri);
        String tableName = null;
        switch (type){
            case URI_MATCH_USER :
                tableName = DatabaseHelper.USER_TABLE_NAME;
                break;
            case URI_MATCH_BOOK:
                tableName = DatabaseHelper.BOOK_TABLE_NAME;
                break;
        }
        return tableName;
    }
}
