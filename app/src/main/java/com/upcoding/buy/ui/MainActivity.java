package com.upcoding.buy.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.support.v7.widget.AppCompatImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.upcoding.buy.R;
import com.upcoding.buy.service.UserService;
import com.upcoding.buy.ui.info.InfoFragment;
import com.upcoding.buy.ui.post.PostFragment;
import com.upcoding.buy.utils.PermissionsChecker;

public class MainActivity extends ToolbarActivity {


    @BindView(R.id.flyt_main_container)
    FrameLayout flytMainContainer;
    @BindView(R.id.ib_main_recommend)
    AppCompatImageButton ibMainRecommend;
    @BindView(R.id.ib_main_post)
    AppCompatImageButton ibMainPost;
    @BindView(R.id.ib_main_msg)
    AppCompatImageButton ibMainMsg;
    @BindView(R.id.ib_main_my)
    AppCompatImageButton ibMainMy;
    @BindView(R.id.llyt_main_bottom)
    LinearLayout llytMainBottom;
    @BindView(R.id.activity_main)
    ConstraintLayout activityMain;

    private AppCompatImageButton[] buttons;

    private InfoFragment infoFragment;
    private PostFragment postFragment;
    private FragmentTransaction fragmentTransaction;

    private int currentSelectMenuId = -1;


    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    //test commit
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE,

    };

    private PermissionsChecker mPermissionsChecker; // 权限检测器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initListener();
        initFragments();
    }

    private void initFragments() {
        infoFragment = new InfoFragment();
        postFragment = new PostFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(flytMainContainer.getId(), infoFragment);
        fragmentTransaction.add(flytMainContainer.getId(), postFragment);
        fragmentTransaction.show(infoFragment).hide(postFragment);
        fragmentTransaction.commit();
    }

    private void initListener() {
        for (AppCompatImageButton imageButton : buttons) {
            imageButton.setOnClickListener((view) -> checkMenu(imageButton.getId()));
        }
    }

    protected void initData() {
//        UserService.getInstance().autoLogin(this);
        buttons = new AppCompatImageButton[]{ibMainRecommend, ibMainPost, ibMainMsg, ibMainMy};
        currentSelectMenuId = ibMainRecommend.getId();
        ibMainRecommend.setSelected(true);
        mPermissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    private void checkMenu(int id) {
        if (id == currentSelectMenuId) {
            return;
        }

        currentSelectMenuId = id;

        for (AppCompatImageButton imageButton : buttons) {
            if (imageButton.getId() == id) {
                imageButton.setSelected(true);
            } else {
                imageButton.setSelected(false);
            }
        }

        switch (id) {
            case R.id.ib_main_recommend:
                getSupportFragmentManager().beginTransaction().show(infoFragment).hide(postFragment).commit();
                break;
            case R.id.ib_main_post:
                getSupportFragmentManager().beginTransaction().show(postFragment).hide(infoFragment).commit();
                break;
            case R.id.ib_main_msg:
                break;
            case R.id.ib_main_my:
                break;
        }

//        getSupportFragmentManager().beginTransaction().commit();

    }


}
