package com.example.lizhongli.note.dao;

import android.content.Context;
import android.util.Log;

import com.example.lizhongli.note.base.BaseDao;
import com.example.lizhongli.note.base.DBHelperOrmlite;
import com.example.lizhongli.note.model.NoteVO;
import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lizhongli on 2016/8/15.
 */
public class NoteOrmliteDAO extends BaseDao<NoteVO> {

    public NoteOrmliteDAO(Context context) {
        super(NoteVO.class);
        dbHelper = new DBHelperOrmlite(context);
    }

    public void addNoteTest(){
        try{
            List<NoteVO> lstNote = new ArrayList<NoteVO>();
            for(int i=0;i<10;i++){
                NoteVO noteVO = new NoteVO();
                String id = UUID.randomUUID().toString().replace("-","");
                noteVO.setNoteId(id);
                String title = "这是标题栏内容_"+i;
                noteVO.setNoteTitle(title);
                String content = "这是内容"+i+",很长很长很长很长";
                noteVO.setNoteContext(content);
                Date date = new Date();
                long createTime = date.getTime();
                noteVO.setCreateTime(createTime);
                noteVO.setUpdateTime(createTime);
                lstNote.add(noteVO);
            }
            batchInsert(lstNote);
        }catch (Exception e){
            Log.e("插入测试数据失败",e.getMessage());
        }
    }


}
