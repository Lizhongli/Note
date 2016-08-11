package com.example.lizhongli.note.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by lizhongli on 2016/8/2.
 */
public class BaseApplication extends Application {

    static DBHelper dbHelper = null;
    static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }

    public static synchronized DBHelper getDBHelper(){
        if(null == dbHelper){
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }


}
