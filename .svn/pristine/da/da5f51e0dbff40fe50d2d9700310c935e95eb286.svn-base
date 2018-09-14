package com.smyhvae.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/28.
 */

public class BigDecimalUtil {
    public String getStringUtil(String price){
        Double str = Double.valueOf(price);
        String s1 = str.toString();
        String regex = "^(\\d+)(\\.0)$";
        String s = s1.replaceAll(regex,"$1");
        return s;
    }

    public String getDate(Date date){
        String time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        time = sdf.format(date);
        return time;
    }
}
