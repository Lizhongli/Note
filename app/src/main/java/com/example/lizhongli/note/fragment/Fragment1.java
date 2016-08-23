package com.example.lizhongli.note.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lizhongli.note.R;
import com.example.lizhongli.note.adapter.NoteAdapter;
import com.example.lizhongli.note.base.BaseFragment;
import com.example.lizhongli.note.dao.NoteDAO;
import com.example.lizhongli.note.dao.NoteOrmliteDAO;
import com.example.lizhongli.note.model.NoteVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Fragment1 extends BaseFragment implements View.OnClickListener {

    ListView lv_note;
    NoteAdapter adapter;
    List<NoteVO> lstVO = new ArrayList<NoteVO>();
    Button btn_addTest;
    Button btn_readTest;
    NoteOrmliteDAO noteDAO;
    TextView tv_num;
    Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        lv_note = (ListView) view.findViewById(R.id.lv_note);
        adapter = new NoteAdapter(getActivity());
        lv_note.setAdapter(adapter);
        btn_addTest = (Button) view.findViewById(R.id.btn_addTest);
        btn_addTest.setOnClickListener(this);
        btn_readTest = (Button) view.findViewById(R.id.btn_readTest);
        btn_readTest.setOnClickListener(this);
        tv_num = (TextView) view.findViewById(R.id.tv_num);
        noteDAO = new NoteOrmliteDAO(getActivity());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addTest:
                addTest();
                break;
            case R.id.btn_readTest:
                getTestNum();
                break;
        }
    }

    public void addTest() {
        new Thread() {
            @Override
            public void run() {
                noteDAO.addNoteTest();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_num.setText("测试数据插入完成");
                    }
                });
            }
        }.start();
    }

    public void getTestNum() {
        new Thread() {
            @Override
            public void run() {
                try {
                    lstVO.clear();
                    lstVO = noteDAO.queryForAll();
                    if (lstVO == null) {
                        lstVO = new ArrayList<NoteVO>();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tv_num.setText("数据总数为:" + String.valueOf(lstVO.size()));
                            adapter.setData(lstVO);
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


}
