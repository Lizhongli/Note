package com.example.lizhongli.note.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lizhongli.note.R;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by lizhongli on 2016/8/1.
 */
public class DBHelperOrmlite extends OrmLiteSqliteOpenHelper {

    Context context;
    SQLiteDatabase db;

    public DBHelperOrmlite(Context context) {
        super(context, "note", null, 1);
        this.context = context;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        String[] tables = context.getResources().getStringArray(R.array.database_tables);
        try {
            if (tables.length > 0) {
                for (String table : tables) {
                    TableUtils.createTableIfNotExists(connectionSource, Class.forName(table));
                }
            }
        } catch (Exception e) {
            Log.e("新建表失败", e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    @SuppressWarnings("unchecked")
    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        AndroidConnectionSource connectionSource = new AndroidConnectionSource((SQLiteDatabase) db);
        @SuppressWarnings("rawtypes")
        Dao dao = DaoManager.createDao(connectionSource, clazz);
        return (D) dao;
    }


}
