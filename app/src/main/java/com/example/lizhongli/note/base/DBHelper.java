package com.example.lizhongli.note.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lizhongli.note.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by lizhongli on 2016/8/1.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    Context context;

    public DBHelper(Context context) {
        super(context, "note", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        String[] tables = context.getResources().getStringArray(R.array.tables);
        try{
            if(tables.length>0){
                for(String table : tables){
                    TableUtils.createTableIfNotExists(connectionSource,Class.forName(table));
                }
            }
        }catch (Exception e){
            Log.e("新建表失败",e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
