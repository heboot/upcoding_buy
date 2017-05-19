package com.upcoding.buy.ui;

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
import com.upcoding.buy.ui.info.InfoFragment;
import com.upcoding.buy.ui.post.PostFragment;

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

    private void initData() {
        buttons = new AppCompatImageButton[]{ibMainRecommend, ibMainPost, ibMainMsg, ibMainMy};
        currentSelectMenuId = ibMainRecommend.getId();
        ibMainRecommend.setSelected(true);
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
