package com.upcoding.buy.ui;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.upcoding.buy.R;
import com.upcoding.buy.ui.recommend.RecommentFragment;
import com.upcoding.buy.utils.LogUtils;

public class MainActivity extends ToolbarActivity {


    @BindView(R.id.flyt_main_container)
    FrameLayout flytMainContainer;
    @BindView(R.id.ib_main_recommend)
    ImageButton ibMainRecommend;
    @BindView(R.id.ib_main_post)
    ImageButton ibMainPost;
    @BindView(R.id.ib_main_msg)
    ImageButton ibMainMsg;
    @BindView(R.id.ib_main_my)
    ImageButton ibMainMy;
    @BindView(R.id.llyt_main_bottom)
    LinearLayout llytMainBottom;
    @BindView(R.id.activity_main)
    ConstraintLayout activityMain;

    private ImageButton[] buttons;

    private RecommentFragment recommentFragment;

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
        recommentFragment = new RecommentFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(flytMainContainer.getId(), recommentFragment);
        fragmentTransaction.commit();
        fragmentTransaction.show(recommentFragment);
    }

    private void initListener() {
        for (ImageButton imageButton : buttons) {
            imageButton.setOnClickListener((view) -> checkMenu(imageButton.getId()));
        }
//        ibMainRecommend.performClick();
    }

    private void initData() {
        buttons = new ImageButton[]{ibMainRecommend, ibMainPost, ibMainMsg, ibMainMy};
        currentSelectMenuId = ibMainRecommend.getId();
    }


    private void refreshMenu(int id) {


    }


    private void checkMenu(int id) {
        if (id == currentSelectMenuId) {
            return;
        }
        switch (id) {
            case R.id.ib_main_recommend:
                ibMainRecommend.setImageResource(R.drawable.ic_account_balance_red_24dp);
                break;
            case R.id.ib_main_post:
                break;
            case R.id.ib_main_msg:
                break;
            case R.id.ib_main_my:
                break;
        }


        for (int i = 0; i < buttons.length; i++) {

        }


//        for (ImageButton imageButton : buttons) {
//            if (imageButton.getTag().equals(tag)) {
//                imageButton.setImageResource(R.drawable.ic_account_balance_red_24dp);
//            } else {
//                imageButton.setImageResource(R.drawable.ic_account_balance_gray_24dp);
//            }
//        }
    }


}
