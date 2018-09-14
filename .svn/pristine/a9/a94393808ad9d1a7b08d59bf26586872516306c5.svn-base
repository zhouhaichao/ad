package com.smyhvae.service;

import android.content.Context;

import com.smyhvae.model.FuBaseModel;
import com.smyhvae.util.DataUtil;
import com.smyhvae.util.DialogUtil;
import com.smyhvae.util.Logcat;

import java.util.ArrayList;
import java.util.List;

public class AcService {
    public List<FuBaseModel> getAC(Context context, String url, int loginstaffid, int logininvid, String accessKey, int accountid, String methodid, String input, Integer justForName) {

        BaseService baseService = new BaseService();
        String result = baseService.doListForAC(url, methodid, loginstaffid, logininvid, accessKey, accountid, input, justForName);
        Logcat.show(result);
        DataUtil dataUtil = new DataUtil();
        FuBaseModel baseModel = dataUtil.message(result);
        List<FuBaseModel> baseModelList = new ArrayList<>();
        if (baseModel.getResultCode().intValue() == 1) {
            baseModelList = dataUtil.getDataListForAC(result);
        } else if (baseModel.getResultCode().intValue() == 0) {
            DialogUtil.showDialog(context, baseModel.getMessage(), false);
        }
        return baseModelList;
    }
}
