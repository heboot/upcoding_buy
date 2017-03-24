package upcoding.com.buy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upcoding.com.buy.R;
import upcoding.com.buy.api.RxHelper;
import upcoding.com.buy.api.RxSubcribe;
import upcoding.com.buy.bean.CommonGuestBean;
import upcoding.com.buy.service.CommonService;
import upcoding.com.buy.utils.LogUtils;

public class MainActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CommonService.getInstance().homeGuest().subscribe(new RxSubcribe<CommonGuestBean>() {
            @Override
            protected void _onNext(CommonGuestBean bean) {
                LogUtils.e(TAG, bean.toString());
            }

            @Override
            protected void _onError(String msg) {
                LogUtils.e(TAG + ">>>>>>", msg);
            }
        });
    }
}
