package com.example.lizhongli.note.event;

/**
 * Created by lizhongli on 2016/8/12.
 */
public class NoteEvent {

    private String eventFlag = "";

    private String param = "";

    public NoteEvent(String eventFlag,String param){
        this.eventFlag = eventFlag;
        this.param = param;
    }

    public String getEventFlag() {
        return eventFlag;
    }

    public void setEventFlag(String eventFlag) {
        this.eventFlag = eventFlag;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
