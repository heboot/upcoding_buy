package com.upcoding.buy.ui.recommend;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.upcoding.buy.R;
import com.upcoding.buy.common.ContentKey;
import com.upcoding.buy.databinding.InfoDetailBind;
import com.upcoding.buy.model.InfoModel;
import com.upcoding.buy.ui.ToolbarActivity;

/**
 * Created by Heboot on 2017/5/3.
 */

public class InfoDetailActivity extends ToolbarActivity {

    //test vpn
    private InfoModel infoModel;

    private View toolBarView;

    private TextView toolBarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InfoDetailBind infoDetailBind = DataBindingUtil.setContentView(this, R.layout.activity_info_detail);
        infoModel = (InfoModel) getIntent().getExtras().get(ContentKey.INFO_MODEL);
        if (infoModel != null) {
            initIncludeToolBar(infoModel.getTitle());
            infoDetailBind.setInfoModel(infoModel);
        } else {
        }

    }

    private void initIncludeToolBar(String title) {

        toolBarView = findViewById(R.id.include_toolbar);

        toolBarTitle = (TextView) toolBarView.findViewById(R.id.tv_toolbar_title);

        toolBarTitle.setText(title);

    }


}
