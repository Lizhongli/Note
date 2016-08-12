package com.example.lizhongli.note.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lizhongli.note.R;

/**
 * Created by lizhongli on 2016/8/12.
 */
public class DbHelperSqlite extends SQLiteOpenHelper {

    Context context;

    public static SQLiteDatabase db;

    public DbHelperSqlite(Context context) {
        super(context, "note", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(db!=null){
            String sql = context.getResources().getString(R.string.db_sql);
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
