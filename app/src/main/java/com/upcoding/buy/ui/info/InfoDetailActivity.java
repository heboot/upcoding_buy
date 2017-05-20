package com.upcoding.buy.ui.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.upcoding.buy.R;
import com.upcoding.buy.common.ContentKey;
import com.upcoding.buy.databinding.ContentDetailBind;
import com.upcoding.buy.databinding.InfoDetailBind;
import com.upcoding.buy.model.InfoModel;
import com.upcoding.buy.ui.ToolbarActivity;

/**
 * Created by Heboot on 2017/5/3.
 */

public class InfoDetailActivity extends ToolbarActivity {

    private InfoModel infoModel;

    private View toolBarView;

    private TextView toolBarTitle;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentDetailBind contentDetailBind = DataBindingUtil.setContentView(this, R.layout.layout_content_detail);
        infoModel = (InfoModel) getIntent().getExtras().get(ContentKey.INFO_MODEL);
        if (infoModel != null) {
            initIncludeToolBar(infoModel.getTitle());
            contentDetailBind.setContentType("info");
            contentDetailBind.setInfoModel(infoModel);
        } else {
        }

        String aa = "a";
        aa.equals("2");

    }

    private void initIncludeToolBar(String title) {

        toolBarView = findViewById(R.id.include_toolbar);

        toolbar = (Toolbar) toolBarView.findViewById(R.id.toolbar);

        toolBarTitle = (TextView) toolBarView.findViewById(R.id.tv_toolbar_title);

        toolBarTitle.setText(title);

        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}
