package com.example.lizhongli.note.extview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lizhongli.note.R;

/**
 * Created by lizhongli on 2016/8/29.
 */
public class HeadView extends RelativeLayout {

    RelativeLayout relativeLayout;
    ImageView img_left;
    ImageView img_right;
    TextView tv_center;

    private final int TYPE_ONLY_LEFT = 1;
    private final int TYPE_ONLY_RIGHT = 2;
    private final int TYPE_ALL = 3;

    public HeadView(Context context) {
        super(context);
        relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.head,null);
        initView();
    }

    public void initView(){
        img_left = (ImageView) relativeLayout.findViewById(R.id.img_left);
        img_right = (ImageView) relativeLayout.findViewById(R.id.img_right);
        tv_center = (TextView) relativeLayout.findViewById(R.id.tv_center);
    }

    public void setTitle(String title){
        tv_center.setText(title);
    }

    public void setHeadParam(int type){
        switch (type){
            case TYPE_ALL:
                img_left.setVisibility(View.VISIBLE);
                img_right.setVisibility(View.VISIBLE);
                break;
            case TYPE_ONLY_LEFT:
                img_left.setVisibility(View.VISIBLE);
                img_right.setVisibility(View.GONE);
                break;
            case TYPE_ONLY_RIGHT:
                img_left.setVisibility(View.GONE);
                img_right.setVisibility(View.VISIBLE);
                break;
        }
    }
}
