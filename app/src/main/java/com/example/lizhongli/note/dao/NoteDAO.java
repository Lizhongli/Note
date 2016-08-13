package com.example.lizhongli.note.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lizhongli.note.base.DbHelperSqlite;
import com.example.lizhongli.note.model.NoteVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lizhongli on 2016/8/12.
 */
public class NoteDAO{

    SQLiteDatabase db ;
    DbHelperSqlite dbHelper;
    Context context;

    public NoteDAO(Context context){
        this.context = context;
        dbHelper = new DbHelperSqlite(context);
    }

    public List<NoteVO> getNoteList(){
        List<NoteVO> lstNote = new ArrayList<NoteVO>();
        if(db == null){
            db = dbHelper.getWritableDatabase();
        }
        String sql = "SELECT * FROM TAB_NOTE";
        Cursor cur = db.rawQuery(sql,null);
        if(null !=  cur){
            while (cur.moveToNext()){
                NoteVO noteVO = new NoteVO();
                noteVO.setNoteId(cur.getString(cur.getColumnIndex("ID")));
                noteVO.setNoteTitle(cur.getString(cur.getColumnIndex("TITLE")));
                noteVO.setNoteContext(cur.getString(cur.getColumnIndex("CONTENT")));
                String strCreateTime = cur.getString(cur.getColumnIndex("CREATE_TIME"));
                noteVO.setCreateTime(Long.parseLong(strCreateTime));
                String strUpdateTime = cur.getString(cur.getColumnIndex("UPDATE_TIME"));
                noteVO.setCreateTime(Long.parseLong(strUpdateTime));
                lstNote.add(noteVO);
            }
        }
        return lstNote;
    }

    public void addNoteTest(){
        if(db == null){
            db = dbHelper.getWritableDatabase();
        }
        String sql = "INSERT INTO TAB_NOTE(ID,TITLE,CONTENT,CREATE_TIME,UPDATE_TIME) VALUES(?,?,?,?,?)";
        try{
            for(int i=0;i<10;i++){
                String id = UUID.randomUUID().toString().replace("-","");
                String title = "这是标题栏内容_"+i;
                String content = "这是内容"+i+",很长很长很长很长";
                Date date = new Date();
                long createTime = date.getTime();
                Object[] param = new Object[]{id,title,content,createTime,createTime};
                db.execSQL(sql,param);
            }
        }catch (Exception e){
            Log.e("插入测试数据失败",e.getMessage());
        }
    }

    public int getCount(){
        int num = 0;
        if(db == null){
            db = dbHelper.getWritableDatabase();
        }
        try {
            String sql = "SELECT * FROM TAB_NOTE";
            Cursor cur = db.rawQuery(sql,null);
            if(null != cur){
                num = cur.getCount();
            }
        }catch (Exception e){
            Log.e("数据查询失败",e.getMessage());
        }

        return num;
    }


}
