package com.smyhvae.bleprint.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.gprinter.command.EscCommand;
import com.gprinter.command.LabelCommand;
import com.smyhvae.bleprint.DeviceConnFactoryManager;
import com.smyhvae.bleprint.model.BlePrintModel;
import com.smyhvae.myapplication.MyApplication;
import com.smyhvae.myapplication.R;

import java.util.Vector;

/**
 * 蓝牙打印管理服务
 * <p>
 * Created by Administrator on 2018/8/12.
 */

public class BlePrintManage implements IBlePrintServer<BlePrintModel> {

    @Override
    public void receiptPrint(BlePrintModel date) {
        EscCommand esc = new EscCommand();

        esc.addInitializePrinter();
        esc.addPrintAndFeedLines((byte) 3);

        // 设置打印居中
        esc.addSelectJustification(EscCommand.JUSTIFICATION.RIGHT);
        // 设置为倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        // 打印文字
        esc.addText("客户联\n");

        // 设置打印居中
        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        // 设置为倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
        // 打印title
        esc.addText(date.getTitle() + "\n");
        esc.addPrintAndLineFeed();

        // 取消倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText(String.format("日期: %s", "2018-08-12"));
        esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
        esc.addSetAbsolutePrintPosition((short) 10);
        esc.addText(String.format("单号: %s", "216"));
        esc.addPrintAndLineFeed();

        esc.addText(String.format("门店: %s", "默认店"));
        esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
        esc.addSetAbsolutePrintPosition((short) 10);
        esc.addText(String.format("店员: %s", "王大陆"));
        esc.addPrintAndLineFeed();

        // 设置为倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText(String.format("客户: %s", "散客"));
        esc.addPrintAndLineFeed();
        esc.addPrintAndLineFeed();

        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        /* 绝对位置 具体详细信息请查看GP58编程手册 */
        esc.addText("货品信息         数量  单价    小计\n");
        esc.addText("================================================", "GB2312");
        esc.addPrintAndLineFeed();
        esc.addText("1503 花纹黑丝袜");
        esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
        esc.addSetAbsolutePrintPosition((short) 8);
        esc.addText("1");
        esc.addSetAbsolutePrintPosition((short) 11);
        esc.addText("350");
        esc.addSetAbsolutePrintPosition((short) 14);
        esc.addText("157.5");
        esc.addPrintAndLineFeed();

        esc.addText("983 被子");
        esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
        esc.addSetAbsolutePrintPosition((short) 8);
        esc.addText("2");
        esc.addSetAbsolutePrintPosition((short) 11);
        esc.addText("80");
        esc.addSetAbsolutePrintPosition((short) 14);
        esc.addText("72");
        esc.addPrintAndLineFeed();
        esc.addText("------------------------------------------------", "GB2312");
        esc.addPrintAndLineFeed();

        esc.addText("合计");
        esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
        esc.addSetAbsolutePrintPosition((short) 8);
        esc.addText("3");
        esc.addSetAbsolutePrintPosition((short) 14);
        esc.addText("130");
        esc.addPrintAndLineFeed();
        esc.addText(String.format("配置:%s", ""));
        esc.addPrintAndLineFeed();
        esc.addPrintAndLineFeed();

		/*
         * QRCode命令打印 此命令只在支持QRCode命令打印的机型才能使用。 在不支持二维码指令打印的机型上，则需要发送二维条码图片
		 */
        // 打印文字
        esc.addText("二维码\n");

        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        // 设置纠错等级
        esc.addSelectErrorCorrectionLevelForQRCode((byte) 0x31);
        // 设置qrcode模块大小
        esc.addSelectSizeOfModuleForQRCode((byte) 6);
        // 设置qrcode内容
        esc.addStoreQRCodeData("www.smarnet.cc");
        esc.addPrintQRCode();
        esc.addPrintAndLineFeed();
        esc.addText("微信扫码");
        esc.addPrintAndLineFeed();
        esc.addPrintAndLineFeed();


        // 设置纠错等级
        esc.addSelectErrorCorrectionLevelForQRCode((byte) 0x31);
        // 设置qrcode模块大小
        esc.addSelectSizeOfModuleForQRCode((byte) 6);
        // 设置qrcode内容
        esc.addStoreQRCodeData("www.smarnet.cc");
        esc.addPrintQRCode();
        esc.addPrintAndLineFeed();
        esc.addText("支付宝");
        esc.addPrintAndLineFeed();
        esc.addPrintAndLineFeed();


        // 设置纠错等级
        esc.addSelectErrorCorrectionLevelForQRCode((byte) 0x31);
        // 设置qrcode模块大小
        esc.addSelectSizeOfModuleForQRCode((byte) 6);
        // 设置qrcode内容
        esc.addStoreQRCodeData("www.smarnet.cc");
        esc.addPrintQRCode();
        esc.addPrintAndLineFeed();
        esc.addText("加微信");
        esc.addPrintAndLineFeed();
        esc.addPrintAndLineFeed();

        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText(String.format("打印时间:%s", "2018-08-12 18:55:45"));

        // 开钱箱
        esc.addGeneratePlus(LabelCommand.FOOT.F5, (byte) 255, (byte) 255);
        esc.addPrintAndFeedLines((byte) 8);
        // 加入查询打印机状态，打印完成后，此时会接收到GpCom.ACTION_DEVICE_STATUS广播
        esc.addQueryPrinterStatus();
        Vector<Byte> datas = esc.getCommand();
        // 发送数据
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].sendDataImmediately(datas);
    }
}
