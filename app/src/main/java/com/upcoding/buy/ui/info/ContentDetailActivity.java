package com.upcoding.buy.ui.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.upcoding.buy.R;
import com.upcoding.buy.common.ConstantValue;
import com.upcoding.buy.common.ContentKey;
import com.upcoding.buy.databinding.ContentDetailBind;
import com.upcoding.buy.databinding.InfoDetailBind;
import com.upcoding.buy.model.InfoModel;
import com.upcoding.buy.model.PostModel;
import com.upcoding.buy.ui.ToolbarActivity;

/**
 * Created by Heboot on 2017/5/3.
 */

public class ContentDetailActivity extends ToolbarActivity {


    private InfoModel infoModel;
    private PostModel postModel;

    private View toolBarView;

    private TextView toolBarTitle;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


    @Override
    protected void initData() {
        ContentDetailBind contentDetailBind = DataBindingUtil.setContentView(this, R.layout.layout_content_detail);
        int contentType = getIntent().getExtras().getInt(ContentKey.PAGEJUMP_CONTENT_TYPE);
        if (contentType == ConstantValue.CONTENT_TYPE_INFO) {
            infoModel = (InfoModel) getIntent().getExtras().get(ContentKey.INFO_MODEL);
            initIncludeToolBar(infoModel.getTitle());
            contentDetailBind.setInfoModel(infoModel);
        } else if (contentType == ConstantValue.CONTENT_TYPE_POST) {
            postModel = (PostModel) getIntent().getExtras().get(ContentKey.POST_MODEL);
            initIncludeToolBar(postModel.getTitle());
            contentDetailBind.setPostModel(postModel);
        }

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
