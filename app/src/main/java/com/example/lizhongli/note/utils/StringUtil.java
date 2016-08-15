/******************************************************************************
 * Copyright (C) 2015 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package com.example.lizhongli.note.utils;

import android.graphics.Paint;
import android.util.Log;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串常用工具类
 *
 * @author lizhongli
 * @since JDK1.6
 */
public class StringUtil {

    /**
     * Tag
     */
    private static final String TAG = StringUtil.class.getSimpleName();

    /**
     * 判断字符串是否全为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String getUUIDCode() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    public static String[] toArray(String target, String delim) {
        if (target == null)
            return new String[0];
        StringTokenizer st = new StringTokenizer(target, delim);
        String[] result = new String[st.countTokens()];
        int i = 0;

        while (st.hasMoreTokens()) {
            result[i] = st.nextToken();
            i++;
        }
        return result;
    }

    public static String arrayToStr(String[] arr) {
        StringBuilder sb = new StringBuilder();
        if (arr == null)
            return "";
        else {
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
                sb.append("\r\n");
            }
        }
        return sb.toString();
    }

    public static String replaceStr(String str, String oldStr, String newStr) {
        int s = 0;
        int e = 0;
        int ol = oldStr.length();
        StringBuffer result = new StringBuffer();

        while ((e = str.indexOf(oldStr, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(newStr);
            s = e + ol;
        }
        result.append(str.substring(s));
        return result.toString();
    }

    public static String convertToStr(Object obj) {
        String str = "";
        if (!isEmptyObj(obj)) {
            str = obj.toString().trim();
        }
        return str;
    }

    public static String convertDbStrToStr(Object obj) {
        String str = "";
        if (!isEmptyObj(obj)) {
            str = obj.toString().trim().toLowerCase();
        }
        if (str.contains("_")) {
            String[] tmp = str.split("_");
            int length = tmp.length;

            StringBuilder sb = new StringBuilder();
            sb.append(tmp[0]);
            for (int i = 1; i < length; i++) {
                String strtmp = tmp[i];
                sb.append(strtmp.substring(0, 1).toUpperCase() + strtmp.substring(1, strtmp.length()));
            }
            str = sb.toString();
        }
        return str;
    }

    public static String convertToStrs(Object obj) {
        String str = " ";
        if (!isEmptyObj(obj)) {
            str = obj.toString().trim();
        }
        return str;
    }

    public static String parseDateToString(Object obj, String format) {
        if (null == obj) {
            return "";
        }
        if (isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(obj);
    }

    /**
     * 可用于判断集合非空. <br/>
     * 日期: 2013-10-28 上午9:21:13 <br/>
     *
     * @param obj
     * @return
     * @author wangenzi
     * @since JDK 1.6
     */
    public static boolean isNotEmptyObj(Object obj) {
        return !isEmptyObj(obj);
    }

