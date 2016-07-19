package com.plus.server.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

/**
 * Created by jiangwulin on 16/7/9.
 */
public class DateUtil {
    public static String toDateString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date toDate(String dateString){
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        }
        catch (Exception e){
            return new Date();
        }
    }
}
