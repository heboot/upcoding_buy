package com.upcoding.buy.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.usage.ConfigurationStats;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.upcoding.buy.common.ConstantValue;
import com.upcoding.buy.common.ContentKey;
import com.upcoding.buy.model.InfoModel;
import com.upcoding.buy.model.PostModel;
import com.upcoding.buy.ui.info.ContentDetailActivity;

/**
 * Created by Heboot on 2017/5/3.
 */

public class IntentUtil {

    public static void intent2ContentDetailActivity(Activity context, InfoModel infoModel, View v) {
        Intent intent = new Intent(context.getApplicationContext(), ContentDetailActivity.class);
        intent.putExtra(ContentKey.INFO_MODEL, infoModel);
        intent.putExtra(ContentKey.PAGEJUMP_CONTENT_TYPE, ConstantValue.CONTENT_TYPE_INFO);
        //将原先的跳转改成如下方式，注意这里面的第三个参数决定了ActivityTwo 布局中的android:transitionName的值，它们要保持一致
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context, v, "shareTransition").toBundle());
    }

    public static void intent2ContentDetailActivity(Activity context, PostModel postModel, View v) {
        Intent intent = new Intent(context.getApplicationContext(), ContentDetailActivity.class);
        intent.putExtra(ContentKey.POST_MODEL, postModel);
        intent.putExtra(ContentKey.PAGEJUMP_CONTENT_TYPE, ConstantValue.CONTENT_TYPE_POST);
        //将原先的跳转改成如下方式，注意这里面的第三个参数决定了ActivityTwo 布局中的android:transitionName的值，它们要保持一致
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context, v, "shareTransition").toBundle());
    }

    public static void intent2LoginActivity(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), ContentDetailActivity.class);
        //将原先的跳转改成如下方式，注意这里面的第三个参数决定了ActivityTwo 布局中的android:transitionName的值，它们要保持一致
    }
}
