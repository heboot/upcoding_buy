package upcoding.com.buy.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import upcoding.com.buy.R;
import upcoding.com.buy.utils.DensityUtil;
import upcoding.com.buy.utils.LogUtils;

public class MainActivity extends ToolbarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        CommonService.getInstance().homeGuest().subscribe(new RxSubcribe<CommonGuestBean>() {
//            @Override
//            protected void _onNext(CommonGuestBean bean) {
//                LogUtils.e(TAG, bean.toString());
//            }
//
//            @Override
//            protected void _onError(String msg) {
//                LogUtils.e(TAG + ">>>>>>", msg);
//            }
//        });
    }

    public boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {//ceshi sshkey
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }


}
