package com.upcoding.buy.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upcoding.buy.MyApplication;
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
    private final int viewtype_search = 1;
    private final int viewtype_item = 2;

    public MyAdapter(InfoHomeBean beabn) {
        data = beabn.getInfo();
    }

    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        switch (viewType) {
//            case viewtype_search:
//                return new SearchHolder(parent, R.layout.view_fragment_recommend_top);
//            case viewtype_item:
        return new ViewHolder(parent, R.layout.item);
//        }
//        return null;
    }


//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {
//            return viewtype_search;
//        }
//        return viewtype_item;
//    }


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
