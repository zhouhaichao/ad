package com.smyhvae.util;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientThread implements Runnable{
    private Socket s;
    private String printStr;
    //public Handler revHandler  // 创建revHandler对象
    public Handler revHandler /*= new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x345) {
                try {
                   *//* s = new Socket(ip, 8888);
                    os = s.getOutputStream();*//*
                    // 为当前线程初始化Looper

                    Logcat.show(msg.obj.toString());
                    os.write((msg.obj.toString() + "\n").getBytes("utf-8"));
                } catch (SocketTimeoutException e) {
                    e.printStackTrace();

                } catch (IOException io) {
                    io.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }*/;
    // 启动Looper;
    OutputStream os = null;
    String ip;

    public  ClientThread(String ip,String  printStr){
        super();
        this.ip = ip;
        this.printStr=printStr;
        Log.d("sfn", ip);
    }
    @Override
    public void run() {
        try {
            /*
            * wifi端口  9100
            * 蓝牙 针式打印 端口 8888
            * */
            s = new Socket(ip, 8888);
            os = s.getOutputStream();

            os.write((printStr + "\n").getBytes("utf-8"));
            // 为当前线程初始化Looper
            /*Looper.prepare();

            revHandler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x345) {
                        try {
                   *//* s = new Socket(ip, 8888);
                    os = s.getOutputStream();*//*
                            // 为当前线程初始化Looper

                            Logcat.show(msg.obj.toString());
                            os.write((msg.obj.toString() + "\n").getBytes("utf-8"));
                        } catch (SocketTimeoutException e) {
                            e.printStackTrace();

                        } catch (IOException io) {
                            io.printStackTrace();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            };
            Looper.loop();*/

        } catch (SocketTimeoutException e) {
            e.printStackTrace();

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
