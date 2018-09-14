package com.smyhvae.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10.
 * 为PT380打印机准备的每行数据封装工具
 */

public class OneLineArray {

    public  int font;

    byte [] returnArray;

    public OneLineArray(int font){
        this.font =font;
        if(font==2)
        {
            returnArray = new byte[24];
        }
        else
        {
            returnArray = new byte[48];
        }

        for(int i =0;i<returnArray.length;i++)
        {
            returnArray[i]=0X20;
        }

    }

    // dataArray 数据，isLeftAlign代表往左还是往右 position代表开始位置
    public OneLineArray putDataToArray(byte[] dataArray, boolean isLeftAlign,int position) {

        if(dataArray!=null)
        {
            if(font==2)//每行可有24个字节
            {
                //if()//右对齐 并且保证数组不越界
            }
            else//每行可有48个字节
            {
                if(isLeftAlign)//左对齐 并且保证数组不越界
                {
                    for(int i =0;i<dataArray.length;i++)
                    {
                        if(position+i<48)//保证数组不越界
                        {
                            returnArray[position+i] = dataArray[i];
                        }
                    }
                }
                if(!isLeftAlign&&position-dataArray.length>=0)//右对齐 并且保证数组不越界
                {
                    int j =0;
                    for(int i = dataArray.length-1;i>=0;i--,j++)
                    {
                        if(position-j>=0)//保证数组不越界
                        {
                            returnArray[position-j] = dataArray[i];
                        }
                    }
                }
            }
        }


        return this;
    }
    //清空上次数组数据
    public OneLineArray clearArray(){
        for(int i =0;i<returnArray.length;i++)
        {
            returnArray[i]=0X20;
        }
        return this;
    }

    //设置下划线
    public OneLineArray setDivide() {
        for(int i =0;i<returnArray.length;i++)
        {
            returnArray[i]=45;
        }
        return this;
    }
    //设置双下划线
    public OneLineArray setDoubleDivide() {
        for(int i =0;i<returnArray.length;i++)
        {
            returnArray[i]=61;
        }
        return this;
    }

    public byte[] getReturnArray()
    {
        return returnArray;
    }



}
