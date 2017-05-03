package com.upcoding.buy.utils;

import android.content.Context;
import android.content.Intent;

import com.upcoding.buy.common.ContentKey;
import com.upcoding.buy.model.InfoModel;
import com.upcoding.buy.ui.recommend.RecommentDetailActivity;

/**
 * Created by Heboot on 2017/5/3.
 */

public class IntentUtil {
    public static void intent2InfoDetailActivity(Context context, InfoModel infoModel) {
//        InfoService.getInstance().read(infoModel.getInfoId());
        Intent intent = new Intent(context, RecommentDetailActivity.class);
        intent.putExtra(ContentKey.INFO_MODEL, infoModel);
        context.startActivity(intent);
    }
}
