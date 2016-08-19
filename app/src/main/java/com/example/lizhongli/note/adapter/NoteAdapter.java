package com.example.lizhongli.note.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lizhongli.note.model.NoteVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongli on 2016/8/15.
 */
public class NoteAdapter extends BaseAdapter {

    List<NoteVO> lstNoteVO = new ArrayList<NoteVO>();

    @Override
    public int getCount() {
        return lstNoteVO.size();
    }

    @Override
    public Object getItem(int position) {
        return lstNoteVO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public class HoldView{

    }
}