    /**
     * 可用于判断集合(判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty). <br/>
     * 日期: 2013-10-28 上午9:21:13 <br/>
     *
     * @param obj
     * @return
     * @author wangenzi
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmptyObj(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof String)
            return ((String) obj).trim().length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

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

    public static String convertDecToStr(Object obj, String code) {
        String str = convertToStr(obj);
        try {
            if (!str.equals(""))
                str = URLDecoder.decode(str, code);
        } catch (Exception e) {
            // SaveStackTrace(e);
        }
        return str;
    }

    public static String wrapClauses(String[] strs) {
        if (strs == null || strs.length == 0)
            return "('')";
        String rel = "(";
        StringBuilder sb = new StringBuilder();
        sb.append(rel);
        for (int i = 0; i < strs.length; i++) {
            sb.append("'" + strs[i] + "',");

        }
        rel = sb.toString();
        rel = removeEnd(rel, ",");
        return rel + ")";
    }

    public static String wrapClause(String[] strs) {
        String rel = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append("'");
            sb.append(strs[i]);
            sb.append("'");
        }
        rel = sb.toString();
        rel = removeEnd(rel, ",");
        return rel;
    }

    public static String wrapClauseList(Map<String, List<String>> map) {
        String rel = "";
        StringBuilder sb = new StringBuilder();
        if (!isEmptyObj(map)) {
            for (Iterator<Entry<String, List<String>>> iter = map.entrySet().iterator(); iter.hasNext(); ) {
                Entry<String, List<String>> entry = iter.next();
                sb.append(" " + entry.getKey() + " in " + wrapMidList(entry.getValue()) + " and");

            }
            rel = sb.toString();
            rel = removeEnd(rel, "and");
        }
        return rel;
    }

    public static String wrapClauseStringArray(Map<String, String[]> map) {
        String rel = "";
        if (!isEmptyObj(map)) {
            StringBuilder sb = new StringBuilder();
            for (Iterator<Entry<String, String[]>> iter = map.entrySet().iterator(); iter.hasNext(); ) {
                Entry<String, String[]> entry = iter.next();
                sb.append(" " + entry.getKey() + " in " + wrapClauses(entry.getValue()) + " and");
            }
            rel = sb.toString();
            rel = removeEnd(rel, "and");
        }
        return rel;
    }

    public static String wrapClauseString(Map<String, String> map) {
        String rel = "";
        StringBuilder sb = new StringBuilder();
        if (!isEmptyObj(map)) {
            for (Iterator<Entry<String, String>> iter = map.entrySet().iterator(); iter.hasNext(); ) {
                Entry<String, String> entry = iter.next();
                sb.append(" " + entry.getKey() + " = " + entry.getValue() + " and");
            }
            rel = sb.toString();
            rel = removeEnd(rel, "and");
        }
        return rel;
    }

    public static String wrapMidList(List<String> detailMidList) {
        if (detailMidList == null || !(detailMidList.size() > 0))
            return "('')";
        String imidStr = "(";
        StringBuilder sb = new StringBuilder();
        sb.append(imidStr);
        for (Iterator<String> iter = detailMidList.iterator(); iter.hasNext(); ) {
            String str = iter.next();
            if (iter.hasNext()) {
                sb.append("'");
                sb.append(str);
                sb.append("'");
            } else {
                sb.append("'");
                sb.append(str);
                sb.append("')");
            }

        }
        return sb.toString();
    }

    public static String toInsql(String[] values) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < values.length; i++) {

            sb.append("'");
            sb.append(values[i]);

            sb.append("'");
            if (i != values.length - 1) {
                sb.append(",");
            }

        }
        return sb.toString();

    }

    public static List<String> coverToList(String data) {
        List<String> list = new ArrayList<String>();
        if (data != null && !data.trim().equals("")) {
            String datas[] = data.split(",");
            int num = datas.length;
            for (int i = 0; i < num; i++)
                list.add(datas[i]);
        }
        return list;
    }

    public static String removeEnd(String str, String remove) {
        if (str.equals("") || remove.equals(""))
            return str;
        if (str.endsWith(remove))
            return str.substring(0, str.length() - remove.length());
        else
            return str;
    }

    public static String convertBig5ToUnicode(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes("Big5"), "ISO8859_1");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String convertToUnicode(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes(), "ISO8859_1");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String convertUnicodeToBig5(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes("ISO8859_1"), "Big5");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String convertUnicodeToGbk(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes("utf-8"), "GBK");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String convertUnicodeToUtf8(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes("ISO8859_1"), "utf-8");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String convertUtf8ToUnicode(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes("utf-8"), "ISO8859_1");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String convertLatin1ToUtf8(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes("Latin1"), "utf-8");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String convertToUtf8(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes(), "utf-8");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String convertBig5ToUtf8(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes("Big5"), "utf-8");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String convertUtfToBig5(Object obj) {
        try {
            return new String(convertToStr(obj).getBytes("utf-8"), "Big5");
        } catch (java.io.UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    public static String splitList(List<String> list) {
        String result = "";
        StringBuilder sb = new StringBuilder();
        if (isEmptyObj(list)) {
            for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
                //String str = iter.next();
                sb.append(iter.next());
                sb.append("##");
            }
            result = sb.toString();
            result = removeEnd(result, "##");
        }
        return result;
    }

    /** */
    public final static int SERIAL_LENGTH = 8;

    /**
     * 去除字符串左右空格包括全角和半角2种空格
     *
     * @param param 原字符串
     * @return String 去除左右2种空格后的字符串，如果原字符串为null则返回""
     */
    public static String trim2KindsSpace(String param) {
        param = param.trim();
        if (isEmpty(param)) {
            return param;
        }
        while (param.charAt(0) == '　') {
            param = param.substring(1, param.length()).trim();
        }
        while (param.endsWith("　")) {
            param = param.substring(0, param.length() - 1).trim();
        }
        return param;
    }

