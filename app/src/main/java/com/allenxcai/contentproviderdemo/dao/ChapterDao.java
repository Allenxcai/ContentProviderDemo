package com.allenxcai.contentproviderdemo.dao;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * @Author:AXC 时间：2018/10/29
 * 项目名：ContentProviderDemo
 * 包名：com.allenxcai.contentproviderdemo.dao
 * 简述：
 */
public class ChapterDao {
    private Context mContext;
    private ContentResolver mResolver;
    private Uri             mUri;

    public ChapterDao(Context context, String uriStr) {
        mUri = Uri.parse(uriStr);
        mResolver=context.getContentResolver();
    }


    public int delete(String selection, String[] selectionArgs) {
        int count = mResolver.delete(mUri, selection,selectionArgs);
        return count;
    }

    public Uri insert(ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
         Uri uri =mResolver.insert(mUri,values);
        return uri;

    }

    public Cursor query(String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor c = mResolver.query(mUri, projection, selection, selectionArgs, sortOrder);

        return c;
    }


}
