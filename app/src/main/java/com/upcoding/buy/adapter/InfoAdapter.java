package com.upcoding.buy.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import com.upcoding.buy.R;
import com.upcoding.buy.adapter.baseadapter.BaseRecyclerViewAdapter;
import com.upcoding.buy.adapter.baseadapter.BaseRecyclerViewHolder;
import com.upcoding.buy.bean.InfoHomeBean;
import com.upcoding.buy.databinding.ItemHomeInfoBind;
import com.upcoding.buy.model.InfoModel;
import com.upcoding.buy.utils.IntentUtil;

/**
 * Created by Heboot on 2017/4/26.
 * //test
 */

public class InfoAdapter extends BaseRecyclerViewAdapter<InfoModel> {

    private Activity activity;

    public InfoAdapter(InfoHomeBean infoHomeBean, Activity ac) {
        data = infoHomeBean.getInfo();
        activity = ac;
    }

    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_home_info);
//        }
    }


    private class ViewHolder extends BaseRecyclerViewHolder<InfoModel, ItemHomeInfoBind> {

        ViewHolder(ViewGroup parent, int layout) {
            super(parent, layout);
        }

        @Override
        public void onBindViewHolder(final InfoModel infoModel, int position) {
            binding.setInfoModel(infoModel);
            binding.getRoot().setOnClickListener((view) -> IntentUtil.intent2InfoDetailActivity(activity, infoModel, view));
        }
    }

}
