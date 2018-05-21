package com.smyhvae.service;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.smyhvae.model.FuAccountModel;
import com.smyhvae.model.FuBaseModel;
import com.smyhvae.model.FuExtraMoneyFixModel;
import com.smyhvae.model.FuMoneyModel;
import com.smyhvae.model.FuSalesBillModel;
import com.smyhvae.model.FuStaffModel;
import com.smyhvae.myapplication.MyApplication;
import com.smyhvae.myapplication.SelectSalesBillActivity;
import com.smyhvae.util.ClientThread;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DatabaseHelper;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;
import com.smyhvae.util.Print;
import com.smyhvae.util.PrintBillUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Socket;

/**
 * Created by Administrator on 2018/3/30.
 */

public class PrintService extends Service {
    private MyApplication application;
    private int accountid;
    private int loginstaffid;
    private int logininvid;
    private String webServerUrl;
    private Integer staffid;
    private String accessKey;
    private FuAccountModel accountModel;
    private FuStaffModel staffModel;
    private int numberber = 0;

    private String isPrinterType80mm;


    private InputStream InStream = null;
    private OutputStream OutStream = null;
    private Socket socket = null;

    private DataUtil dataUtil;

    FuSalesBillModel fuSalesBillModel;
    private BigDecimal totlele = new BigDecimal(0);
    ClientThread clientThread;
    String result;
    String isWIFIPrinter;
    DatabaseHelper db;
    Cursor cursor;
    String ip="";

    /*public  PrintService(String result)
    {
        this.result=result;
    }*/

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = (MyApplication) getApplication().getApplicationContext();
        accessKey = application.getAccessKey();
        accountid = application.getFuStaffModel().getAccountid();
        loginstaffid = application.getFuStaffModel().getId();
        logininvid = application.getFuStaffModel().getFuInventoryList().get(0).getId();
        webServerUrl = application.getWebServerUrl();
        accountModel = application.getFuAccountModel();
        staffModel = application.getFuStaffModel();

        dataUtil = new DataUtil();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TestLog","PrintService--onStartCommand");
        result = intent.getStringExtra("data");
        numberber = intent.getExtras().getInt("numberber");
        totlele = (BigDecimal) intent.getExtras().get("totlele");
        fuSalesBillModel = dataUtil.getSalesBillData(result);
        staffid = fuSalesBillModel.getStaffid() == null ? loginstaffid : fuSalesBillModel.getStaffid();

        db =  new DatabaseHelper(this);
        String sql = "select * from fu_parameter ";
        cursor = db.queryParameter(sql, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            if (name.equals("printerAddress")){
                ip = cursor.getString(cursor.getColumnIndex("val"));
            }
            if (name.equals("isWIFIPrinter")){
                isWIFIPrinter = cursor.getString(cursor.getColumnIndex("val"));
            }
            if(name.equals("printerType80mm"))
            {
                isPrinterType80mm = cursor.getString(cursor.getColumnIndex("val"));
            }
        }
        /*if(isWIFIPrinter.equals("0")){
            clientThread = new ClientThread(ip);
            new Thread(clientThread).start();
        }else if (isWIFIPrinter.equals("1")){
//            if(! isStrictMode){
//                StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder()).detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
//                StrictMode.setVmPolicy((new android.os.StrictMode.VmPolicy.Builder()).detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
//                isStrictMode = true;
//            }
        }*/

        Toast.makeText(getApplicationContext(), "正在打印，请等待...", Toast.LENGTH_SHORT).show();
        try {
            String qrcodewx = "";
            BaseService baseService = new BaseService();
            String staffForQRCODE = baseService.doSelectData(webServerUrl, "staff", "selectStaff", loginstaffid, logininvid, accessKey, accountid, staffid);
            Logcat.show(staffForQRCODE);
            DataUtil dataUtil = new DataUtil();
            FuBaseModel baseModel = dataUtil.message(staffForQRCODE);
            if(baseModel.getResultCode().intValue() ==1){
                FuStaffModel staffModel = dataUtil.getStaffDataForQRCODE(staffForQRCODE);
                qrcodewx = staffModel.getQrcodewx();
            }else if(baseModel.getResultCode().intValue() ==0){
                DialogUtil.showDialog(this, baseModel.getMessage(), false);
            }
            final String finalQrcodewx = qrcodewx;
            // 然后发送给子线程Handler
            if (isWIFIPrinter.equals("0")){
//                                    Thread.sleep(500);
                /*Message msg = new Message();
                msg.what = 0x345;*/
                String printStr = new Print().doPrint(this, result, accountModel, numberber, staffModel, totlele, finalQrcodewx);
                /*if(clientThread==null)
                    Log.i("TestLog","clientThread为空");
                if(clientThread.revHandler==null)
                    Log.i("TestLog","clientThread.revHandler为空");
                if(msg==null)
                    Log.i("TestLog","msg为空");*/

                clientThread = new ClientThread(ip,printStr);
                new Thread(clientThread).start();

                //clientThread.revHandler.sendMessage(msg);
            }else if (isWIFIPrinter.equals("1")) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            socket = new Socket(ip, 9100);
                            InStream = socket.getInputStream();
                            OutStream = socket.getOutputStream();
                            /**/
                                PrintBillUtil printBillUtil = new PrintBillUtil(socket, OutStream, InStream,PrintService.this);
                                final String printResult = new Print().doPrint(PrintService.this, result, accountModel, numberber, staffModel, totlele, finalQrcodewx);

                                if("1".equals(isPrinterType80mm))
                                {
                                    printBillUtil.printeForPT380(printResult);
                                }
                                else
                                {
                                    if (accountModel.getColorsizemode().intValue() == 0) {
                                        /*Message msg = mhandler.obtainMessage();
                                        msg.what = 0x346;
                                        msg.obj = "颜色尺码模式不支持该打印..";
                                        mhandler.sendMessage(msg);*/

                                        printBillUtil.prePrint(printResult);
                                    } else if (accountModel.getColorsizemode().intValue() == 1) {
                                        printBillUtil.prePrint(printResult);
                                    }
                                }

                        } catch (Exception var4) {
                            var4.printStackTrace();
                            Message msg = mhandler.obtainMessage();
                            msg.what = 0x346;
                            msg.obj = "打印连接错误！";
                            mhandler.sendMessage(msg);
                        }
                    }
                }.start();
            }
        }catch (Exception e){
            e.printStackTrace();
            //DialogUtil.showDialog(getApplicationContext(), "打印连接错误！", false);
            //                                throw e;
            Message msg = mhandler.obtainMessage();
            msg.what = 0x346;
            msg.obj = "打印连接错误！";
            mhandler.sendMessage(msg);
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private Handler mhandler  = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x346) {
                Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
                //DialogUtil.showDialog(getApplicationContext(), msg.obj.toString(), false);
            }
        }
    };

    @Override
    public void onDestroy() {
        if (cursor != null){
            cursor.close();
            db.close();
        }
        super.onDestroy();
        Log.i("TestLog","PrintService的onDestroy");
    }
}
