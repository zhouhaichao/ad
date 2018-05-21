package com.smyhvae.util;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.smyhvae.myapplication.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintBillUtil {
    BigDecimalUtil bigDecimalUtil = new BigDecimalUtil();
    private OutputStream OutStream = null;
    private InputStream InStream = null;
    private MyApplication myApplication;
    private Socket socket = null;
    private byte[] readBuf = new byte[1024];
    private String printerstatus = "";
    private DatabaseHelper db;
    private Cursor cursor;

    private String needPrintDetailSeperator,needSpecialSizeTitle;

    public PrintBillUtil(Socket socket, OutputStream OutStream, InputStream InStream, Context context){
        super();
        this.socket = socket;
        this.OutStream = OutStream;
        this.InStream = InStream;
        this.myApplication = (MyApplication) context.getApplicationContext();

        db =  new DatabaseHelper(context);
        String sql = "select * from fu_parameter ";
        cursor = db.queryParameter(sql, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            if (name.equals("needPrintDetailSeperator")){
                needPrintDetailSeperator = cursor.getString(cursor.getColumnIndex("val"));
            }

            if(name.equals("needSpecialSizeTitle"))
            {
                needSpecialSizeTitle = cursor.getString(cursor.getColumnIndex("val"));
            }
            /*if (name.equals("isWIFIPrinter")){
                //isWIFIPrinter = cursor.getString(cursor.getColumnIndex("val"));
            }
            if(name.equals("printerType80mm"))
            {
                //isPrinterType80mm = cursor.getString(cursor.getColumnIndex("val"));
            }*/
        }
    }

    public void prePrint(String printResult){
        this.clearbuffer();
        this.setup(110, 0, 5, 7, 0, 0, 0);
        //this.sendcommand("OFFSET 0.5\n");
        //this.sendcommand("BACKFEED 40\n");
        this.clearbuffer();
        int y = doPrint(printResult);

        this.clearbuffer();
        this.setup(110, y/8, 5, 7, 0, 0, 0);
        this.sendcommand("OFFSET 0.5\n");
        this.sendcommand("BACKFEED 80\n");
        this.clearbuffer();
        this.doPrint(printResult);

        this.printlabel(1, 1);
        this.clearbuffer();
        this.closeport();

    }

    public int doPrint(String printResult) {
        Log.d("--sfn", printResult);
        Map<String, Object> map = JsonToMap.toMap(printResult);

        JSONUtility jsonUtility = new JSONUtility();
        String prefix = "";
        if (("1").equals(jsonUtility.getStringValueFromJSON(map, "isSalesOrderBill"))) {
            prefix = "(订)";
        }
        String header = prefix + jsonUtility.getStringValueFromJSON(map, "header");

        String codeTitle = "单号: ";
        String code = jsonUtility.getStringValueFromJSON(map, "code");

        String dateTitle = "日期: ";
        String date = jsonUtility.getStringValueFromJSON(map, "occurrenceTime");

        String invTitle = "门店: ";
        String inv = jsonUtility.getStringValueFromJSON(map, "invString");

        String addressTitle = "地址: ";
        String address = jsonUtility.getStringValueFromJSON(map, "invAddress");
        address = address == null ? " " : address;

        String phoneTitle = "电话: ";
        String phone = jsonUtility.getStringValueFromJSON(map, "invTel");
        phone = phone == null ? " " : phone;

        String cellphoneTitle = "手机: ";
        String cellphone = jsonUtility.getStringValueFromJSON(map, "cellphone");
        cellphone = cellphone == null ? " " : cellphone;

        String alipay = jsonUtility.getStringValueFromJSON(map, "alipay");
        alipay = alipay == null ? " " : alipay;

        String accountnameTitle = "微信: ";
        String accountname = jsonUtility.getStringValueFromJSON(map, "accountname");
        accountname = accountname == null ? " " : accountname;

        String clientTitle = "客户: ";
        String client = jsonUtility.getStringValueFromJSON(map, "clientString");
        client = client == null ? " " : client;

        String staffTitle = "店员: ";
        String staff = jsonUtility.getStringValueFromJSON(map, "staffString");
        staff = staff == null ? " " : staff;

        String realalipay = jsonUtility.getStringValueFromJSON(map, "realalipay");
        String realalipaystring = jsonUtility.getStringValueFromJSON(map, "realalipaystring");

        String wechatpay = jsonUtility.getStringValueFromJSON(map, "wechatpay");
        String wechatpaystring = jsonUtility.getStringValueFromJSON(map, "wechatpaystring");
        String qrcodewx = jsonUtility.getStringValueFromJSON(map, "qrcodewx");

        String remarkSum = jsonUtility.getStringValueFromJSON(map, "remarkSum");

        List<Map<String, Object>> bankAccountListTemp = jsonUtility.getListValueFromJSON(map, "bankAccountList");
        List<Map<String, Object>> bankAccountList = new ArrayList<>();
        for (Map<String, Object> one : bankAccountListTemp) {
            String name = jsonUtility.getStringValueFromJSON(one, "name");
            if (!("").equals(name)) {
                bankAccountList.add(one);
            }
        }

        String appVersionTitle = "启豹店铺管理软件HD版 v";
        String appVersion = jsonUtility.getStringValueFromJSON(map, "appversion");

        String printDateTitle = "打印时间: ";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date printDate = new Date();
        String printDateString = sdf.format(printDate);

        Map<String, String> sumMap = new HashMap<>();
        sumMap.put("amount", jsonUtility.getStringValueFromJSON(map, "amountSum"));
        sumMap.put("total", jsonUtility.getStringValueFromJSON(map, "totalSum"));
        sumMap.put("remarkSum", jsonUtility.getStringValueFromJSON(map, "remarkSum") == null ? " " : jsonUtility.getStringValueFromJSON(map, "remarkSum"));

        List<Map<String, Object>> detailList = jsonUtility.getListValueFromJSON(map, "details");
        if (detailList == null) {
            detailList = new ArrayList<>();
        }

        int x = 0;
        int y = 0;

        int py = (800-(header.length()*40))/2;
        this.printerfont(750, y, "TSS24.BF2", 0, 1, 1, "客户联");
        y = y + 30;

        this.printerfont(x+py, y+20, "TSS24.BF2", 0, 3, 3, header);

        y = y + 100;
        this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, dateTitle+date);
        x = x + 320;
        this.printerfont(x, y , "TSS24.BF2", 0, 1, 1, codeTitle+code);

        x = 0;
        y = y + 30;
        this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, invTitle+inv);
        x = x + 320;
        this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, staffTitle+staff);
        y = y + 30;

        x = 0;
        this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, clientTitle + client);
        y = y+70;

        if("0".equals(needSpecialSizeTitle))//非尺码表头模式
        {
            if(myApplication.getFuAccountModel().getColorsizemode()==0)//顏色尺碼模式
            {
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "货品信息");
                x = x+300;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "颜色");
                x = x+80;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "尺码");
                x = x+80;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "数量");
                x = x+80;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "单价");
                x = x+80;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "折扣");
                x = x+80;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "小计");
                x = 0;
                y = y + 30;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "------------------------------------------------------------------------------");

                for (int i = 0; i < detailList.size(); i++){
                    Map<String, Object> detail = detailList.get(i);
                    x = 0;
                    y = y + 30;
                    String styleCode = jsonUtility.getStringValueFromJSON(detail, "code");
                    String name = jsonUtility .getStringValueFromJSON(detail, "name");
                    String amount = jsonUtility.getStringValueFromJSON(detail, "amount");
                    String price = jsonUtility.getStringValueFromJSON(detail, "price");
                    String total = jsonUtility.getStringValueFromJSON(detail, "total");
                    String color = jsonUtility.getStringValueFromJSON(detail, "colorString");
                    String size = jsonUtility.getStringValueFromJSON(detail, "sizeString");
                    String discount = jsonUtility.getStringValueFromJSON(detail, "discount");

                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, styleCode+" "+name);
                    x = x + 300;
                    if(color!=null)
                        this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, color);
                    x = x + 80;
                    if(size!=null)
                        this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, size);
                    x = x + 80;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, amount);
                    x = x + 80;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, bigDecimalUtil.getStringUtil(price));
                    x = x + 80;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, discount);
                    x = x + 80;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, bigDecimalUtil.getStringUtil(total));
                }

                x = 0;
                y = y + 30;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "------------------------------------------------------------------------------");
                y = y + 30;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, "合计");
                String amountSum = jsonUtility.getStringValueFromJSON(map, "amountSum");
                String totalSum = jsonUtility.getStringValueFromJSON(map, "totalSum");
                x = x + 460;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, amountSum);
                x = x + 240;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, bigDecimalUtil.getStringUtil(totalSum));

            }
            else//均色均码模式
            {
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "序");
                x = x + 40;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "款号");
                x = x + 250;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "名称");
                x = x + 200;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "数量");
                x = x + 100;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "单价");
                x = x + 100;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "小计");

                x = 0;
                y = y + 30;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "------------------------------------------------------------------------------");

                for (int i = 0; i < detailList.size(); i++){
                    Map<String, Object> detail = detailList.get(i);
                    x = 0;
                    y = y + 30;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, i+1+"");
                    String styleCode = jsonUtility.getStringValueFromJSON(detail, "code");
                    x = x + 40;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, styleCode);
                    String name = jsonUtility .getStringValueFromJSON(detail, "name");
                    x = x + 250;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, name);
                    String amount = jsonUtility.getStringValueFromJSON(detail, "amount");
                    x = x + 200;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, amount);
                    String price = jsonUtility.getStringValueFromJSON(detail, "price");
                    x = x + 100;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, bigDecimalUtil.getStringUtil(price));
                    String total = jsonUtility.getStringValueFromJSON(detail, "total");
                    x = x + 100;
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, bigDecimalUtil.getStringUtil(total));
                }

                x = 0;
                y = y + 30;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "------------------------------------------------------------------------------");

                y = y + 30;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, "合计");
                String amountSum = jsonUtility.getStringValueFromJSON(map, "amountSum");
                String totalSum = jsonUtility.getStringValueFromJSON(map, "totalSum");
                x = x + 500;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, amountSum);
                x = x + 200;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, bigDecimalUtil.getStringUtil(totalSum));
            }
        }
        else//尺码表头模式
        {

            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "货品信息");
            x = x+600;
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "数量");
            x = x+80;
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "单价");
            x = x+80;
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "小计");
            y=y+30;
            x = 0;

            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "颜色");
            x = x+100;

            List<Map<String, Object>> sizeList = jsonUtility.getListValueFromJSON(map, "sizeList");

            for(int i = 0;i <sizeList.size();i++)
            {
                Map<String, Object> size = sizeList.get(i);
                String sizeName = jsonUtility.getStringValueFromJSON(size, "name");
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, sizeName);
                x = x+50;
            }
            y = y + 30;
            x = 0;

            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "------------------------------------------------------------------------------");
            y = y + 30;

            List<Map<String, Object>> detailsList = jsonUtility.getListValueFromJSON(map, "detailsForSpecialSizeTitle");
            for(int i = 0 ;i<detailsList.size();i++)
            {
                Map<String, Object> details = detailsList.get(i);
                String styleId = jsonUtility.getStringValueFromJSON(details,"styleid");
                String detailcode  = jsonUtility.getStringValueFromJSON(details,"code");
                String name  = jsonUtility.getStringValueFromJSON(details,"name");
                String price = jsonUtility.getStringValueFromJSON(details,"price");
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, detailcode+"  "+name);
                x = x + 600;

                int sum=0 ;
                double total=0;
                for(int j =0 ;j<detailsList.size();j++)
                {
                    Map<String, Object> details1 = detailsList.get(j);
                    String styleId1 = jsonUtility.getStringValueFromJSON(details1,"styleid");

                    if(styleId1!=null)
                    {
                        if(styleId1.equals(styleId))
                        {
                            String  sumstr = jsonUtility.getStringValueFromJSON(details1,"totalSizeAmount");
                            String  totalstr = jsonUtility.getStringValueFromJSON(details1,"total");
                            if(sumstr!=null&&sumstr.length()>0)
                                sum += Integer.valueOf(sumstr);
                            if(totalstr!=null&&totalstr.length()>0)
                                total += Double.valueOf(totalstr);

                        }
                    }
                    else
                    {
                        String namestr = jsonUtility.getStringValueFromJSON(details1,"name");
                        if("抹零".equals(namestr))
                        {
                            String  totalstr = jsonUtility.getStringValueFromJSON(details1,"total");
                            if(totalstr!=null&&totalstr.length()>0)
                                total += Double.valueOf(totalstr);
                        }
                    }
                }
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, sum+"");
                x = x+ 80;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, price);
                x = x+ 80;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, total+"");

                y = y+30;
                x = 0;

                for(int j =0 ;j<detailsList.size();j++)
                {

                    Map<String, Object> details1 = detailsList.get(j);
                    String styleId1 = jsonUtility.getStringValueFromJSON(details1,"styleid");
                    if(styleId1!=null)
                    {
                        if(styleId1.equals(styleId))
                        {
                            String color = jsonUtility.getStringValueFromJSON(details1,"colorString");
                            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, color);
                            x = x+100;

                            List<Map<String, Object>> sizeAmountList = jsonUtility.getListValueFromJSON(details1,"sizeAmountList");
                            for(int k = 0;k< sizeList.size();k++)
                            {
                                for(int p =0;p< sizeAmountList.size();p++)
                                {
                                    if(jsonUtility.getStringValueFromJSON(sizeList.get(k),"id").equals(jsonUtility.getStringValueFromJSON(sizeAmountList.get(p),"sizeid")))
                                    {
                                        x =100+k*50;
                                        this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, jsonUtility.getStringValueFromJSON(sizeAmountList.get(p),"amount"));
                                    }
                                }
                            }
                            y=y+30;
                            x= 0;
                            if(j>i)
                            {
                                detailsList.remove(j);
                                j--;
                            }
                        }
                    }
                }

                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "------------------------------------------------------------------------------");
                y =y+30;
            }
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, "合计");
            String amountSum = jsonUtility.getStringValueFromJSON(map, "amountSum");
            String totalSum = jsonUtility.getStringValueFromJSON(map, "totalSum");
            x = x + 600;
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, amountSum);
            x = x + 160;
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, bigDecimalUtil.getStringUtil(totalSum));

        }


        x = 0;
        y = y + 50;
        this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "备注："+remarkSum);

        if (!("1").equals(jsonUtility.getStringValueFromJSON(map, "isSalesOrderBill"))) {
            String combineString = "";
            int salesTotalAmount = 0;
            double salesTotalMoney = 0.0f;
            int backTotalAmount = 0;
            double backTotalMoney = 0.0f;
            for (Map<String, Object> detail : detailList) {
                if (jsonUtility.getIntegerValueFromJSON(detail,
                        "stylecolorsizeid") != null) {
                    int detailAmount = jsonUtility.getIntegerValueFromJSON(
                            detail, "amount");
                    double detailTotal = jsonUtility
                            .getBigDecimalValueFromJSON(detail, "total")
                            .doubleValue();
                    if (detailAmount >= 0) {
                        salesTotalAmount += detailAmount;
                    } else {
                        backTotalAmount += detailAmount * (1);
                    }
                    if (detailTotal >= 0) {
                        salesTotalMoney += detailTotal;
                    } else {
                        backTotalMoney += detailTotal * (1);
                    }
                }
            }
            if (salesTotalAmount > 0) {
                combineString += "销售 " + salesTotalAmount + "  ";
            }
            if (salesTotalMoney > 0) {
                combineString += "销售额 " + String.format("%.2f", salesTotalMoney) + "  ";
            }
            if (backTotalAmount < 0) {
                combineString += "退货 " + backTotalAmount + "  ";
            }
            if (backTotalMoney < 0) {
                combineString += "退货额 " + String.format("%.2f", backTotalMoney) + "  ";
            }

            String otherString = "";
            int realPay = 0;
            List<Map<String, Object>> cutMoneyList = jsonUtility.getListValueFromJSON(map, "cutMoneyList") == null ? (new ArrayList<Map<String, Object>>())
                    : jsonUtility.getListValueFromJSON(map, "cutMoneyList");
            for (Map<String, Object> one : cutMoneyList) {
                otherString += jsonUtility.getStringValueFromJSON(one, "name") + " " + jsonUtility.getStringValueFromJSON(one, "money") + "  ";
            }

            x = 0;
            y = y + 30;
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, combineString);
            if (cutMoneyList.size() > 0) {
                y = y + 50;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, otherString);
            }


            String tempForPayway = "";
            List<Map<String, Object>> moneyList = jsonUtility .getListValueFromJSON(map, "moneyList") == null ? (new ArrayList<Map<String, Object>>())
                    : jsonUtility.getListValueFromJSON(map, "moneyList");
            for (Map<String, Object> one : moneyList) {
                Integer paymentid = jsonUtility.getIntegerValueFromJSON(one, "paymentid");
                if (paymentid.intValue() == 1) {
                    tempForPayway += jsonUtility.getStringValueFromJSON(one, "name")
                            + " " + jsonUtility.getStringValueFromJSON(one, "money") + "  ";
                } else {
                    tempForPayway += jsonUtility.getStringValueFromJSON(one, "name")
                            + " " + jsonUtility.getStringValueFromJSON(one, "moneyaccountString")
                            + " " + jsonUtility.getStringValueFromJSON(one, "money") + "  ";
                }
                realPay += jsonUtility.getBigDecimalValueFromJSON(one, "money").intValue();
            }
            int realTotalSum = jsonUtility.getBigDecimalValueFromJSON(map, "totalSum").intValue();
            String payString = "本单: " + realTotalSum;
            if (jsonUtility.getIntegerValueFromJSON(map, "type").intValue() == 1) {
                tempForPayway += "代收" + " " + (realTotalSum - realPay);
            } else {
                if (moneyList == null || moneyList.size() <= 0) {
                    if (realTotalSum == 0) {

                    } else if (realTotalSum > 0) {
                        tempForPayway += "本次欠款";
                    } else if (realTotalSum < 0) {
                        tempForPayway += "本次余额";
                    }
                }
            }
            y = y + 50;
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, (payString+"      "+tempForPayway));

            String arearsString = "";
            int lastArrears = jsonUtility.getStringValueFromJSON(map,
                    "arrears") == null || ("").equals(jsonUtility.getStringValueFromJSON(
                    map, "arrears")) ? 0 : jsonUtility.getIntegerValueFromJSON(map, "arrears").intValue();
            int currentArrears = realTotalSum - realPay;
            if (jsonUtility.getIntegerValueFromJSON(map, "type").intValue() == 1) {
                currentArrears = 0;
            }
            int totalArrears = lastArrears + currentArrears;
            arearsString += "上次累计" + (lastArrears < 0 ? "余额" : "欠款") + " "
                    + Math.abs(lastArrears) + "  " + "本次"
                    + (currentArrears < 0 ? "余额" : "欠款") + " "
                    + Math.abs(currentArrears) + "  " + "共累计"
                    + (totalArrears < 0 ? "余额" : "欠款") + " " + Math.abs(totalArrears);
            if (lastArrears != 0 || currentArrears != 0 || totalArrears != 0) {
                y = y + 50;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 2, arearsString);
            }
        }

        List<String> bankAccountLines = new ArrayList<>();
        for (int i = 0; i < bankAccountList.size() - 1; i += 2) {
            Map<String, Object> first = bankAccountList.get(i);
            Map<String, Object> second = bankAccountList.get(i + 1);

            String title1 = "汇款信息: ";
            String cardName1 = jsonUtility.getStringValueFromJSON(
                    first, "cardname");
            String name1 = jsonUtility.getStringValueFromJSON(first,
                    "name");
            String cardName2 = jsonUtility.getStringValueFromJSON(
                    second, "cardname");
            String name2 = jsonUtility.getStringValueFromJSON(second,
                    "name");
            String line = title1 + cardName1 + " " + name1;
            line += title1 + cardName2 + " " + name2;
            bankAccountLines.add(line);
        }
        y = y + 60;
        if (bankAccountList.size() > 0){
            Map<String, Object> last = bankAccountList.get(bankAccountList.size() - 1);
            String titleLast = "汇款信息: ";
            String cardNameLast = jsonUtility.getStringValueFromJSON(last, "cardname");
            String nameLast = jsonUtility.getStringValueFromJSON(last, "name");
            String lineLast = titleLast + cardNameLast + " " + nameLast;
            bankAccountLines.add(lineLast);
            for (String line : bankAccountLines) {
                x = 0;
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, line);
                y = y + 30;
            }
        }
        if(address!=null&&address.trim().length()>0)
        {
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, addressTitle+address);
            y = y + 30;
        }
        if((phone!=null&&phone.trim().length()>0)||(accountname!=null&&accountname.trim().length()>0))
        {
            if(phone!=null&&phone.trim().length()>0)
            {
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, phoneTitle+phone);
                x = x + 200;
            }
            if(accountname!=null&&accountname.trim().length()>0)
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, accountnameTitle+accountname);
            y = y + 30;
        }
        if(cellphone!=null&&cellphone.trim().length()>0)
        {
            x = 0;
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, cellphoneTitle+cellphone);
            y = y + 30;
        }

        String []str = alipay.split("\n");
        for(int i = 0 ;i<str.length;i++)
        {
            if(str[i].length()<=35)
            {
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, str[i]);
                y = y + 30;
            }
            else
            {
                while(str[i].length()>34)
                {
                    String tmpString = str[i].substring(0,34);
                    this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, tmpString);
                    y = y + 30;
                    str[i] =  str[i].substring(34, str[i].length());
                }
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, str[i]);
                y = y + 30;
            }

        }




        /*if (alipay.length() <= 35) {
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, alipay);
        } else {
            String tmpString = alipay.substring(0,34);
            String tmpString2 = alipay.substring(34, alipay.length());
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, tmpString);
            y = y + 30;
            this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, tmpString2);
            y = y + 30;
        }*/

        if((qrcodewx!=null&&qrcodewx.length()>0)||(realalipay!=null&&realalipay.length()>0)||(wechatpay!=null&&wechatpay.length()>0))
        {
            if(qrcodewx!=null&&qrcodewx.length()>0)
            {
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, "扫一扫，加微信");
                x = x + 300;
            }
            if(realalipay!=null&&realalipay.length()>0)
            {
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, realalipaystring);
                x = x + 300;
            }

            if(wechatpay!=null&&wechatpay.length()>0)
                this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, wechatpaystring);

            y = y + 30;
            x = 0;
            if(qrcodewx!=null&&qrcodewx.length()>0)
            {
                this.qrcode(x, y, qrcodewx);
                x = x + 300;
            }

            if(realalipay!=null&&realalipay.length()>0)
            {
                this.qrcode(x, y, realalipay);
                x = x + 300;
            }
            if(wechatpay!=null&&wechatpay.length()>0)
                this.qrcode(x, y, wechatpay);
            y = y + 200;
        }

        x = 0;

        this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, appVersionTitle+appVersion);
        x = x + 350;
        this.printerfont(x, y, "TSS24.BF2", 0, 1, 1, printDateTitle + printDateString);

        y = y + 50;
        return y;
    }

    public void printerfont(int x, int y, String size, int rotation, int x_multiplication, int y_multiplication, String string) {
        String message = "";
        try {
            message = "TEXT " + x + "," + y + ",\"" + size + "\"" + "," + rotation + " ," + x_multiplication + " ," + y_multiplication + ",\"" + string + "\"\n";
            this.sendcommand((message.getBytes("gb2312")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void printeForPT380(String printResult) {
        OneLineArray oneLineArray = new OneLineArray(0);
        byte [] lineArray;
        Map<String, Object> map = JsonToMap.toMap(printResult);
        JSONUtility jsonUtility = new JSONUtility();

        String realalipay = jsonUtility.getStringValueFromJSON(map, "realalipay");
        String realalipaystring = jsonUtility.getStringValueFromJSON(map, "realalipaystring");

        String wechatpay = jsonUtility.getStringValueFromJSON(map, "wechatpay");
        String wechatpaystring = jsonUtility.getStringValueFromJSON(map, "wechatpaystring");
        String qrcodewx = jsonUtility.getStringValueFromJSON(map, "qrcodewx");

        String remarkSum = jsonUtility.getStringValueFromJSON(map, "remarkSum");

        List<Map<String, Object>> bankAccountListTemp = jsonUtility.getListValueFromJSON(map, "bankAccountList");
        List<Map<String, Object>> bankAccountList = new ArrayList<>();
        for (Map<String, Object> one : bankAccountListTemp) {
            String name = jsonUtility.getStringValueFromJSON(one, "name");
            if (!("").equals(name)) {
                bankAccountList.add(one);
            }
        }


        Map<String, String> sumMap = new HashMap<>();
        sumMap.put("amount", jsonUtility.getStringValueFromJSON(map, "amountSum"));
        sumMap.put("total", jsonUtility.getStringValueFromJSON(map, "totalSum"));
        sumMap.put("remarkSum", jsonUtility.getStringValueFromJSON(map, "remarkSum") == null ? " " : jsonUtility.getStringValueFromJSON(map, "remarkSum"));


        printerOneLine(strToArray(" "),true,false,0);
        printerOneLine(strToArray(" "),true,false,0);
        String header = "";
        if (("1").equals(jsonUtility.getStringValueFromJSON(map, "isSalesOrderBill"))) {
            header = "(订)";
        }
        header += jsonUtility.getStringValueFromJSON(map, "header");
        printerOneLine(strToArray(header),true,true,2);

        printerOneLine(strToArray(" "),true,false,0);


        String inv = jsonUtility.getStringValueFromJSON(map, "invString"); //门店
        String staff = jsonUtility.getStringValueFromJSON(map, "staffString"); //店员
        staff = staff == null ? " " : staff;
        lineArray = oneLineArray.clearArray()
                .putDataToArray(strToArray("门店: "+inv),true,0)
                .putDataToArray(strToArray("店员: "+staff),true,24)
                .getReturnArray();
        printerOneLine(lineArray,false,false,0);



        String code = jsonUtility.getStringValueFromJSON(map, "code");//单号
        String date = jsonUtility.getStringValueFromJSON(map, "occurrenceTime");//日期
        lineArray = oneLineArray.clearArray()
                .putDataToArray(strToArray("单号: "+code),true,0)
                .putDataToArray(strToArray("日期: "+date),true,24)
                .getReturnArray();
        printerOneLine(lineArray,false,false,0);


        //客户
        String client = jsonUtility.getStringValueFromJSON(map, "clientString");
        client = client == null ? " " : client;

        printerOneLine(strToArray("客户: "+client),false,true,0);

        List<Map<String, Object>> detailList = jsonUtility.getListValueFromJSON(map, "details");
        if (detailList == null) {
            detailList = new ArrayList<>();
        }

        if(myApplication.getFuAccountModel().getColorsizemode() ==0)//颜色尺码模式
        {
            printerOneLine(strToArray("货品信息"),false,false,0);

            lineArray = oneLineArray.clearArray()
                    .putDataToArray(strToArray("颜色"),true,0)
                    .putDataToArray(strToArray("尺码"),true,7)
                    .putDataToArray(strToArray("数量"),false,17)
                    .putDataToArray(strToArray("单价"),false,24)
                    .putDataToArray(strToArray("折扣"),false,31)
                    .putDataToArray(strToArray("小计"),false,39)
                    .getReturnArray();
            printerOneLine(lineArray,false,false,0);
            //画双虚线
            lineArray = oneLineArray.setDoubleDivide().getReturnArray();
            printerOneLine(lineArray,false,false,0);
            for (int i = 0; i < detailList.size(); i++) {
                Map<String, Object> detail = detailList.get(i);
                String styleCode = jsonUtility.getStringValueFromJSON(detail, "code");
                String name = jsonUtility .getStringValueFromJSON(detail, "name");
                String amount = jsonUtility.getStringValueFromJSON(detail, "amount");
                String color = jsonUtility.getStringValueFromJSON(detail, "colorString");
                String size = jsonUtility.getStringValueFromJSON(detail, "sizeString");
                String discount = jsonUtility.getStringValueFromJSON(detail, "discount");
                String price = jsonUtility.getStringValueFromJSON(detail, "price");
                String total = jsonUtility.getStringValueFromJSON(detail, "total");

                printerOneLine(strToArray(styleCode+"  "+name),false,false,0);
                lineArray = oneLineArray.clearArray()
                        .putDataToArray(strToArray(color),true,0)
                        .putDataToArray(strToArray(size),true,7)
                        .putDataToArray(strToArray(amount),false,16)
                        .putDataToArray(strToArray(price),false,24)
                        .putDataToArray(strToArray(discount),false,30)
                        .putDataToArray(strToArray(total),false,39)
                        .getReturnArray();
                printerOneLine(lineArray,false,false,0);

                if(("1").equals(needPrintDetailSeperator))//明细分割线打开
                {
                    if(i!=detailList.size()-1)//最后一个不打分割线
                    {
                        //画虚线
                        lineArray = oneLineArray.setDivide().getReturnArray();
                        printerOneLine(lineArray,false,false,0);
                    }
                }

            }
            //画虚线
            lineArray = oneLineArray.setDivide().getReturnArray();
            printerOneLine(lineArray,false,false,0);

            String amountSum = jsonUtility.getStringValueFromJSON(map, "amountSum");
            String totalSum = jsonUtility.getStringValueFromJSON(map, "totalSum");

            lineArray = oneLineArray.clearArray()
                    .putDataToArray(strToArray("合计: "),true,0)
                    .putDataToArray(strToArray(amountSum),false,16)
                    .putDataToArray(strToArray(totalSum),false,39)
                    .getReturnArray();
            printerOneLine(lineArray,false,true,0);

        }
        else//均色均码模式
        {
            lineArray = oneLineArray.clearArray()
                    .putDataToArray(strToArray("货品信息"),true,0)
                    .putDataToArray(strToArray("数量"),false,28)
                    .putDataToArray(strToArray("单价"),false,36)
                    .putDataToArray(strToArray("小计"),false,44)
                    .getReturnArray();
            printerOneLine(lineArray,false,false,0);
            //画双虚线
            lineArray = oneLineArray.setDoubleDivide().getReturnArray();
            printerOneLine(lineArray,false,false,0);

            for (int i = 0; i < detailList.size(); i++){
                Map<String, Object> detail = detailList.get(i);
                String styleCode = jsonUtility.getStringValueFromJSON(detail, "code");
                String name = jsonUtility .getStringValueFromJSON(detail, "name");
                String amount = jsonUtility.getStringValueFromJSON(detail, "amount");
                String price = jsonUtility.getStringValueFromJSON(detail, "price");
                String total = jsonUtility.getStringValueFromJSON(detail, "total");
                if(strToArray(styleCode+"  "+name).length>19)//名称款号太长，换行处理
                {
                    printerOneLine(strToArray(styleCode+"  "+name),false,false,0);
                    lineArray = oneLineArray.clearArray()
                            .putDataToArray(strToArray(amount),false,28)
                            .putDataToArray(strToArray(price),false,36)
                            .putDataToArray(strToArray(total),false,44)
                            .getReturnArray();
                    printerOneLine(lineArray,false,false,0);
                }
                else
                {
                    lineArray = oneLineArray.clearArray()
                            .putDataToArray(strToArray(styleCode+"  "+name),true,0)
                            .putDataToArray(strToArray(amount),false,28)
                            .putDataToArray(strToArray(price),false,36)
                            .putDataToArray(strToArray(total),false,44)
                            .getReturnArray();
                    printerOneLine(lineArray,false,false,0);
                }

                if(("1").equals(needPrintDetailSeperator))//明细分割线打开
                {
                    if(i!=detailList.size()-1)//最后一个不打分割线
                    {
                        //画虚线
                        lineArray = oneLineArray.setDivide().getReturnArray();
                        printerOneLine(lineArray,false,false,0);
                    }
                }
            }
            //画虚线
            lineArray = oneLineArray.setDivide().getReturnArray();
            printerOneLine(lineArray,false,false,0);

            String amountSum = jsonUtility.getStringValueFromJSON(map, "amountSum");
            String totalSum = jsonUtility.getStringValueFromJSON(map, "totalSum");

            lineArray = oneLineArray.clearArray()
                    .putDataToArray(strToArray("合计: "),true,0)
                    .putDataToArray(strToArray(amountSum),false,28)
                    .putDataToArray(strToArray(totalSum),false,44)
                    .getReturnArray();
            printerOneLine(lineArray,false,true,0);
        }


        printerOneLine(strToArray("备注: "+remarkSum),false,false,0);


        if (!("1").equals(jsonUtility.getStringValueFromJSON(map, "isSalesOrderBill"))) {
            String combineString = "";
            int salesTotalAmount = 0;
            double salesTotalMoney = 0.0f;
            int backTotalAmount = 0;
            double backTotalMoney = 0.0f;
            for (Map<String, Object> detail : detailList) {
                if (jsonUtility.getIntegerValueFromJSON(detail,
                        "stylecolorsizeid") != null) {
                    int detailAmount = jsonUtility.getIntegerValueFromJSON(
                            detail, "amount");
                    double detailTotal = jsonUtility
                            .getBigDecimalValueFromJSON(detail, "total")
                            .doubleValue();
                    if (detailAmount >= 0) {
                        salesTotalAmount += detailAmount;
                    } else {
                        backTotalAmount += detailAmount * (1);
                    }
                    if (detailTotal >= 0) {
                        salesTotalMoney += detailTotal;
                    } else {
                        backTotalMoney += detailTotal * (1);
                    }
                }
            }
            if (salesTotalAmount > 0) {
                combineString += "销售 " + salesTotalAmount + "  ";
            }
            if (salesTotalMoney > 0) {
                combineString += "销售额 " + String.format("%.2f", salesTotalMoney) + "  ";
            }
            if (backTotalAmount < 0) {
                combineString += "退货 " + backTotalAmount + "  ";
            }
            if (backTotalMoney < 0) {
                combineString += "退货额 " + String.format("%.2f", backTotalMoney) + "  ";
            }

            String otherString = "";
            int realPay = 0;
            List<Map<String, Object>> cutMoneyList = jsonUtility.getListValueFromJSON(map, "cutMoneyList") == null ? (new ArrayList<Map<String, Object>>())
                    : jsonUtility.getListValueFromJSON(map, "cutMoneyList");
            for (Map<String, Object> one : cutMoneyList) {
                otherString += jsonUtility.getStringValueFromJSON(one, "name") + " " + jsonUtility.getStringValueFromJSON(one, "money") + "  ";
            }

            printerOneLine(strToArray(combineString),false,false,1);

            if (cutMoneyList.size() > 0) {
                printerOneLine(strToArray(otherString),false,false,1);
            }


            String tempForPayway = "";
            List<Map<String, Object>> moneyList = jsonUtility .getListValueFromJSON(map, "moneyList") == null ? (new ArrayList<Map<String, Object>>())
                    : jsonUtility.getListValueFromJSON(map, "moneyList");
            for (Map<String, Object> one : moneyList) {
                Integer paymentid = jsonUtility.getIntegerValueFromJSON(one, "paymentid");
                if (paymentid.intValue() == 1) {
                    tempForPayway += jsonUtility.getStringValueFromJSON(one, "name")
                            + " " + jsonUtility.getStringValueFromJSON(one, "money") + "  ";
                } else {
                    tempForPayway += jsonUtility.getStringValueFromJSON(one, "name")
                            + " " + jsonUtility.getStringValueFromJSON(one, "moneyaccountString")
                            + " " + jsonUtility.getStringValueFromJSON(one, "money") + "  ";
                }
                realPay += jsonUtility.getBigDecimalValueFromJSON(one, "money").intValue();
            }
            int realTotalSum = jsonUtility.getBigDecimalValueFromJSON(map, "totalSum").intValue();
            String payString = "本单 " + realTotalSum;
            if (jsonUtility.getIntegerValueFromJSON(map, "type").intValue() == 1) {
                tempForPayway += "代收" + " " + (realTotalSum - realPay);
            } else {
                if (moneyList == null || moneyList.size() <= 0) {
                    if (realTotalSum == 0) {

                    } else if (realTotalSum > 0) {
                        tempForPayway += "本次欠款";
                    } else if (realTotalSum < 0) {
                        tempForPayway += "本次余款";
                    }
                }
            }


            printerOneLine(strToArray(payString+"  支付: "+tempForPayway),false,false,1);


            String arearsString = "";
            int lastArrears = jsonUtility.getStringValueFromJSON(map,
                    "arrears") == null || ("").equals(jsonUtility.getStringValueFromJSON(
                    map, "arrears")) ? 0 : jsonUtility.getIntegerValueFromJSON(map, "arrears").intValue();
            int currentArrears = realTotalSum - realPay;
            if (jsonUtility.getIntegerValueFromJSON(map, "type").intValue() == 1) {
                currentArrears = 0;
            }
            int totalArrears = lastArrears + currentArrears;
            arearsString += "上" + (lastArrears < 0 ? "余" : "欠") + " "
                    + Math.abs(lastArrears) + "  " + "本"
                    + (currentArrears < 0 ? "余" : "欠") + " "
                    + Math.abs(currentArrears) + "  " + "共"
                    + (totalArrears < 0 ? "余" : "欠") + " " + Math.abs(totalArrears);
            if (lastArrears != 0 || currentArrears != 0 || totalArrears != 0) {
                printerOneLine(strToArray(arearsString),false,false,1);
            }
        }

        List<String> bankAccountLines = new ArrayList<>();
        for (int i = 0; i < bankAccountList.size() - 1; i += 2) {
            Map<String, Object> first = bankAccountList.get(i);
            Map<String, Object> second = bankAccountList.get(i + 1);

            String title1 = "汇款信息: ";
            String cardName1 = jsonUtility.getStringValueFromJSON(
                    first, "cardname");
            String name1 = jsonUtility.getStringValueFromJSON(first,
                    "name");
            String cardName2 = jsonUtility.getStringValueFromJSON(
                    second, "cardname");
            String name2 = jsonUtility.getStringValueFromJSON(second,
                    "name");
            String line = title1 + cardName1 + " " + name1;
            line += title1 + cardName2 + " " + name2;
            bankAccountLines.add(line);
        }
        if (bankAccountList.size() > 0){
            Map<String, Object> last = bankAccountList.get(bankAccountList.size() - 1);
            String titleLast = "汇款信息: ";
            String cardNameLast = jsonUtility.getStringValueFromJSON(last, "cardname");
            String nameLast = jsonUtility.getStringValueFromJSON(last, "name");
            String lineLast = titleLast + cardNameLast + " " + nameLast;
            bankAccountLines.add(lineLast);
            for (String line : bankAccountLines) {
                printerOneLine(strToArray(line),false,false,0);
            }
        }

        String address = jsonUtility.getStringValueFromJSON(map, "invAddress");
        address = address == null ? " " : address;


        String phone = jsonUtility.getStringValueFromJSON(map, "invTel");
        phone = phone == null ? " " : phone;


        String cellphone = jsonUtility.getStringValueFromJSON(map, "cellphone");
        cellphone = cellphone == null ? " " : cellphone;

        String accountname = jsonUtility.getStringValueFromJSON(map, "accountname");
        accountname = accountname == null ? " " : accountname;

        printerOneLine(strToArray("地址: "+address),false,false,0);
        lineArray = oneLineArray.clearArray()
                .putDataToArray(strToArray("电话: "+phone),true,0)
                .putDataToArray(strToArray("微信: "+accountname),true,30)
                .getReturnArray();
        printerOneLine(lineArray,false,false,0);

        printerOneLine(strToArray("手机: "+cellphone),false,false,0);

        String alipay = jsonUtility.getStringValueFromJSON(map, "alipay");
        if(alipay!=null&&alipay.trim().length()>0)
        {
            printerOneLine(strToArray(alipay),false,false,0);
        }

        if(realalipay!=null&&realalipay.length()>0)
        {
            printerOneLine(strToArray(realalipaystring),false,false,0);
            qrcodeForPT380(realalipay);
            printerOneLine(strToArray(" "),true,false,0);
        }
        if(wechatpay!=null&&wechatpay.length()>0)
        {
            printerOneLine(strToArray(wechatpaystring),false,false,0);
            qrcodeForPT380(wechatpay);
            printerOneLine(strToArray(" "),true,false,0);
        }
        if(qrcodewx!=null&&qrcodewx.length()>0)
        {
            printerOneLine(strToArray("加微信"),false,false,0);
            qrcodeForPT380(qrcodewx);
            printerOneLine(strToArray(" "),true,false,0);
        }

        String appVersion = jsonUtility.getStringValueFromJSON(map, "appversion");
        printerOneLine(strToArray("启豹店铺管理软件 v"+appVersion+"  400-835-1880"),false,false,0);

        String printDateTitle = "打印时间: ";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date printDate = new Date();
        String printDateString = sdf.format(printDate);
        printerOneLine(strToArray("打印时间: "+printDateString),false,false,0);

        //结尾空行
        printerOneLine(strToArray(" "),true,false,0);
        printerOneLine(strToArray(" "),true,false,0);
    }

    public byte[] strToArray(String str) {
        try
        {
            return str.getBytes("gb2312");
        }catch (Exception e)
        {
            return null;
        }
    }

    //POS形式打印一行
    public void printerOneLine(byte[] dataArray,boolean isCenter,Boolean isBold,int font){
        byte []data = new byte[128];
        //对齐方式
        if (isCenter) {
            data[0] = 0x1B;
            data[1] = 0x61;
            data[2] = 0x01;
        }else{
            data[0] = 0x1B;
            data[1] = 0x61;
            data[2] = 0x00;
        }
        sendcommand(data,3);
        //字体设置
        data[0] = 0x1B;
        data[1] = 0x21;
        data[2] = 0b00000000;
        byte beBold = 0b00001000;
        byte beHeight = 0b00010000;
        byte beWidth = 0b00100000;
        //是否加粗
        if (isBold) {
            data[2] |= beBold;
        }
        if (font == 0) {
        }else if (font == 1) {
            data[2] |= beHeight;
        }else if (font == 2) {
            data[2] |= beHeight;
            data[2] |= beWidth;
        }
        sendcommand(data,3);
        //针对中文字体也需做出对应的设置
        data[0] = 0x1C;
        data[1] = 0x21;
        data[2] = 0b00000000;
        beHeight = 0b00001000;
        beWidth = 0b00000100;
        if (font == 0) {
        }else if (font == 1) {
            data[2] |= beHeight;
        }else if (font == 2) {
            data[2] |= beHeight;
            data[2] |= beWidth;
        }

        sendcommand(data,3);

        //行间距
        if(font ==0)
        {
            data[0] = 0x1B;
            data[1] = 0x32;
            sendcommand(data,2);

        }
        else
        {
            data[0] = 0x1B;
            data[1] = 0x33;
            data[2] = 0B01110000;//与IOS不同，ISO为0x05，这里是0x45
            //
            sendcommand(data,3);
        }


        byte finalArray[] = new byte[dataArray.length+1];
        System.arraycopy(dataArray,0,finalArray,0,dataArray.length);
        finalArray[finalArray.length-1] = 0x0A;
        sendcommand(finalArray);
    }

    public void qrcodeForPT380(String qrcodeStr){

        try
        {
            byte data[] = new byte[128];
            data[0] = 0x1d;
            data[1] = 0x28;
            data[2] = 0x6b;
            data[3] = 0x03;
            data[4] = 0x00;
            data[5] = 0x31;
            data[6] = 0x43;
            data[7] = 0x07;
            sendcommand(data,8);


            data[0] = 0x1d;
            data[1] = 0x28;
            data[2] = 0x6b;
            data[3] = 0x03;
            data[4] = 0x00;
            data[5] = 0x31;
            data[6] = 0x45;
            data[7] = 0x33;
            sendcommand(data,8);

            //byte qrArray[] =qrcodeStr.getBytes("utf-8");
            //byte
            data[0] = 0x1d;
            data[1] = 0x28;
            data[2] = 0x6b;
            data[3] = (byte) ((qrcodeStr.length() + 3) % 256);
            data[4] = (byte) ((qrcodeStr.length() + 3) / 256);
            data[5] = 0x31;
            data[6] = 0x50;
            data[7] = 0x30;
            sendcommand(data,8);
            sendcommand(qrcodeStr.getBytes("utf-8"));

            data[0] = 0x1d;
            data[1] = 0x28;
            data[2] = 0x6b;
            data[3] = 0x03;
            data[4] = 0x00;
            data[5] = 0x31;
            data[6] = 0x51;
            data[7] = 0x30;
            sendcommand(data,8);

        }catch (Exception e)
        {

        }






    }


    public void qrcode(int x, int y, String string) {
        String message = "";
        message = "QRCODE "+x+","+y+",H,4,A,0,\""+string+"\"\n";

        byte[] msgBuffer = message.getBytes();
        try {
            this.OutStream.write(msgBuffer);
        } catch (IOException var22) {
            var22.printStackTrace();
        }

    }

    public void sendcommand(String message) {
        byte[] msgBuffer = new byte[0];
        try {
            msgBuffer = message.getBytes("gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        sendcommand(msgBuffer);
    }

    public void sendcommand(byte[] message) {
        try {
            this.OutStream.write(message);
        } catch (IOException var3) {
            var3.printStackTrace();
        }
        //Log.i("TestLog","-----"+message.length+"----------"+message[0]);
    }

    public void sendcommand(byte[] message, int sendLenght){
        byte []data = new byte[sendLenght];
        for(int i = 0;i<sendLenght;i++)
        {
            data[i]=message[i];
        }
        sendcommand(data);
    }

    public void clearbuffer() {
        String message = "CLS\n";
        byte[] msgBuffer = message.getBytes();
        try {
            this.OutStream.write(msgBuffer);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    public void setup(int width, int height, int speed, int density, int sensor, int sensor_distance, int sensor_offset) {
        String message = "";
        String size = "SIZE " + width + " mm" + ", " + height + " mm";
        String speed_value = "SPEED " + speed;
        String density_value = "DENSITY " + density;
        String sensor_value = "";
        if (sensor == 0) {
            sensor_value = "GAP " + sensor_distance + " mm" + ", " + sensor_offset + " mm";
        } else if (sensor == 1) {
            sensor_value = "BLINE " + sensor_distance + " mm" + ", " + sensor_offset + " mm";
        }

        message = size + "\n" + speed_value + "\n" + density_value + "\n" + sensor_value + "\n";
        byte[] msgBuffer = message.getBytes();

        try {
            this.OutStream.write(msgBuffer);
        } catch (IOException var15) {
            var15.printStackTrace();
        }
    }

    public void printlabel(int quantity, int copy) {
        String message = "";
        message = "PRINT " + quantity + ", " + copy + "\n";
        byte[] msgBuffer = message.getBytes();

        try {
            this.OutStream.write(msgBuffer);
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    public void closeport() {
        try {
            this.OutStream.flush();
            this.socket.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    public String status() {
        byte[] message = new byte[]{(byte) 27, (byte) 33, (byte) 63};
        try {
            this.OutStream.write(message);
        } catch (IOException var4) {
            Log.e("THINBTCLIENT", "ON RESUME: Exception during write.", var4);
        }

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

        int tim;
        try {
            while (this.InStream.available() > 0) {
                this.readBuf = new byte[1024];
                tim = this.InStream.read(this.readBuf);
                Log.e("","tim is "+tim);
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        if (this.readBuf[0] == 2 && this.readBuf[5] == 3) {
            for (tim = 0; tim <= 7; ++tim) {
                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 64 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 64 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Ready";
                    this.readBuf = new byte[1024];
                    break;
                }
                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 69 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 96 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Head Open";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 64 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 96 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Head Open";
                    this.readBuf = new byte[1024];
                    break;
                }
                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 69 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 72 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Ribbon Jam";
                    this.readBuf = new byte[1024];
                    break;
                }
                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 69 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 68 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Ribbon Empty";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 69 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 65 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "No Paper";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 69 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 66 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Paper Jam";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 69 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 65 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Paper Empty";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 67 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 64 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Cutting";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 75 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 64 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Waiting to Press Print Key";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 76 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 64 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Waiting to Take Label";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 80 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 64 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Printing Batch";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 96 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 64 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Pause";
                    this.readBuf = new byte[1024];
                    break;
                }

                if (this.readBuf[tim] == 2 && this.readBuf[tim + 1] == 69 && this.readBuf[tim + 2] == 64 && this.readBuf[tim + 3] == 64 && this.readBuf[tim + 4] == 64 && this.readBuf[tim + 5] == 3) {
                    this.printerstatus = "Pause";
                    this.readBuf = new byte[1024];
                    break;
                }
            }
        }
        return this.printerstatus;
    }

    //条形码
    public void barcode(int x, int y, String type, int height, int human_readable, int rotation, int narrow, int wide, String string) {
        String message = "";
        String barcode = "BARCODE ";
        String position = x + "," + y;
        String mode = "\"" + type + "\"";
        String height_value = "" + height;
        String human_value = "" + human_readable;
        String rota = "" + rotation;
        String narrow_value = "" + narrow;
        String wide_value = "" + wide;
        String string_value = "\"" + string + "\"";
        message = barcode + position + " ," + mode + " ," + height_value + " ," + human_value + " ," + rota + " ," + narrow_value + " ," + wide_value + " ," + string_value + "\n";
        byte[] msgBuffer = message.getBytes();

        try {
            this.OutStream.write(msgBuffer);
        } catch (IOException var22) {
            var22.printStackTrace();
        }
    }
}
