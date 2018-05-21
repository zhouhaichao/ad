package com.smyhvae.util;

import android.util.Log;


public class Logcat {
    public static void show(String str) {
        if(str!=null)
        {
            str = str.trim();
            int index = 0;
            int maxLength = 4000;
            String sub;
            while (index < str.length()) {
                // java的字符不允许指定超过总的长度end
                if (str.length() <= index + maxLength) {
                    sub = str.substring(index);
                } else {
                    sub = str.substring(index, maxLength + index);
                }

                index += maxLength;
                Log.i("打印信息", sub.trim());
            }
        }
    }
}
