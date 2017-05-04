package com.upcoding.buy.ui.recommend;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.upcoding.buy.R;
import com.upcoding.buy.common.ContentKey;
import com.upcoding.buy.databinding.InfoDetailBind;
import com.upcoding.buy.model.InfoModel;
import com.upcoding.buy.ui.ToolbarActivity;

/**
 * Created by Heboot on 2017/5/3.
 */

public class RecommentDetailActivity extends ToolbarActivity {

    private InfoModel infoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InfoDetailBind infoDetailBind = DataBindingUtil.setContentView(this, R.layout.activity_info_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoModel = (InfoModel) getIntent().getExtras().get(ContentKey.INFO_MODEL);
        if (infoModel != null) {
            infoDetailBind.setInfoModel(infoModel);
        } else {
        }

    }
}
