package com.example.lizhongli.note.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lizhongli.note.base.DbHelper;
import com.example.lizhongli.note.model.NoteVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongli on 2016/8/12.
 */
public class NoteDAO{

    SQLiteDatabase db ;
    DbHelper dbHelper;
    Context context;

    public NoteDAO(Context context){
        this.context = context;
        dbHelper = new DbHelper(context);
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
        String[] value1 = new String[]{};

    }


}
