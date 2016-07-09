package com.plus.server.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiangwulin on 16/7/9.
 */
public class DateUtil {
    public static String toDateString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
