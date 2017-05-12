package com.upcoding.buy.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import com.upcoding.buy.R;
import com.upcoding.buy.adapter.baseadapter.BaseRecyclerViewAdapter;
import com.upcoding.buy.adapter.baseadapter.BaseRecyclerViewHolder;
import com.upcoding.buy.bean.PostHomeBean;
import com.upcoding.buy.databinding.ItemBinding;
import com.upcoding.buy.model.PostModel;

/**
 * Created by Heboot on 2017/4/26.
 * //test
 */

public class PostAdapter extends BaseRecyclerViewAdapter<PostModel> {

    private Activity activity;

    public PostAdapter(PostHomeBean postHomeBean, Activity ac) {
        data = postHomeBean.getPost();
        activity = ac;
    }

    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_home_info);
//        }
    }


    private class ViewHolder extends BaseRecyclerViewHolder<PostModel, ItemBinding> {

        ViewHolder(ViewGroup parent, int layout) {
            super(parent, layout);
        }

        @Override
        public void onBindViewHolder(final PostModel infoModel, int position) {
//            binding.set(infoModel);
//            binding.getRoot().setOnClickListener((view) -> IntentUtil.intent2InfoDetailActivity(activity, infoModel, view));
        }
    }

}
