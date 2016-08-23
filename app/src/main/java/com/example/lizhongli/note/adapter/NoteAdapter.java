package com.example.lizhongli.note.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lizhongli.note.R;
import com.example.lizhongli.note.model.NoteVO;
import com.example.lizhongli.note.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongli on 2016/8/15.
 */
public class NoteAdapter extends BaseAdapter {



    List<NoteVO> lstNoteVO = new ArrayList<NoteVO>();

    Context context;

    public NoteAdapter(Context context){
        this.context = context;
    }

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

    public void setData(List<NoteVO> lstNoteVO){
        this.lstNoteVO = lstNoteVO;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_note,null);
            holdView = new HoldView();
            holdView.tv_title = (TextView) convertView.findViewById(R.id.tv_note_title);
            holdView.tv_time = (TextView) convertView.findViewById(R.id.tv_note_time);
            holdView.tv_content = (TextView) convertView.findViewById(R.id.tv_note_content);
            convertView.setTag(holdView);
        }else{
            holdView = (HoldView) convertView.getTag();
        }
        NoteVO noteVO = (NoteVO) getItem(position);
        holdView.tv_title.setText(noteVO.getNoteTitle());
        holdView.tv_content.setText(noteVO.getNoteContext());
        holdView.tv_time.setText(StringUtil.getStringDate(noteVO.getCreateTime()));
        return convertView;
    }

    public class HoldView{
        TextView tv_title;
        TextView tv_time;
        TextView tv_content;
    }
}
