package com.example.lizhongli.note.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class StringUtil {


    public static boolean isEmpty(String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmptyObj(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            boolean empty = true;
            for (int i = 0; i < object.length; i++)
                if (!isEmptyObj(object[i])) {
                    empty = false;
                    break;
                }
            return empty;
        }
        return false;
    }

    public static String getStringDate(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        date.setTime(time);
        return dateFormat.format(date);
    }

}
