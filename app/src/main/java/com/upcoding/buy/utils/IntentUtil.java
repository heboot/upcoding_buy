package com.upcoding.buy.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.upcoding.buy.common.ContentKey;
import com.upcoding.buy.model.InfoModel;
import com.upcoding.buy.ui.recommend.RecommentDetailActivity;

/**
 * Created by Heboot on 2017/5/3.
 */

public class IntentUtil {
    public static void intent2InfoDetailActivity(Activity context, InfoModel infoModel, View v) {
//        InfoService.getInstance().read(infoModel.getInfoId());
        Intent intent = new Intent(context.getApplicationContext(), RecommentDetailActivity.class);
        intent.putExtra(ContentKey.INFO_MODEL, infoModel);
//        context.startActivity(intent);

        //将原先的跳转改成如下方式，注意这里面的第三个参数决定了ActivityTwo 布局中的android:transitionName的值，它们要保持一致
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context, v, "shareTransition").toBundle());
    }
}
