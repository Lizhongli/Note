package com.example.lizhongli.note.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lizhongli.note.R;

import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    public void registerEvent(){
        EventBus.getDefault().register(getActivity());
    }

    public void unRegisterEnevt(){
        EventBus.getDefault().unregister(getActivity());
    }

    public void OnEvent(){

    }

}
