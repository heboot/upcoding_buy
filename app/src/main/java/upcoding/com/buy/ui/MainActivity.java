package upcoding.com.buy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upcoding.com.buy.R;
import upcoding.com.buy.service.CommonService;
import upcoding.com.buy.utils.LogUtils;

public class MainActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CommonService.getInstance().homeGuest();

//                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
//                .subscribe(
//                        (bean) -> {
//                            LogUtils.e(TAG, "ON CALL" + bean);
//                        },
//                        (e) -> {
//                            LogUtils.e(TAG, "ON CALL2" + e);
//                        },
//                        () -> {
//                            LogUtils.e(TAG, "ON CALL3");
//                        });
//
//
    }
}