    /**
     * 批量替换在页面显示过程中使用到的特殊字符，将半角置换成全角来显示。
     *
     * @param value 需要检查的字符串对象。
     * @return 返回替换后的字符串对象。
     */
    public static String replaceCharacter(String value) {
        if (value == null) {
            return null;
        }
        char[] objTargetChar = new char[]{'－', '—', '(', ')', '０', '１', '２', '３', '４', '５', '６', '７', '８', '９', '？',
                '＆'};
        char[] objReplaceChar = new char[]{'-', '-', '（', '）', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '?',
                '&'};
        for (int i = 0, j = objTargetChar.length; i < j; i++) {
            value = value.replace(objTargetChar[i], objReplaceChar[i]);
        }
        return value;
    }

    /**
     * 截取字符串
     *
     * @param value
     * @param charNumber
     * @return
     */
    public static String cutString(String value, int charNumber) {
        if (charNumber < value.length()) {
            return value.substring(0, charNumber) + "...";
        } else {
            return value;
        }
    }

    /**
     * 把传进来的字符创转换成半角返回 转半角的函数(DBC case)任意字符串 半角字符串 全角空格为12288，半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     *
     * @param qjString 被装换的字符串
     * @return 返回转换后的字符创（半角的形式）
     */
    public static String qChangeToB(String qjString) {
        char[] c = qjString.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * 把传进来的字符创转换成全角形式返回 转半角的函数(DBC case)任意字符串 半角字符串 全角空格为12288，半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     *
     * @param bjString 被装换的字符串
     * @return 返回转换后的字符创（全角的形式）
     */
    public static String bChangeToQ(String bjString) {
        char[] c = bjString.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127) {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }

    /**
     * 扩展String.valueOf方法，当参数为null时返回空字符串而非"null"
     *
     * @param o
     * @return o的字符串表示，若o为null，则返回""
     */
    public static String valueOf(Object o) {
        return o == null ? "" : o.toString();
    }

    /**
     * 扩展String.valueOf方法，当参数为null时返回指定的字符串defaultValue而非"null"
     *
     * @param o
     * @return o的字符串表示，若o为null，则返回""
     */
    public static String valueOf(Object o, String defaultValue) {
        return o == null ? defaultValue : o.toString();
    }

    /** 十六进制字符数组 */
    //private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * 源字符串中可能包含终止符'\0'，将截取终止符之前的字符串返回
     *
     * @param src
     * @return
     */
    public static String cutAtStopCharater(String src) {
        int iIndex = src.indexOf('\0');
        return iIndex >= 0 ? src.substring(0, iIndex) : src;
    }

    /**
     * 将一个字符串的第一个字符转为大写
     *
     * @param s
     * @return
     */
    public static String firstLetterUpper(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    /**
     * 将是null值的字符串展示为空串
     *
     * @return
     */
    public static String nullToEmptyChar(String mayNullString) {
        if (isEmpty(mayNullString) || "0.0".equals(mayNullString)) {
            return "";
        } else {
            if (mayNullString.endsWith(".0")) {
                return mayNullString.substring(0, mayNullString.length() - 2);
            }
            if (mayNullString.endsWith(".00")) {
                return mayNullString.substring(0, mayNullString.length() - 3);
            }
        }
        return mayNullString;
    }

    /**
     * 将是0 数字展示为空串 同时将数据中已.0或.00 结尾的数据将它取整
     *
     * @param mayNullString
     * @return
     */
    public static String zeroToEmptyChar(double mayNullString) {
        if (mayNullString == 0) {
            return "";
        } else {
            String strDeleteZero = String.valueOf(mayNullString);
            if (strDeleteZero.endsWith(".0")) {
                return strDeleteZero.substring(0, strDeleteZero.length() - 2);
            }
            if (strDeleteZero.endsWith(".00")) {
                return strDeleteZero.substring(0, strDeleteZero.length() - 3);
            }
        }
        return String.valueOf(mayNullString);
    }

    /**
     * 将是0.0的字符串用空和以.0结尾的去掉.0
     *
     * @param mayZeroString
     * @return
     */
    public static String zeroSubToString(String mayZeroString) {
        if (isEmpty(mayZeroString) || "0.0".equals(mayZeroString)) {
            return "";
        }
        if (mayZeroString.endsWith(".0")) {

            return mayZeroString.substring(0, mayZeroString.length() - 2);
        }
        if (mayZeroString.endsWith(".000000")) {
            return mayZeroString.substring(0, mayZeroString.length() - 4);
        }
        return mayZeroString;
    }


    public static String getNullOrEmptyText(Object value) {
        if (value == null || value.toString().length() == 0) {
            return "无";
        }
        return value.toString();
    }

    public static List<String> distinct(List<String> values) {
        Map<String, String> tab = new HashMap<String, String>();
        List<String> result = new ArrayList<String>();
        for (String e : values) {
            if (tab.containsKey(e)) {

            } else {
                tab.put(e, e);
                result.add(e);
            }

        }
        return result;
    }

    public static boolean isBlank(String str) {
        if ((str == null) || (str.length() == 0)) {
            return true;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断包含字符串. <br/>
     * 日期: 2014-4-22 下午2:42:25 <br/>
     *
     * @param strContent
     * @param strContains
     * @return
     * @author wangenzi
     * @since JDK 1.6
     */
    public static boolean containsStr(String strContent, String strContains) {
        if (strContent == null || strContains == null) {
            return false;
        }

        return strContent.contains(strContains);
    }


    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        if (isBlank(input)) {
            return "";
        }
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        if (isBlank(str)) {
            return "";
        }
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":")
                .replaceAll("（", "(").replaceAll("）", ")");// 替换中文标号  
        String regEx = "[『』]"; // 清除掉特殊字符  
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    

	/*
     *
	 * replace TAB
	 */

    public static String replaceTABToSpace(String str) {
        str = str.replaceAll(" ", "      ");
        return str;
    }

    public static String replaceBreakLineToSpace(String str) {
        str = str.replaceAll("\n", " ");
        return str;
    }

	/*
	 * 
	 * Letters and numbers
	 */

    public static boolean isLetterOfEnglish(char c) {
        int count = (int) c;
        if (count >= 65 && count <= 90) {
            // A ~ Z
            return true;
        } else if (count >= 97 && count <= 122) {
            // a ~ z
            return true;
        } else if (count >= 48 && count <= 57) {
            // 0 ~ 9
            return true;
        }
        return false;
    }

	/*
	 * 
	 * English punctuation
	 */

    public static boolean isHalfPunctuation(char c) {
        int count = (int) c;
        if (count >= 33 && count <= 47) {
            // !~/
            return true;
        } else if (count >= 58 && count <= 64) {
            // :~@
            return true;
        } else if (count >= 91 && count <= 96) {
            // [~
            return true;
        } else if (count >= 123 && count <= 126) {
            // {~~
            return true;
        }
        return false;
    }

	/*
	 * 
	 * Chinese punctuation
	 */

    public static boolean isPunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

	/*
	 * 得到的字符串值的像素长度
	 * Get the pixel length of the string value
	 */

    public static int getWidthofString(String str, Paint paint) {
        if (str != null && str.equals("") == false && paint != null) {
            int strLength = str.length();
            int result = 0;
            float[] widths = new float[strLength];
            paint.getTextWidths(str, widths);
            for (int i = 0; i < strLength; i++) {
                result += widths[i];
            }
            return (int) result;
        }
        return 0;
    }

	/*
	 * 标点符號的左半
	 * the left half of the punctuation . For example:" ( < [ { "
	 */

    public static boolean isLeftPunctuation(char c) {
        int count = (int) c;
        if (count == 8220 || count == 12298 || count == 65288 || count == 12304
                || count == 40 || count == 60 || count == 91 || count == 123) {
            return true;
        }
        return false;
    }

	/*
	 * 标点符号右边的一半
	 * the right half of the punctuation . For example:" ) > ] } "
	 */

    public static boolean isRightPunctuation(char c) {
        int count = (int) c;
        if (count == 8221 || count == 12299 || count == 65289 || count == 12305
                || count == 41 || count == 62 || count == 93 || count == 125) {
            return true;
        }
        return false;
    }

}
