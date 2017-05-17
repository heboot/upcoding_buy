package com.upcoding.buy.ui.recommend;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upcoding.buy.R;
import com.upcoding.buy.RecommendBind;
import com.upcoding.buy.adapter.InfoAdapter;
import com.upcoding.buy.bean.InfoHomeBean;
import com.upcoding.buy.service.InfoService;
import com.upcoding.buy.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Heboot on 2017/4/26.
 */

public class InfoFragment extends Fragment {
    protected String TAG = this.getClass().getName();
    RecommendBind bind;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;

    private Activity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        ButterKnife.bind(this, bind.getRoot());
        rvRecommend.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        initData();
        return bind.getRoot();
    }


    protected void initData() {
        InfoService.getInstance().home(1, 10, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InfoHomeBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.e(TAG, "onCompleted");


                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onError");
                    }

                    @Override
                    public void onNext(InfoHomeBean bean) {
                        LogUtils.e(TAG, "onNext");
//                        bind.setInfoModel(bean.getInfo().get(0));
//                        rvRecommend.setAdapter();
//                        bean.getInfo().add(0,new InfoModel());
                        bind.setAdapter(new InfoAdapter(bean, getActivity()));
                    }
                });
    }
}
