package upcoding.com.buy.ui.recommend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upcoding.com.buy.R;
import upcoding.com.buy.api.ApiClient;
import upcoding.com.buy.bean.CommonGuestBean;
import upcoding.com.buy.bean.InfoHomeBean;
import upcoding.com.buy.service.CommonService;
import upcoding.com.buy.service.InfoService;
import upcoding.com.buy.ui.BaseFragment;
import upcoding.com.buy.utils.LogUtils;

/**
 * Created by Heboot on 2017/4/26.
 */

public class RecommentFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
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
                        LogUtils.e(TAG, "onError" );
                    }

                    @Override
                    public void onNext(InfoHomeBean bean) {
                        LogUtils.e(TAG, "onNext");
                    }
                });
    }
}
