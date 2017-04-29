package com.upcoding.buy.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upcoding.buy.R;
import com.upcoding.buy.adapter.baseadapter.BaseRecyclerViewAdapter;
import com.upcoding.buy.adapter.baseadapter.BaseRecyclerViewHolder;
import com.upcoding.buy.bean.InfoHomeBean;
import com.upcoding.buy.databinding.ItemBinding;
import com.upcoding.buy.model.InfoModel;
import com.upcoding.buy.utils.LogUtils;

/**
 * Created by Heboot on 2017/4/26.
 * //test
 */

public class MyAdapter extends BaseRecyclerViewAdapter<InfoModel> {

    InfoHomeBean bean;

    public MyAdapter(InfoHomeBean beabn) {
        data = beabn.getInfo();
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<InfoModel, ItemBinding> {

        ViewHolder(ViewGroup parent, int layout) {
            super(parent, layout);
        }


        @Override
        public void onBindViewHolder(final InfoModel beana, int position) {
            binding.setInfoModel(beana);
//            binding.tvItem.setOnClickListener(new PerfectClickListener() {
//                @Override
//                protected void onNoDoubleClick(View v) {
//                    if (bean != null && !TextUtils.isEmpty(bean.getAlt())) {
//                        WebViewActivity.loadUrl(v.getContext(), bean.getAlt(), bean.getName());
//                    }
//                }
//            });
        }
    }

}
