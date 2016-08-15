package com.example.lizhongli.note;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;
import android.util.Log;

import com.example.lizhongli.note.base.BaseApplication;
import com.example.lizhongli.note.dao.NoteDAO;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends InstrumentationTestCase{

    NoteDAO noteDAO;
    Context context;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = new MockContext();
        assertNotNull(context);
        noteDAO = new NoteDAO(context);
    }

    public void testAdd(){
        noteDAO.addNoteTest();
        int num = noteDAO.getCount();
        Log.e("现在的总条数为：",num+"");
    }
}