package com.example.lizhongli.note.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lizhongli.note.R;
import com.example.lizhongli.note.base.BaseFragment;

import java.util.List;


public class Fragment1 extends BaseFragment {

    ListView lv_note ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        return view;
    }

    public void initView(View view){
        lv_note = (ListView) view.findViewById(R.id.lv_note);

    }







}
