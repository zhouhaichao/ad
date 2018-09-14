package com.smyhvae.bleprint.service;

/**
 * 蓝牙打印服务
 * Created by Administrator on 2018/8/12.
 */

public interface IBlePrintServer<T> {

    /**
     * 小票打印接口
     */
    public void receiptPrint(T date);

}
