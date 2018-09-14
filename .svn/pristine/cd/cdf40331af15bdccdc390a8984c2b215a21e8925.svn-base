package com.smyhvae.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sfn.db";  //数据库名称
    public static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql="CREATE TABLE account(id integer primary key, code VARCHAR(20), password VARCHAR(20), accountid integer(20), accountname VARCHAR(20))";
//        db.execSQL(sql);
        String sql = "CREATE TABLE fu_parameter(id integer primary key AUTOINCREMENT, name VARCHAR(100), val VARCHAR(100), remark VARCHAR(100))";
        db.execSQL(sql);
        initParameter(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public Cursor queryParameter(String sql, String[] bindArgs){
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(sql, bindArgs);
        return cursor;
    }

    public void updateData(SQLiteDatabase db, String val, String name) {
        ContentValues cv = new ContentValues();
        cv.put("val", val);
        String whereClasuse = "name = ?";
        String[] whereArgs = new String[]{String.valueOf(name)};
        db.update("fu_parameter", cv, whereClasuse, whereArgs);
    }

    public void initParameter(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("id", "1");
            values.put("name", "databaseVersion");
            values.put("val", DATABASE_VERSION);
            values.put("remark", "数据库版本号");

            db.insert("fu_parameter", null, values);
            values.put("id", "2");
            values.put("name", "lastaccountid");
            values.put("val", "");
            values.put("remark", "上次登录帐套");
            db.insert("fu_parameter", null, values);

            values.put("id", "3");
            values.put("name", "lastLoginNo");
            values.put("val", "");
            values.put("remark", "上次登录工号");
            db.insert("fu_parameter", null, values);

            values.put("id", "4");
            values.put("name", "printerAddress");
            values.put("val", "");
            values.put("remark", "打印机地址");
            db.insert("fu_parameter", null, values);

            values.put("id", "5");
            values.put("name", "lastSyncTime_fu_staff");
            values.put("val", "");
            values.put("remark", "员工表上次同步时间");
            db.insert("fu_parameter", null, values);

            values.put("id", "6");
            values.put("name", "lastSyncTime_fu_client");
            values.put("val", "");
            values.put("remark", "客户表上次同步时间");
            db.insert("fu_parameter", null, values);

            values.put("id", "7");
            values.put("name", "lastSyncTime_fu_style");
            values.put("val", "");
            values.put("remark", "款表上次同步时间");
            db.insert("fu_parameter", null, values);

            values.put("id", "8");
            values.put("name", "lastSyncTime_fu_brand");
            values.put("val", "");
            values.put("remark", "品牌表上次同步时间");
            db.insert("fu_parameter", null, values);

            values.put("id", "9");
            values.put("name", "printCount");
            values.put("val", "1");
            values.put("remark", "打印份数");
            db.insert("fu_parameter", null, values);

            values.put("id", "10");
            values.put("name", "needMergeDetail");
            values.put("val", "0");
            values.put("remark", "开单时是否合并单据明细");
            db.insert("fu_parameter", null, values);

            values.put("id", "11");
            values.put("name", "needQBKeyboard");
            values.put("val", "1");
            values.put("remark", "使用启豹键盘");
            db.insert("fu_parameter", null, values);

            values.put("id", "12");
            values.put("name", "needSpecialSizeTitle");
            values.put("val", "0");
            values.put("remark", "尺码表头模板");
            db.insert("fu_parameter", null, values);

            values.put("id", "13");
            values.put("name", "localSales");
            values.put("val", "1");
            values.put("remark", "本地选款");
            db.insert("fu_parameter", null, values);

            values.put("id", "14");
            values.put("name", "printInterval");
            values.put("val", "0");
            values.put("remark", "连续打印间隔");
            db.insert("fu_parameter", null, values);

            values.put("id", "15");
            values.put("name", "printerType80mm");
            values.put("val", "0");
            values.put("remark", "80mm打印机");
            db.insert("fu_parameter", null, values);

            values.put("id", "16");
            values.put("name", "needLandscape");
            values.put("val", "0");
            values.put("remark", "纵向打印");
            db.insert("fu_parameter", null, values);

            values.put("id", "17");
            values.put("name", "offsetX");
            values.put("val", "0");
            values.put("remark", "水平偏移量");
            db.insert("fu_parameter", null, values);

            values.put("id", "18");
            values.put("name", "offsetY");
            values.put("val", "0");
            values.put("remark", "垂直偏移量");
            db.insert("fu_parameter", null, values);

            values.put("id", "19");
            values.put("name", "codeOffset");
            values.put("val", "0");
            values.put("remark", "款号列偏移量");
            db.insert("fu_parameter", null, values);

            values.put("id", "20");
            values.put("name", "nameOffset");
            values.put("val", "0");
            values.put("remark", "名称列偏移量");
            db.insert("fu_parameter", null, values);

            values.put("id", "21");
            values.put("name", "needPrintDetailSeperator");
            values.put("val", "0");
            values.put("remark", "打印明细分隔线");
            db.insert("fu_parameter", null, values);

            values.put("id", "22");
            values.put("name", "isWIFIPrinter");
            values.put("val", "0");
            values.put("remark", "是否WIFI打印机");
            db.insert("fu_parameter", null, values);

            values.put("id", "23");
            values.put("name", "amountOffset");
            values.put("val", "0");
            values.put("remark", "数量列偏移量");
            db.insert("fu_parameter", null, values);

            values.put("id", "24");
            values.put("name", "priceOffset");
            values.put("val", "0");
            values.put("remark", "单价列偏移量");
            db.insert("fu_parameter", null, values);

            values.put("id", "25");
            values.put("name", "totalOffset");
            values.put("val", "0");
            values.put("remark", "小计列偏移量");
            db.insert("fu_parameter", null, values);

            values.put("id", "26");
            values.put("name", "remarkOffset");
            values.put("val", "0");
            values.put("remark", "备注列偏移量");
            db.insert("fu_parameter", null, values);

            values.put("id", "27");
            values.put("name", "convertToNotColorSizeStyleToPrint");
            values.put("val", "0");
            values.put("remark", "转换为均色均码风格打印");
            db.insert("fu_parameter", null, values);

            values.put("id", "28");
            values.put("name", "customizeBarcodePrint");
            values.put("val", "0");
            values.put("remark", "自定义条码打印");
            db.insert("fu_parameter", null, values);

            values.put("id", "29");
            values.put("name", "barcodeWithoutYear");
            values.put("val", "0");
            values.put("remark", "条码去除年份");
            db.insert("fu_parameter", null, values);

            values.put("id", "30");
            values.put("name", "barcodePaperSize");
            values.put("val", "80_60");
            values.put("remark", "条码信息模式");
            db.insert("fu_parameter", null, values);

            values.put("id", "31");
            values.put("name", "lastSyncTime_fu_style_barcode_info");
            values.put("val", "");
            values.put("remark", "条码信息表上次同步时间");
            db.insert("fu_parameter", null, values);

            values.put("id", "32");
            values.put("name", "statement");
            values.put("val", "0");
            values.put("remark", "对账单默认模式");
            db.insert("fu_parameter", null, values);

            values.put("id", "33");
            values.put("name", "statementforarrears");
            values.put("val", "0");
            values.put("remark", "对账单按累计欠款");
            db.insert("fu_parameter", null, values);

            values.put("id", "34");
            values.put("name", "statementforconvertdetail");
            values.put("val", "0");
            values.put("remark", "明细对账单按单号合并");
            db.insert("fu_parameter", null, values);

            values.put("id", "35");
            values.put("name", "styleInfoChange");
            values.put("val", "0");
            values.put("remark", "货品信息换行");
            db.insert("fu_parameter", null, values);

            values.put("id", "36");
            values.put("name", "isPos");
            values.put("val", "0");
            values.put("remark", "pos指令打印");
            db.insert("fu_parameter", null, values);

            values.put("id", "37");
            values.put("name", "convertToNotSizeStyleToPrint");
            values.put("val", "0");
            values.put("remark", "合并颜色打印");
            db.insert("fu_parameter", null, values);

            values.put("id", "38");
            values.put("name", "tempPrintCount");
            values.put("val", "0");
            values.put("remark", "临时打印份数");
            db.insert("fu_parameter", null, values);

            values.put("id", "39");
            values.put("name", "isLocal");
            values.put("val", "1");
            values.put("remark", "是否本地AC");
            db.insert("fu_parameter", null, values);

            values.put("id", "40");
            values.put("name", "btPrinter");
            values.put("val", "0");
            values.put("remark", "蓝牙打印机");
            db.insert("fu_parameter", null, values);

            values.put("id", "41");
            values.put("name", "zq_dualmode");
            values.put("val", "0");
            values.put("remark", "中崎双模");
            db.insert("fu_parameter", null, values);

            values.put("id", "42");
            values.put("name", "btuuid");
            values.put("val", "");
            values.put("remark", "蓝牙设备标志");
            db.insert("fu_parameter", null, values);

            values.put("id", "43");
            values.put("name", "printStandardBarcode");
            values.put("val", "0");
            values.put("remark", "销售单打印标准条码(仅限针式打印)");
            db.insert("fu_parameter", null, values);

            values.put("id", "44");
            values.put("name", "PT380");
            values.put("val", "0");
            values.put("remark", "PT380");
            db.insert("fu_parameter", null, values);

            values.put("id", "45");
            values.put("name", "printerHost");
            values.put("val", "0");
            values.put("remark", "蓝牙打印主机");
            db.insert("fu_parameter", null, values);

            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            db.endTransaction();
        }
    }
}
