package com.upcoding.buy.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.upcoding.buy.R;

/**
 * Created by Heboot on 2017/1/11.
 */

public class ToolbarActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
////        // 这句很关键，注意是调用父类的方法
//        super.setContentView(R.layout.activity_base_toolbar);
//////        // 经测试在代码里直接声明透明状态栏更有效
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
////            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
////            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//////            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
////        }
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }
    }


}
