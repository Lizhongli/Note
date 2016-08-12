package com.example.lizhongli.note.base;

import android.app.Activity;

import de.greenrobot.event.EventBus;

/**
 * Created by lizhongli on 2016/8/12.
 */
public class BaseActivity extends Activity {

    public void registerEvent() {
        EventBus.getDefault().register(this);
    }

    public void unRegisterEnevt() {
        EventBus.getDefault().unregister(this);
    }

    public void OnEvent() {

    }
}
