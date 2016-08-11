package com.example.lizhongli.note;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.lizhongli.note.fragment.Fragment1;
import com.example.lizhongli.note.fragment.Fragment2;
import com.example.lizhongli.note.fragment.Fragment3;
import com.example.lizhongli.note.fragment.Fragment4;

public class MainActivity extends Activity implements View.OnClickListener{

    RelativeLayout tab1;
    RelativeLayout tab2;
    RelativeLayout tab3;
    RelativeLayout tab4;

    Fragment1 f1;
    Fragment2 f2;
    Fragment3 f3;
    Fragment4 f4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment(f1);
    }

    public void initView(){
        tab1 = (RelativeLayout) findViewById(R.id.rl_tab1);
        tab1.setOnClickListener(this);
        tab2 = (RelativeLayout) findViewById(R.id.rl_tab2);
        tab2.setOnClickListener(this);
        tab3 = (RelativeLayout) findViewById(R.id.rl_tab3);
        tab3.setOnClickListener(this);
        tab4 = (RelativeLayout) findViewById(R.id.rl_tab4);
        tab4.setOnClickListener(this);
        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();
        f4 = new Fragment4();
    }

    public void initFragment(Fragment fragment){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_content,fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_tab1:
                initFragment(f1);
                break;
            case R.id.rl_tab2:
                initFragment(f2);
                break;
            case R.id.rl_tab3:
                initFragment(f3);
                break;
            case R.id.rl_tab4:
                initFragment(f4);
                break;
        }
    }
}
