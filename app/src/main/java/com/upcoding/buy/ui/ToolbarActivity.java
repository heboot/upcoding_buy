package com.upcoding.buy.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upcoding.buy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Heboot on 2017/1/11.
 */

public abstract class ToolbarActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getName();

    private Toolbar toolbar;
    private ImageButton ibToolBarSend;
    private TextView tvToolBarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base_toolbar);
//        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }

        initToolbar();

//
//
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected abstract void initData();

    /**
     * 显示ToolBar
     */
    protected void showToolBar(String title, boolean displayHomeAsUpEnabled, Integer homeAsUpResId) {
        setToolBarTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (displayHomeAsUpEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (homeAsUpResId != null) {
                getSupportActionBar().setHomeAsUpIndicator(homeAsUpResId);
            }
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            tvToolBarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
            ibToolBarSend = (ImageButton) toolbar.findViewById(R.id.tv_toolbar_send);
        }
    }

    protected void setToolBarTitle(String title) {
        if (tvToolBarTitle != null) {
            tvToolBarTitle.setText(title);
        }
    }

    protected void showToolBarSend(View.OnClickListener listener) {
        ibToolBarSend.setVisibility(View.VISIBLE);
        ibToolBarSend.setOnClickListener(listener);
    }


}
