package com.example.lizhongli.note.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by lizhongli on 2016/8/1.
 */
@DatabaseTable(tableName = "TAB_NOTE")
public class NoteVO extends BaseVO{

    @DatabaseField(columnName = "NOTE_ID", canBeNull = false, id = true)
    private String noteId;
    @DatabaseField(columnName = "NOTE_TITLE")
    private String noteTitle;
    @DatabaseField(columnName = "CREATE_TIME")
    private long createTime;
    @DatabaseField(columnName = "UPDATE_TIME")
    private long updateTime;
    @DatabaseField(columnName = "NOTE_CONTEXT")
    private String noteContext;

    public NoteVO() {
        noteId = "";
        noteTitle = "";
        noteContext = "";
        createTime = 0;
        updateTime = 0;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getNoteContext() {
        return noteContext;
    }

    public void setNoteContext(String noteContext) {
        this.noteContext = noteContext;
    }
}
